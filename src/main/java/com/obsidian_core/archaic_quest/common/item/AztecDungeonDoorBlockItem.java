package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import static com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock.BlockType;

public class AztecDungeonDoorBlockItem extends BlockItem {

    private static final EnumProperty<BlockType> typeProperty = AztecDungeonDoorBlock.BLOCK_TYPE;

    public AztecDungeonDoorBlockItem(Block block) {
        super(block, new Item.Properties().tab(AQCreativeTabs.DECORATION));
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext context, BlockState blockState) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        switch (direction) {
            case SOUTH, NORTH -> {
                level.setBlock(pos.west(), blockState.setValue(typeProperty, BlockType.LOWER_LEFT), 2);
                level.setBlock(pos.east(), blockState.setValue(typeProperty, BlockType.LOWER_RIGHT), 2);
                level.setBlock(pos.above(), blockState.setValue(typeProperty, BlockType.MIDDLE), 2);
                level.setBlock(pos.above().west(), blockState.setValue(typeProperty, BlockType.LEFT), 2);
                level.setBlock(pos.above().east(), blockState.setValue(typeProperty, BlockType.RIGHT), 2);
                level.setBlock(pos.above(2), blockState.setValue(typeProperty, BlockType.TOP), 2);
                level.setBlock(pos.above(2).west(), blockState.setValue(typeProperty, BlockType.LEFT_TOP), 2);
                level.setBlock(pos.above(2).east(), blockState.setValue(typeProperty, BlockType.RIGHT_TOP), 2);
            }
            case EAST, WEST -> {
                level.setBlock(pos.south(), blockState.setValue(typeProperty, BlockType.LOWER_LEFT), 2);
                level.setBlock(pos.north(), blockState.setValue(typeProperty, BlockType.LOWER_RIGHT), 2);
                level.setBlock(pos.above(), blockState.setValue(typeProperty, BlockType.MIDDLE), 2);
                level.setBlock(pos.above().south(), blockState.setValue(typeProperty, BlockType.LEFT), 2);
                level.setBlock(pos.above().north(), blockState.setValue(typeProperty, BlockType.RIGHT), 2);
                level.setBlock(pos.above(2), blockState.setValue(typeProperty, BlockType.TOP), 2);
                level.setBlock(pos.above(2).south(), blockState.setValue(typeProperty, BlockType.LEFT_TOP), 2);
                level.setBlock(pos.above(2).north(), blockState.setValue(typeProperty, BlockType.RIGHT_TOP), 2);
            }
        }

        return super.placeBlock(context, blockState);
    }
}
