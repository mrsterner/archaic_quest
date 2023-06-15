package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.properties.EnumProperty;

import static com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock.BlockType;

public class AztecDungeonDoorBlockItem extends BlockItem {

    private static final EnumProperty<BlockType> typeProperty = AztecDungeonDoorBlock.BLOCK_TYPE;

    public AztecDungeonDoorBlockItem(Block block) {
        super(block, new Item.Properties().tab(AQCreativeTabs.DECORATION));
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext context, BlockState blockState) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        World world = context.getWorld();
        BlockPos pos = context.getClickedPos();

        switch (direction) {
            case SOUTH, NORTH -> {
                world.setBlock(pos.west(), blockState.setValue(typeProperty, BlockType.LOWER_LEFT), 2);
                world.setBlock(pos.east(), blockState.setValue(typeProperty, BlockType.LOWER_RIGHT), 2);
                world.setBlock(pos.above(), blockState.setValue(typeProperty, BlockType.MIDDLE), 2);
                world.setBlock(pos.above().west(), blockState.setValue(typeProperty, BlockType.LEFT), 2);
                world.setBlock(pos.above().east(), blockState.setValue(typeProperty, BlockType.RIGHT), 2);
                world.setBlock(pos.above(2), blockState.setValue(typeProperty, BlockType.TOP), 2);
                world.setBlock(pos.above(2).west(), blockState.setValue(typeProperty, BlockType.LEFT_TOP), 2);
                world.setBlock(pos.above(2).east(), blockState.setValue(typeProperty, BlockType.RIGHT_TOP), 2);
            }
            case EAST, WEST -> {
                world.setBlock(pos.south(), blockState.setValue(typeProperty, BlockType.LOWER_LEFT), 2);
                world.setBlock(pos.north(), blockState.setValue(typeProperty, BlockType.LOWER_RIGHT), 2);
                world.setBlock(pos.above(), blockState.setValue(typeProperty, BlockType.MIDDLE), 2);
                world.setBlock(pos.above().south(), blockState.setValue(typeProperty, BlockType.LEFT), 2);
                world.setBlock(pos.above().north(), blockState.setValue(typeProperty, BlockType.RIGHT), 2);
                world.setBlock(pos.above(2), blockState.setValue(typeProperty, BlockType.TOP), 2);
                world.setBlock(pos.above(2).south(), blockState.setValue(typeProperty, BlockType.LEFT_TOP), 2);
                world.setBlock(pos.above(2).north(), blockState.setValue(typeProperty, BlockType.RIGHT_TOP), 2);
            }
        }

        return super.placeBlock(context, blockState);
    }
}
