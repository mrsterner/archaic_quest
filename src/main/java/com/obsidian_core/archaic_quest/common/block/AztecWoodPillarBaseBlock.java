package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class AztecWoodPillarBaseBlock extends AztecWoodPillarBlock {

    public AztecWoodPillarBaseBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canSurvive(world, pos)) {
            world.getBlockTicks().scheduleTick(pos, this, 1);
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        BlockState belowState = world.getBlockState(pos.below());

        return !(belowState.getBlock() instanceof AztecWoodPillarBlock);
    }
}
