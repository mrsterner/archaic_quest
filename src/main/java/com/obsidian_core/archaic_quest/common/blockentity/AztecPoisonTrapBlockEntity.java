package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.block.AztecPoisonTrapBlock;
import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import com.obsidian_core.archaic_quest.common.core.register.AQSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class AztecPoisonTrapBlockEntity extends BlockEntity {

    /** The collision box up the trap to check for players to damage, when trap is active. */
    private Box effectBox = null;
    /** Timer. Next time until we damage/affect whatever entity is inside the collision box when active. */
    private final int maxTimeNextCollisionTick = 8;
    private int timeNextCollisionTick = 8;

    public AztecPoisonTrapBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.POISON_TRAP, pos, state);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        effectBox = new Box(getPos().up()).expand(3.0D, 1.0D, 3.0D);
    }

    public static void tick(World world, BlockPos pos, BlockState state, AztecPoisonTrapBlockEntity trap) {
        // Update collision box
        if (--trap.timeNextCollisionTick <= 0) {
            trap.timeNextCollisionTick = trap.maxTimeNextCollisionTick;
            List<LivingEntity> entities = world.getNonSpectatingEntities(LivingEntity.class, trap.effectBox);

            boolean beActive = world.isReceivingRedstonePower(pos);

            if (!entities.isEmpty() && world.getBlockState(pos.up()).isAir()) {
                for (LivingEntity entity : entities) {
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20 * 10));
                }
                beActive = true;
            }

            if (!world.isClient()) {
                if (beActive && !state.get(AztecPoisonTrapBlock.ACTIVE)) {
                    world.setBlockState(pos, state.with(AztecPoisonTrapBlock.ACTIVE, true));
                    world.playSound(null, pos, AQSoundEvents.POISON_TRAP_ACTIVATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                } else if (!beActive && state.get(AztecPoisonTrapBlock.ACTIVE)) {
                    world.setBlockState(pos, state.with(AztecPoisonTrapBlock.ACTIVE, false));
                }
            }
        }
    }
}
