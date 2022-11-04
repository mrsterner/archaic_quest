package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class AztecWoodPillarBaseBlock extends AztecWoodPillarBlock {

    private static final VoxelShape SHAPE =
            VoxelShapes.or(
                    Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
                    Block.box(4.0D, 0.0D, 4.0D, 12.0D, 5.0D, 12.0D),
                    Block.box(3.0D, 5.0D, 3.0D, 13.0D, 7.0D, 13.0D)
            );

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
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        BlockState belowState = world.getBlockState(pos.below());
        return !(belowState.getBlock() instanceof AztecWoodPillarBlock);
    }
}
