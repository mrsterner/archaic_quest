package com.obsidian_core.archaic_quest.common.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AztecWoodPillarBaseBlock extends AztecWoodPillarBlock {

    private static final VoxelShape SHAPE =
            Shapes.or(
                    Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
                    Block.box(4.0D, 0.0D, 4.0D, 12.0D, 5.0D, 12.0D),
                    Block.box(3.0D, 5.0D, 3.0D, 13.0D, 7.0D, 13.0D)
            );

    public AztecWoodPillarBaseBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState belowState = world.getBlockState(pos.below());
        return !(belowState.getBlock() instanceof AztecWoodPillarBlock);
    }
}
