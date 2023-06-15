package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.entity.BlockEntity;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.material.FluidState;

import javax.annotation.Nullable;

public class InfestedVaseBlock extends CeramicVaseBlock {

    public InfestedVaseBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
        if (!player.isCreative()) {

            Silverfish silverfish = EntityType.SILVERFISH.create(world);

            if (silverfish != null) {
                silverfish.moveTo((double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, 0.0F, 0.0F);
                world.addFreshEntity(silverfish);
                silverfish.spawnAnim();
            }
        }
        return super.onDestroyedByPlayer(state, world, pos, player, willHarvest, fluid);
    }
}
