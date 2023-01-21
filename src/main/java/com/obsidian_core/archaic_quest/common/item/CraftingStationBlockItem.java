package com.obsidian_core.archaic_quest.common.item;


import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CraftingStationBlockItem extends BlockItem {

    public CraftingStationBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
        context.getLevel().setBlock(context.getClickedPos().above(), Blocks.AIR.defaultBlockState(), 27);
        Direction dir = context.getHorizontalDirection();
        return super.placeBlock(context, state);
    }
}
