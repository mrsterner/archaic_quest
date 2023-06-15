package com.obsidian_core.archaic_quest.common.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.BlockGetter;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.SimpleWaterloggedBlock;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.BlockStateProperties;
import net.minecraft.world.world.block.state.properties.BooleanProperty;
import net.minecraft.world.world.material.FluidState;
import net.minecraft.world.world.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AztecWoodPillarBaseBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE =
            Shapes.or(
                    Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
                    Block.box(4.0D, 0.0D, 4.0D, 12.0D, 5.0D, 12.0D),
                    Block.box(3.0D, 5.0D, 3.0D, 13.0D, 7.0D, 13.0D)
            );

    public AztecWoodPillarBaseBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos clickedPos = context.getClickedPos();
        World world = context.getWorld();
        boolean waterlogged = world.getBlockState(clickedPos).getFluidState().is(FluidTags.WATER);

        return this.defaultBlockState().setValue(WATERLOGGED, waterlogged);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATERLOGGED);
    }
}
