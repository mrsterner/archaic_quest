package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.block.ChiselPillarBlock;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HammerAndChiselItem extends Item {

    public HammerAndChiselItem() {
        super(new Item.Settings().group(AQCreativeTabs.TOOLS).stacksTo(1).defaultDurability(150));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState clickedState = world.getBlockState(pos);

        if (clickedState.isOf(AQBlocks.AZTEC_PILLAR)) {
            ChiselPillarBlock.Type pillarType = clickedState.get(ChiselPillarBlock.PILLAR_TPOSITIVE_YE);

            if (pillarType.canBeChiseled()) {
                PlayerEntity player = context.getPlayer();

                if (player != null) {
                    world.setBlockState(pos, clickedState.with(ChiselPillarBlock.PILLAR_TPOSITIVE_YE, ChiselPillarBlock.Type.chiselCycle(player.isSneaking(), pillarType)), 3);
                }
                else {
                    world.setBlockState(pos, clickedState.with(ChiselPillarBlock.PILLAR_TPOSITIVE_YE, ChiselPillarBlock.Type.chiselCycle(false, pillarType)), 3);
                }

                if (world instanceof ServerWorld serverWorld) {
                    if (player != null && !player.isCreative()) {
                        context.getStack().damage(1, player, (serverPlayer) -> serverPlayer.sendToolBreakStatus(context.getHand()));
                    }
                    Vec3d vec = context.getHitPos();
                    serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, clickedState), vec.x, vec.y, vec.z, 5,0.0D, 0.0D, 0.0D, 0.05D);
                    serverWorld.playSound(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.BLOCK_NETHER_BRICKS_BREAK, SoundCategory.BLOCKS, 0.9F, 1.0F);
                }
            }
        }
        return ActionResult.PASS;
    }
}
