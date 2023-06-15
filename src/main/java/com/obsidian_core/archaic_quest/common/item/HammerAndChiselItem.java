package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.block.ChiselPillarBlock;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class HammerAndChiselItem extends Item {

    public HammerAndChiselItem() {
        super(new Item.Properties().tab(AQCreativeTabs.TOOLS).stacksTo(1).defaultDurability(150));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getClickedPos();
        BlockState clickedState = world.getBlockState(pos);

        if (clickedState.is(AQBlocks.AZTEC_PILLAR.get())) {
            ChiselPillarBlock.Type pillarType = clickedState.getValue(ChiselPillarBlock.PILLAR_TYPE);

            if (pillarType.canBeChiseled()) {
                PlayerEntity player = context.getPlayer();

                if (player != null) {
                    world.setBlock(pos, clickedState.setValue(ChiselPillarBlock.PILLAR_TYPE, ChiselPillarBlock.Type.chiselCycle(player.isShiftKeyDown(), pillarType)), 3);
                }
                else {
                    world.setBlock(pos, clickedState.setValue(ChiselPillarBlock.PILLAR_TYPE, ChiselPillarBlock.Type.chiselCycle(false, pillarType)), 3);
                }

                if (world instanceof ServerWorld serverWorld) {
                    if (player != null && !player.isCreative()) {
                        context.getItemInHand().hurtAndBreak(1, player, (serverPlayer) -> serverPlayer.broadcastBreakEvent(context.getHand()));
                    }
                    Vec3d vec = context.getClickLocation();
                    serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, clickedState), vec.x, vec.y, vec.z, 5,0.0D, 0.0D, 0.0D, 0.05D);
                    serverWorld.playSound(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.NETHER_BRICKS_BREAK, SoundSource.BLOCKS, 0.9F, 1.0F);
                }
            }
        }
        return InteractionResult.PASS;
    }
}
