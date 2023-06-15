package com.obsidian_core.archaic_quest.common.entity.living;


import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Tlatlaomi extends HostileEntity {

    public Tlatlaomi(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createTlatlaomiAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.28D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5F)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 25.0D);
    }

    @Override
    protected void initGoals() {
        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, false));
        goalSelector.add(2, new WanderAroundFarGoal(this, 0.80D));
        goalSelector.add(3, new LookAtEntityGoal(this, CatEntity.class, 9.0F));
        goalSelector.add(3, new LookAtEntityGoal(this, OcelotEntity.class, 9.0F));
        goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        goalSelector.add(5, new LookAroundGoal(this));
        targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(getStepSound(), 0.15F, 1.0F);
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.90F;
    }

    @Override
    public float getSoundPitch() {
        return super.getSoundPitch() * 0.75F;
    }
}
