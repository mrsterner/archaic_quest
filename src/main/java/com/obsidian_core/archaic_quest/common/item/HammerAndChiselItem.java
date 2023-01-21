package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.block.ChiselPillarBlock;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class HammerAndChiselItem extends Item {

    public HammerAndChiselItem() {
        super(new Item.Properties().tab(AQCreativeTabs.TOOLS).stacksTo(1).defaultDurability(150));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState clickedState = level.getBlockState(pos);

        if (clickedState.is(AQBlocks.AZTEC_PILLAR.get())) {
            ChiselPillarBlock.Type pillarType = clickedState.getValue(ChiselPillarBlock.PILLAR_TYPE);

            if (pillarType.canBeChiseled()) {
                Player player = context.getPlayer();

                if (player != null) {
                    level.setBlock(pos, clickedState.setValue(ChiselPillarBlock.PILLAR_TYPE, ChiselPillarBlock.Type.chiselCycle(player.isShiftKeyDown(), pillarType)), 3);
                }
                else {
                    level.setBlock(pos, clickedState.setValue(ChiselPillarBlock.PILLAR_TYPE, ChiselPillarBlock.Type.chiselCycle(false, pillarType)), 3);
                }

                if (level instanceof ServerLevel serverLevel) {
                    if (player != null && !player.isCreative()) {
                        context.getItemInHand().hurtAndBreak(1, player, (serverPlayer) -> serverPlayer.broadcastBreakEvent(context.getHand()));
                    }
                    Vec3 vec = context.getClickLocation();
                    serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, clickedState), vec.x, vec.y, vec.z, 5,0.0D, 0.0D, 0.0D, 0.05D);
                    serverLevel.playSound(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.NETHER_BRICKS_BREAK, SoundSource.BLOCKS, 0.9F, 1.0F);
                }
            }
        }
        return InteractionResult.PASS;
    }
}
