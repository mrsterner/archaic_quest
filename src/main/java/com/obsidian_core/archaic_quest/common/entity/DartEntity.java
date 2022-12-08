package com.obsidian_core.archaic_quest.common.entity;

import com.obsidian_core.archaic_quest.common.misc.AQDamageSources;
import com.obsidian_core.archaic_quest.common.misc.AQNBTUtil;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class DartEntity extends ProjectileEntity {

    private static final DataParameter<Byte> ID_FLAGS = EntityDataManager.defineId(DartEntity.class, DataSerializers.BYTE);
    @Nullable
    private BlockState lastState;
    protected boolean inGround;
    protected int inGroundTime;
    public PickupStatus pickup = PickupStatus.DISALLOWED;
    public int shakeTime;
    private int life;
    private double baseDamage = 2.0D;
    private int knockback;
    private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();


    public DartEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    protected DartEntity(EntityType<? extends ProjectileEntity> entityType, double x, double y, double z, World world) {
        this(entityType, world);
        setPos(x, y, z);
    }

    protected DartEntity(EntityType<? extends ProjectileEntity> entityType, LivingEntity livingEntity, World world) {
        this(entityType, livingEntity.getX(), livingEntity.getEyeY() - (double)0.1F, livingEntity.getZ(), world);
        setOwner(livingEntity);

        if (livingEntity instanceof PlayerEntity) {
            pickup = PickupStatus.ALLOWED;
        }
    }

    public void setSoundEvent(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderAtSqrDistance(double dist) {
        double size = getBoundingBox().getSize() * 10.0D;

        if (Double.isNaN(size)) {
            size = 1.0D;
        }

        size = size * 64.0D * getViewScale();
        return dist < size * size;
    }

    protected void defineSynchedData() {
        entityData.define(ID_FLAGS, (byte)0);
    }

    @Override
    public void shoot(double x, double y, double z, float pitch, float yaw) {
        super.shoot(x, y, z, pitch, yaw);
        life = 0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void lerpTo(double x, double y, double z, float yRot, float xRot, int p_180426_9_, boolean p_180426_10_) {
        setPos(x, y, z);
        setRot(yRot, xRot);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void lerpMotion(double x, double y, double z) {
        super.lerpMotion(x, y, z);
        life = 0;
    }

    public void tick() {
        super.tick();
        boolean flag = isNoPhysics();
        Vector3d deltaMovement = getDeltaMovement();

        if (xRotO == 0.0F && yRotO == 0.0F) {
            float f = MathHelper.sqrt(getHorizontalDistanceSqr(deltaMovement));
            this.yRot = (float)(MathHelper.atan2(deltaMovement.x, deltaMovement.z) * (double)(180F / (float)Math.PI));
            this.xRot = (float)(MathHelper.atan2(deltaMovement.y, f) * (double)(180F / (float)Math.PI));
            this.yRotO = this.yRot;
            this.xRotO = this.xRot;
        }

        BlockPos blockpos = blockPosition();
        BlockState blockstate = level.getBlockState(blockpos);

        if (!blockstate.isAir(level, blockpos) && !flag) {
            VoxelShape voxelshape = blockstate.getCollisionShape(level, blockpos);
            if (!voxelshape.isEmpty()) {
                Vector3d vector3d1 = this.position();

                for(AxisAlignedBB axisalignedbb : voxelshape.toAabbs()) {
                    if (axisalignedbb.move(blockpos).contains(vector3d1)) {
                        inGround = true;
                        break;
                    }
                }
            }
        }

        if (shakeTime > 0) {
            --shakeTime;
        }

        if (isInWaterOrRain()) {
            clearFire();
        }

        if (inGround && !flag) {
            if (lastState != blockstate && shouldFall()) {
                startFalling();
            }
            else if (!level.isClientSide) {
                tickDespawn();
            }

            ++inGroundTime;
        }
        else {
            inGroundTime = 0;
            Vector3d vector3d2 = position();
            Vector3d vector3d3 = vector3d2.add(deltaMovement);
            RayTraceResult traceResult = level.clip(new RayTraceContext(vector3d2, vector3d3, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));

            if (traceResult.getType() != RayTraceResult.Type.MISS) {
                vector3d3 = traceResult.getLocation();
            }

            while(isAlive()) {
                EntityRayTraceResult entityResult = findHitEntity(vector3d2, vector3d3);
                if (entityResult != null) {
                    traceResult = entityResult;
                }

                if (traceResult != null && traceResult.getType() == RayTraceResult.Type.ENTITY) {
                    Entity entity = ((EntityRayTraceResult)traceResult).getEntity();
                    Entity owner = getOwner();

                    if (entity instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity) owner).canHarmPlayer((PlayerEntity) entity)) {
                        traceResult = null;
                        entityResult = null;
                    }
                }

                if (traceResult != null && traceResult.getType() != RayTraceResult.Type.MISS && !flag && !ForgeEventFactory.onProjectileImpact(this, traceResult)) {
                    onHit(traceResult);
                    hasImpulse = true;
                }

                if (entityResult == null) {
                    break;
                }
                traceResult = null;
            }

            deltaMovement = getDeltaMovement();
            double xVel = deltaMovement.x;
            double yVel = deltaMovement.y;
            double zVel = deltaMovement.z;
            double newX = getX() + xVel;
            double newY = getY() + yVel;
            double newZ = getZ() + zVel;
            float f1 = MathHelper.sqrt(getHorizontalDistanceSqr(deltaMovement));

            if (flag) {
                yRot = (float)(MathHelper.atan2(-xVel, -zVel) * (double)(180F / (float)Math.PI));
            }
            else {
                yRot = (float)(MathHelper.atan2(xVel, zVel) * (double)(180F / (float)Math.PI));
            }

            xRot = (float)(MathHelper.atan2(yVel, f1) * (double)(180F / (float)Math.PI));
            xRot = lerpRotation(xRotO, xRot);
            yRot = lerpRotation(yRotO, yRot);
            float f2 = 0.99F;

            if (isInWater()) {
                for(int j = 0; j < 4; ++j) {
                    level.addParticle(ParticleTypes.BUBBLE, newX - xVel * 0.25D, newY - yVel * 0.25D, newZ - zVel * 0.25D, xVel, yVel, zVel);
                }
                f2 = getWaterInertia();
            }
            setDeltaMovement(deltaMovement.scale(f2));

            if (!isNoGravity() && !flag) {
                Vector3d vector3d4 = getDeltaMovement();
                setDeltaMovement(vector3d4.x, vector3d4.y - (double)0.05F, vector3d4.z);
            }
            setPos(newX, newY, newZ);
            checkInsideBlocks();
        }
    }

    private boolean shouldFall() {
        return inGround && level.noCollision((new AxisAlignedBB(position(), position())).inflate(0.06D));
    }

    private void startFalling() {
        inGround = false;
        Vector3d vector3d = getDeltaMovement();
        setDeltaMovement(vector3d.multiply(random.nextFloat() * 0.2F, random.nextFloat() * 0.2F, random.nextFloat() * 0.2F));
        life = 0;
    }

    @Override
    public void move(MoverType moverType, Vector3d vec3d) {
        super.move(moverType, vec3d);

        if (moverType != MoverType.SELF && shouldFall()) {
            startFalling();
        }
    }

    protected void tickDespawn() {
        ++life;

        if (life >= 1200) {
            remove();
        }
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult traceResult) {
        super.onHitEntity(traceResult);
        Entity entity = traceResult.getEntity();
        float f = (float)this.getDeltaMovement().length();
        int i = MathHelper.ceil(MathHelper.clamp((double)f * baseDamage, 0.0D, 2.147483647E9D));
        Entity owner = this.getOwner();
        DamageSource damagesource;

        if (owner == null) {
            damagesource = AQDamageSources.dart(this, this);
        }
        else {
            damagesource = AQDamageSources.dart(this, owner);

            if (owner instanceof LivingEntity) {
                ((LivingEntity)owner).setLastHurtMob(entity);
            }
        }
        boolean isEnderman = entity.getType() == EntityType.ENDERMAN;
        int targetFireTicks = entity.getRemainingFireTicks();

        if (isOnFire() && !isEnderman) {
            entity.setSecondsOnFire(5);
        }

        if (entity.hurt(damagesource, (float)i)) {
            if (isEnderman) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;

                if (!level.isClientSide) {
                    livingentity.setArrowCount(livingentity.getArrowCount() + 1);
                }

                if (knockback > 0) {
                    Vector3d vector3d = getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)knockback * 0.6D);

                    if (vector3d.lengthSqr() > 0.0D) {
                        livingentity.push(vector3d.x, 0.1D, vector3d.z);
                    }
                }

                if (!level.isClientSide && owner instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity, owner);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)owner, livingentity);
                }
                this.doPostHurtEffects(livingentity);

                if (owner != null && livingentity != owner && livingentity instanceof PlayerEntity && owner instanceof ServerPlayerEntity && !isSilent()) {
                    ((ServerPlayerEntity)owner).connection.send(new SChangeGameStatePacket(SChangeGameStatePacket.ARROW_HIT_PLAYER, 0.0F));
                }
            }
            playSound(soundEvent, 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));
            remove();
        }
        else {
            entity.setRemainingFireTicks(targetFireTicks);
            setDeltaMovement(getDeltaMovement().scale(-0.1D));
            yRot += 180.0F;
            yRotO += 180.0F;

            if (!level.isClientSide && getDeltaMovement().lengthSqr() < 1.0E-7D) {
                if (pickup == PickupStatus.ALLOWED) {
                    spawnAtLocation(getPickupItem(), 0.1F);
                }
                remove();
            }
        }
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult traceResult) {
        lastState = level.getBlockState(traceResult.getBlockPos());
        super.onHitBlock(traceResult);
        Vector3d vector3d = traceResult.getLocation().subtract(getX(), getY(), getZ());
        setDeltaMovement(vector3d);
        Vector3d vector3d1 = vector3d.normalize().scale(0.05F);
        setPosRaw(getX() - vector3d1.x, getY() - vector3d1.y, getZ() - vector3d1.z);
        playSound(getHitGroundSoundEvent(), 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));
        inGround = true;
        shakeTime = 7;
        setSoundEvent(SoundEvents.ARROW_HIT);
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.ARROW_HIT;
    }

    protected final SoundEvent getHitGroundSoundEvent() {
        return this.soundEvent;
    }

    protected void doPostHurtEffects(LivingEntity livingEntity) {
    }

    @Nullable
    protected EntityRayTraceResult findHitEntity(Vector3d pos, Vector3d nextPos) {
        return ProjectileHelper.getEntityHitResult(this.level, this, pos, nextPos, getBoundingBox().expandTowards(getDeltaMovement()).inflate(1.0D), this::canHitEntity);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compoundNBT) {
        super.addAdditionalSaveData(compoundNBT);
        compoundNBT.putShort("life", (short) life);

        if (lastState != null) {
            compoundNBT.put("inBlockState", AQNBTUtil.writeBlockState(lastState));
        }

        compoundNBT.putByte("shake", (byte)shakeTime);
        compoundNBT.putBoolean("inGround", inGround);
        compoundNBT.putByte("pickup", (byte)pickup.ordinal());
        compoundNBT.putDouble("damage", baseDamage);
        compoundNBT.putString("SoundEvent", ForgeRegistries.SOUND_EVENTS.getKey(soundEvent).toString());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compoundNBT) {
        super.readAdditionalSaveData(compoundNBT);
        life = compoundNBT.getShort("life");

        if (compoundNBT.contains("inBlockState", Constants.NBT.TAG_COMPOUND)) {
            lastState = NBTUtil.readBlockState(compoundNBT.getCompound("inBlockState"));
        }

        shakeTime = compoundNBT.getByte("shake") & 255;
        inGround = compoundNBT.getBoolean("inGround");

        if (compoundNBT.contains("damage", Constants.NBT.TAG_ANY_NUMERIC)) {
            baseDamage = compoundNBT.getDouble("damage");
        }

        if (compoundNBT.contains("pickup", Constants.NBT.TAG_ANY_NUMERIC)) {
            pickup = PickupStatus.byOrdinal(compoundNBT.getByte("pickup"));
        }
        else if (compoundNBT.contains("player", Constants.NBT.TAG_ANY_NUMERIC)) {
            pickup = compoundNBT.getBoolean("player")
                    ? PickupStatus.ALLOWED
                    : PickupStatus.DISALLOWED;
        }

        if (compoundNBT.contains("SoundEvent", Constants.NBT.TAG_STRING)) {
            ResourceLocation soundLocation = new ResourceLocation(compoundNBT.getString("SoundEvent"));

            if (ForgeRegistries.SOUND_EVENTS.containsKey(soundLocation)) {
                soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(soundLocation);
            }
            else {
                soundEvent = getDefaultHitGroundSoundEvent();
            }
        }
    }

    @Override
    public void setOwner(@Nullable Entity entity) {
        super.setOwner(entity);

        if (entity instanceof PlayerEntity) {
            pickup = ((PlayerEntity)entity).abilities.instabuild
                    ? PickupStatus.CREATIVE_ONLY
                    : PickupStatus.ALLOWED;
        }
    }

    @Override
    public void playerTouch(PlayerEntity player) {
        if (!level.isClientSide && (inGround || isNoPhysics()) && shakeTime <= 0) {
            boolean flag = pickup == PickupStatus.ALLOWED || pickup == PickupStatus.CREATIVE_ONLY && player.abilities.instabuild || isNoPhysics() && getOwner().getUUID() == player.getUUID();

            if (pickup == PickupStatus.ALLOWED && !player.inventory.add(getPickupItem())) {
                flag = false;
            }

            if (flag) {
                player.take(this, 1);
                remove();
            }
        }
    }

    public ItemStack getPickupItem() {
        return null;
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public void setKnockback(int knockback) {
        this.knockback = knockback;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected float getEyeHeight(Pose pose, EntitySize size) {
        return 0.13F;
    }

    private void setFlag(int flag, boolean value) {
        byte b0 = this.entityData.get(ID_FLAGS);

        if (value) {
            this.entityData.set(ID_FLAGS, (byte)(b0 | flag));
        }
        else {
            this.entityData.set(ID_FLAGS, (byte)(b0 & ~flag));
        }
    }

    protected float getWaterInertia() {
        return 0.6F;
    }

    public void setNoPhysics(boolean noPhysics) {
        this.noPhysics = noPhysics;
        this.setFlag(2, noPhysics);
    }

    public boolean isNoPhysics() {
        if (!this.level.isClientSide) {
            return this.noPhysics;
        }
        else {
            return (this.entityData.get(ID_FLAGS) & 2) != 0;
        }
    }

    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum PickupStatus {
        DISALLOWED,
        ALLOWED,
        CREATIVE_ONLY;

        public static PickupStatus byOrdinal(int ordinal) {
            if (ordinal < 0 || ordinal > values().length) {
                ordinal = 0;
            }

            return values()[ordinal];
        }
    }
}
