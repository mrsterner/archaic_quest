package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.block.ChiselPillarBlock;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class HammerAndChiselItem extends Item {

    public HammerAndChiselItem() {
        super(new Item.Properties().tab(AQCreativeTabs.TOOLS).stacksTo(1).defaultDurability(150));
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState clickedState = world.getBlockState(pos);

        if (clickedState.is(AQBlocks.AZTEC_PILLAR.get())) {
            ChiselPillarBlock.Type pillarType = clickedState.getValue(ChiselPillarBlock.PILLAR_TYPE);

            if (pillarType.canBeChiseled()) {
                world.setBlock(pos, clickedState.setValue(ChiselPillarBlock.PILLAR_TYPE, ChiselPillarBlock.Type.chiselCycle(pillarType)), 3);

                if (world instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld) world;
                    ServerPlayerEntity player = context.getPlayer() == null ? null : (ServerPlayerEntity) context.getPlayer();

                    if (player != null && !player.isCreative()) {
                        context.getItemInHand().hurtAndBreak(1, player, (serverPlayer) -> {
                            serverPlayer.broadcastBreakEvent(context.getHand());
                        });
                    }
                    Vector3d vec = context.getClickLocation();
                    serverWorld.sendParticles(new BlockParticleData(ParticleTypes.BLOCK, clickedState), vec.x, vec.y, vec.z, 5,0.0D, 0.0D, 0.0D, 0.05D);
                    serverWorld.playSound(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.NETHER_BRICKS_BREAK, SoundCategory.BLOCKS, 0.9F, 1.0F);
                }
            }
        }
        return ActionResultType.PASS;
    }
}
