package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.HorizontalDirectionalBlock;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;

public class SimpleHorizontalBlock extends HorizontalDirectionalBlock {

    public SimpleHorizontalBlock(Settings properties) {
        super(properties);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext useContext) {
        return this.getDefaultState().with(FACING, useContext.getPlayerFacing());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }
}
