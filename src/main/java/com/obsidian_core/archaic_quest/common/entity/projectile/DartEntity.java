package com.obsidian_core.archaic_quest.common.entity.projectile;

import com.google.common.collect.Lists;
import com.obsidian_core.archaic_quest.common.misc.AQDamageSources;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class DartEntity extends ProjectileEntity {

    private static final double ARROW_BASE_DAMAGE = 2.0D;
    private static final TrackedData<Byte> ID_FLAGS = DataTracker.registerData(DartEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Byte> PIERCE_LEVEL = DataTracker.registerData(DartEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final int FLAG_CRIT = 1;
    private static final int FLAG_NOPHYSICS = 2;
    private static final int FLAG_CROSSBOW = 4;
    @Nullable
    private BlockState lastState;
    protected boolean inGround;
    protected int inGroundTime;
    public PersistentProjectileEntity.PickupPermission pickup = PersistentProjectileEntity.PickupPermission.DISALLOWED;
    public int shakeTime;
    private int life;
    private double baseDamage = 2.0D;
    private int knockback;
    private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
    @Nullable
    private IntOpenHashSet piercingIgnoreEntityIds;
    @Nullable
    private List<Entity> piercedAndKilledEntities;


    public DartEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public DartEntity(EntityType<? extends ProjectileEntity> entityType, double x, double y, double z, World world) {
        this(entityType, world);
        setPos(x, y, z);
    }

    protected DartEntity(EntityType<? extends PersistentProjectileEntity> p_36717_, LivingEntity p_36718_, World p_36719_) {
        this(p_36717_, p_36718_.getX(), p_36718_.getEyeY() - (double)0.1F, p_36718_.getZ(), p_36719_);
        this.setOwner(p_36718_);
        if (p_36718_ instanceof PlayerEntity) {
            this.pickup = PersistentProjectileEntity.PickupPermission.ALLOWED;
        }

    }

    public void setSoundEvent(SoundEvent p_36741_) {
        this.soundEvent = p_36741_;
    }

    public boolean shouldRenderAtSqrDistance(double p_36726_) {
        double d0 = this.getBoundingBox().getAverageSideLength() * 10.0D;
        if (Double.isNaN(d0)) {
            d0 = 1.0D;
        }

        d0 *= 64.0D * getRenderDistanceMultiplier();
        return p_36726_ < d0 * d0;
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(ID_FLAGS, (byte)0);
        this.dataTracker.startTracking(PIERCE_LEVEL, (byte)0);
    }

    @Override
    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        super.setVelocity(x, y, z, speed, divergence);
        this.life = 0;
    }

    @Override
    public void updatePositionAndAngles(double x, double y, double z, float yaw, float pitch) {
        this.setPos(x, y, z);
        this.setRotation(yaw, pitch);
    }

    @Override
    public void setVelocityClient(double x, double y, double z) {
        super.setVelocityClient(x, y, z);
        this.life = 0;
    }

    @Override
    public void tick() {
        super.tick();
        boolean flag = this.isNoPhysics();
        Vec3d vec3 = this.getVelocity();
        if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
            double d0 = vec3.horizontalLength();
            this.setYaw((float)(MathHelper.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
            this.setPitch((float)(MathHelper.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
            this.prevYaw = this.getYaw();
            this.prevPitch = this.getPitch();
        }

        BlockPos blockpos = this.getBlockPos();
        BlockState blockstate = this.world.getBlockState(blockpos);
        if (!blockstate.isAir() && !flag) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
            if (!voxelshape.isEmpty()) {
                Vec3d vec31 = this.getPos();

                for(Box box : voxelshape.getBoundingBoxes()) {
                    if (box.offset(blockpos).contains(vec31)) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }

        if (this.shakeTime > 0) {
            --this.shakeTime;
        }

        if (this.isTouchingWaterOrRain() || blockstate.isOf(Blocks.POWDER_SNOW) || this.isInFluidType((fluidType, height) -> this.canFluidExtinguish(fluidType))) {
            this.extinguish();
        }

        if (this.inGround && !flag) {
            if (this.lastState != blockstate && this.shouldFall()) {
                this.startFalling();
            } else if (!this.world.isClient()) {
                this.tickDespawn();
            }

            ++this.inGroundTime;
        } else {
            this.inGroundTime = 0;
            Vec3d vec32 = this.getPos();
            Vec3d vec33 = vec32.add(vec3);
            HitResult hitresult = this.world.raycast(new RaycastContext(vec32, vec33, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this));
            if (hitresult.getType() != HitResult.Type.MISS) {
                vec33 = hitresult.getPos();
            }

            while(!this.isRemoved()) {
                EntityHitResult entityhitresult = this.findHitEntity(vec32, vec33);
                if (entityhitresult != null) {
                    hitresult = entityhitresult;
                }

                if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
                    Entity entity = ((EntityHitResult)hitresult).getEntity();
                    Entity entity1 = this.getOwner();
                    if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity)entity1).shouldDamagePlayer((PlayerEntity)entity)) {
                        hitresult = null;
                        entityhitresult = null;
                    }
                }

                if (hitresult != null && hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                    this.onCollision(hitresult);
                    this.velocityDirty = true;
                }

                if (entityhitresult == null || this.getPierceWorld() <= 0) {
                    break;
                }

                hitresult = null;
            }

            vec3 = this.getVelocity();
            double d5 = vec3.x;
            double d6 = vec3.y;
            double d1 = vec3.z;
            if (this.isCritArrow()) {
                for(int i = 0; i < 4; ++i) {
                    this.world.addParticle(ParticleTypes.CRIT, this.getX() + d5 * (double)i / 4.0D, this.getY() + d6 * (double)i / 4.0D, this.getZ() + d1 * (double)i / 4.0D, -d5, -d6 + 0.2D, -d1);
                }
            }

            double d7 = this.getX() + d5;
            double d2 = this.getY() + d6;
            double d3 = this.getZ() + d1;
            double d4 = vec3.horizontalLength();
            if (flag) {
                this.setYaw((float)(MathHelper.atan2(-d5, -d1) * (double)(180F / (float)Math.PI)));
            } else {
                this.setYaw((float)(MathHelper.atan2(d5, d1) * (double)(180F / (float)Math.PI)));
            }

            this.setPitch((float)(MathHelper.atan2(d6, d4) * (double)(180F / (float)Math.PI)));
            this.setPitch(updateRotation(this.prevPitch, this.getPitch()));
            this.setYaw(updateRotation(this.prevYaw, this.getYaw()));
            float f = 0.99F;
            float f1 = 0.05F;
            if (this.submergedInWater) {
                for(int j = 0; j < 4; ++j) {
                    float f2 = 0.25F;
                    this.world.addParticle(ParticleTypes.BUBBLE, d7 - d5 * 0.25D, d2 - d6 * 0.25D, d3 - d1 * 0.25D, d5, d6, d1);
                }

                f = this.getWaterInertia();
            }

            this.setVelocity(vec3.multiply(f));
            if (!this.hasNoGravity() && !flag) {
                Vec3d vec34 = this.getVelocity();
                this.setVelocity(vec34.x, vec34.y - (double)0.05F, vec34.z);
            }

            this.setPos(d7, d2, d3);
            this.checkBlockCollision();
        }
    }

    private boolean shouldFall() {
        return this.inGround && this.world.isSpaceEmpty((new Box(this.getPos(), this.getPos())).expand(0.06D));
    }

    private void startFalling() {
        this.inGround = false;
        Vec3d vec3 = this.getVelocity();
        this.setVelocity(vec3.multiply(this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F));
        this.life = 0;
    }

    @Override
    public void move(MovementType movementType, Vec3d movement) {
        super.move(movementType, movement);
        if (movementType != MovementType.SELF && this.shouldFall()) {
            this.startFalling();
        }
    }

    protected void tickDespawn() {
        ++this.life;
        if (this.life >= 1200) {
            this.discard();
        }

    }

    private void resetPiercedEntities() {
        if (this.piercedAndKilledEntities != null) {
            this.piercedAndKilledEntities.clear();
        }

        if (this.piercingIgnoreEntityIds != null) {
            this.piercingIgnoreEntityIds.clear();
        }

    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        float f = (float)this.getVelocity().length();
        int i = MathHelper.ceil(MathHelper.clamp((double)f * this.baseDamage, 0.0D, 2.147483647E9D));
        if (this.getPierceWorld() > 0) {
            if (this.piercingIgnoreEntityIds == null) {
                this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
            }

            if (this.piercedAndKilledEntities == null) {
                this.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
            }

            if (this.piercingIgnoreEntityIds.size() >= this.getPierceWorld() + 1) {
                this.discard();
                return;
            }

            this.piercingIgnoreEntityIds.add(entity.getId());
        }

        if (this.isCritArrow()) {
            long j = (long)this.random.nextInt(i / 2 + 2);
            i = (int)Math.min(j + (long)i, 2147483647L);
        }

        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = AQDamageSources.dart(this, this);
        }
        else {
            damagesource = AQDamageSources.dart(this, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity)entity1).onAttacking(entity);
            }
        }

        boolean flag = entity.getType() == EntityType.ENDERMAN;
        int k = entity.getFireTicks();
        if (this.isOnFire() && !flag) {
            entity.setOnFireFor(5);
        }

        if (entity.damage(damagesource, (float)i)) {
            if (flag) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                if (!this.world.isClient() && this.getPierceWorld() <= 0) {
                    livingentity.setStuckArrowCount(livingentity.getStuckArrowCount() + 1);
                }

                if (this.knockback > 0) {
                    double d0 = Math.max(0.0D, 1.0D - livingentity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                    Vec3d vec3 = this.getVelocity().multiply(1.0D, 0.0D, 1.0D).normalize().multiply((double)this.knockback * 0.6D * d0);
                    if (vec3.lengthSquared() > 0.0D) {
                        livingentity.pushAway(vec3.x, 0.1D, vec3.z);
                    }
                }

                if (!this.world.isClient && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingentity, entity1);
                    EnchantmentHelper.onTargetDamaged((LivingEntity)entity1, livingentity);
                }

                this.doPostHurtEffects(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof PlayerEntity && entity1 instanceof ServerPlayerEntity && !this.isSilent()) {
                    ((ServerPlayerEntity)entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
                }

                if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
                    this.piercedAndKilledEntities.add(livingentity);
                }

                if (!this.world.isClient() && entity1 instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverplayer = (ServerPlayerEntity)entity1;
                    if (this.piercedAndKilledEntities != null && this.shotFromCrossbow()) {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverplayer, this.piercedAndKilledEntities);
                    } else if (!entity.isAlive() && this.shotFromCrossbow()) {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverplayer, Arrays.asList(entity));
                    }
                }
            }

            this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceWorld() <= 0) {
                this.discard();
            }
        } else {
            entity.setFireTicks(k);
            this.setVelocity(this.getVelocity().multiply(-0.1D));
            this.setYaw(this.getYaw() + 180.0F);
            this.prevYaw += 180.0F;
            if (!this.world.isClient() && this.getVelocity().lengthSquared() < 1.0E-7D) {
                if (this.pickup == PersistentProjectileEntity.PickupPermission.ALLOWED) {
                    this.dropStack(this.getPickupItem(), 0.1F);
                }

                this.discard();
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.lastState = this.world.getBlockState(blockHitResult.getBlockPos());
        super.onBlockHit(blockHitResult);
        Vec3d vec3 = blockHitResult.getPos().subtract(this.getX(), this.getY(), this.getZ());
        this.setVelocity(vec3);
        Vec3d vec31 = vec3.normalize().multiply((double)0.05F);
        this.setPos(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
        this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.inGround = true;
        this.shakeTime = 7;
        this.setCritArrow(false);
        this.setPierceWorld((byte)0);
        this.setSoundEvent(SoundEvents.ENTITY_ARROW_HIT);
        this.setShotFromCrossbow(false);
        this.resetPiercedEntities();
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.ENTITY_ARROW_HIT;
    }

    protected final SoundEvent getHitGroundSoundEvent() {
        return this.soundEvent;
    }

    protected void doPostHurtEffects(LivingEntity livingEntity) {
    }


    @Nullable
    protected EntityHitResult findHitEntity(Vec3d p_36758_, Vec3d p_36759_) {
        return ProjectileUtil.getEntityCollision(this.world, this, p_36758_, p_36759_, this.getBoundingBox().stretch(this.getVelocity()).expand(1.0D), this::canHit);
    }

    @Override
    protected boolean canHit(Entity entity) {
        return super.canHit(entity) && (this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(entity.getId()));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putShort("life", (short)this.life);
        if (this.lastState != null) {
            nbt.put("inBlockState", NbtHelper.fromBlockState(this.lastState));
        }

        nbt.putByte("shake", (byte)this.shakeTime);
        nbt.putBoolean("inGround", this.inGround);
        nbt.putByte("pickup", (byte)this.pickup.ordinal());
        nbt.putDouble("damage", this.baseDamage);
        nbt.putBoolean("crit", this.isCritArrow());
        nbt.putByte("PierceWorld", this.getPierceWorld());
        nbt.putString("SoundEvent", Registry.SOUND_EVENT.getKey(this.soundEvent).toString());
        nbt.putBoolean("ShotFromCrossbow", this.shotFromCrossbow());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.life = nbt.getShort("life");
        if (nbt.contains("inBlockState", 10)) {
            this.lastState = NbtHelper.toBlockState(nbt.getCompound("inBlockState"));
        }

        this.shakeTime = nbt.getByte("shake") & 255;
        this.inGround = nbt.getBoolean("inGround");
        if (nbt.contains("damage", 99)) {
            this.baseDamage = nbt.getDouble("damage");
        }

        this.pickup = PersistentProjectileEntity.PickupPermission.fromOrdinal(nbt.getByte("pickup"));
        this.setCritArrow(nbt.getBoolean("crit"));
        this.setPierceWorld(nbt.getByte("PierceWorld"));
        if (nbt.contains("SoundEvent", 8)) {
            this.soundEvent = Registry.SOUND_EVENT.getOrEmpty(new Identifier(nbt.getString("SoundEvent"))).orElse(this.getDefaultHitGroundSoundEvent());
        }

        this.setShotFromCrossbow(nbt.getBoolean("ShotFromCrossbow"));
    }

    @Override
    public void setOwner(@Nullable Entity entity) {
        super.setOwner(entity);
        if (entity instanceof PlayerEntity) {
            this.pickup = ((PlayerEntity)entity).getAbilities().creativeMode ? PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY : PersistentProjectileEntity.PickupPermission.ALLOWED;
        }

    }

    @Override
    public void onPlayerCollision(PlayerEntity playerEntity) {
        if (!this.world.isClient() && (this.inGround || this.isNoPhysics()) && this.shakeTime <= 0) {
            if (this.tryPickup(playerEntity)) {
                playerEntity.sendPickup(this, 1);
                this.discard();
            }

        }
    }

    protected boolean tryPickup(PlayerEntity playerEntity) {
        return switch (this.pickup) {
            case ALLOWED -> playerEntity.getInventory().insertStack(this.getPickupItem());
            case CREATIVE_ONLY -> playerEntity.getAbilities().creativeMode;
            default -> false;
        };
    }

    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected Entity.MoveEffect getMoveEffect() {
        return Entity.MoveEffect.NONE;
    }

    public void setBaseDamage(double p_36782_) {
        this.baseDamage = p_36782_;
    }

    public double getBaseDamage() {
        return this.baseDamage;
    }

    public void setKnockback(int p_36736_) {
        this.knockback = p_36736_;
    }

    public int getKnockback() {
        return this.knockback;
    }

    public boolean isAttackable() {
        return false;
    }

    @Override
    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.13F;
    }

    public void setCritArrow(boolean p_36763_) {
        this.setFlag(1, p_36763_);
    }

    public void setPierceWorld(byte p_36768_) {
        this.dataTracker.set(PIERCE_LEVEL, p_36768_);
    }

    public void setFlag(int p_36738_, boolean p_36739_) {
        byte b0 = this.dataTracker.get(ID_FLAGS);
        if (p_36739_) {
            this.dataTracker.set(ID_FLAGS, (byte)(b0 | p_36738_));
        } else {
            this.dataTracker.set(ID_FLAGS, (byte)(b0 & ~p_36738_));
        }

    }

    public boolean isCritArrow() {
        byte b0 = this.dataTracker.get(ID_FLAGS);
        return (b0 & 1) != 0;
    }

    public boolean shotFromCrossbow() {
        byte b0 = this.dataTracker.get(ID_FLAGS);
        return (b0 & 4) != 0;
    }

    public byte getPierceWorld() {
        return this.dataTracker.get(PIERCE_LEVEL);
    }

    //TODO forge
    public void applyEnchantmentEffects(LivingEntity p_36746_, float p_36747_) {
        int i = EnchantmentHelper.getEnchantmentWorld(Enchantments.POWER_ARROWS, p_36746_);
        int j = EnchantmentHelper.getEnchantmentWorld(Enchantments.PUNCH_ARROWS, p_36746_);
        this.setBaseDamage((double)(p_36747_ * 2.0F) + this.random.triangle((double)this.world.getDifficulty().getId() * 0.11D, 0.57425D));
        if (i > 0) {
            this.setBaseDamage(this.getBaseDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0) {
            this.setKnockback(j);
        }

        if (EnchantmentHelper.getEnchantmentWorld(Enchantments.FLAMING_ARROWS, p_36746_) > 0) {
            this.setOnFireFor(100);
        }

    }

    protected float getWaterInertia() {
        return 0.6F;
    }

    //TODO forge
    public void setNoClip(boolean noClip) {
        this.noClip = noClip;
        this.setFlag(2, noClip);
    }

    public boolean isNoPhysics() {
        if (!this.world.isClient()) {
            return this.noClip;
        } else {
            return (this.dataTracker.get(ID_FLAGS) & 2) != 0;
        }
    }

    public void setShotFromCrossbow(boolean p_36794_) {
        this.setFlag(4, p_36794_);
    }

    public enum Pickup {
        DISALLOWED,
        ALLOWED,
        CREATIVE_ONLY;

        public static Pickup byOrdinal(int index) {
            if (index < 0 || index > values().length) {
                index = 0;
            }
            return values()[index];
        }
    }
}
