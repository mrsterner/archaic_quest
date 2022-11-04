package com.obsidian_core.archaic_quest.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.Direction;

public class CraftingStationBlockItem extends BlockItem {

    public CraftingStationBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        context.getLevel().setBlock(context.getClickedPos().above(), Blocks.AIR.defaultBlockState(), 27);
        Direction dir = context.getHorizontalDirection();
        return super.placeBlock(context, state);
    }
}
