package com.obsidian_core.archaic_quest.common.entity.living;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Tlatlaomi extends Monster {

    public Tlatlaomi(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createTlatlaomiAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5F)
                .add(Attributes.MAX_HEALTH, 25.0D);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.80D));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Cat.class, 9.0F));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Ocelot.class, 9.0F));
        goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(getStepSound(), 0.15F, 1.0F);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.90F;
    }

    @Override
    public float getVoicePitch() {
        return super.getVoicePitch() * 0.75F;
    }
}
