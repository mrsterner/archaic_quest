package com.obsidian_core.archaic_quest.common.block;


import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.DirectionalBlock;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;

public class SimpleDirectionalBlock extends DirectionalBlock {

    public SimpleDirectionalBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext useContext) {
        return this.defaultBlockState().setValue(FACING, useContext.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }
}
