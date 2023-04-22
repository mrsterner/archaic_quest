package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.block.AztecPoisonTrapBlock;
import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import com.obsidian_core.archaic_quest.common.core.register.AQSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class AztecPoisonTrapBlockEntity extends BlockEntity {

    /** The collision box above the trap to check for players to damage, when trap is active. */
    private AABB effectBox = null;
    /** Timer. Next time until we damage/affect whatever entity is inside the collision box when active. */
    private final int maxTimeNextCollisionTick = 8;
    private int timeNextCollisionTick = 8;

    public AztecPoisonTrapBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.POISON_TRAP.get(), pos, state);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        effectBox = new AABB(getBlockPos().above()).inflate(3.0D, 1.0D, 3.0D);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AztecPoisonTrapBlockEntity trap) {
        // Update collision box
        if (--trap.timeNextCollisionTick <= 0) {
            trap.timeNextCollisionTick = trap.maxTimeNextCollisionTick;
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, trap.effectBox);

            boolean beActive = level.hasNeighborSignal(pos);

            if (!entities.isEmpty() && level.getBlockState(pos.above()).isAir()) {
                for (LivingEntity entity : entities) {
                    entity.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 10));
                }
                beActive = true;
            }

            if (beActive && !state.getValue(AztecPoisonTrapBlock.ACTIVE)) {
                level.setBlockAndUpdate(pos, state.setValue(AztecPoisonTrapBlock.ACTIVE, true));
                level.playSound(null, pos, AQSoundEvents.POISON_TRAP_ACTIVATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            else if (!beActive && state.getValue(AztecPoisonTrapBlock.ACTIVE)) {
                level.setBlockAndUpdate(pos, state.setValue(AztecPoisonTrapBlock.ACTIVE, false));
            }
        }
    }
}
