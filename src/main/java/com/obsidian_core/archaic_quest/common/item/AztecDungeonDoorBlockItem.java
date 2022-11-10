package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock.BlockType;

public class AztecDungeonDoorBlockItem extends BlockItem {

    private static final DirectionProperty dirProperty = AztecDungeonDoorBlock.FACING;
    private static final EnumProperty<BlockType> typeProperty = AztecDungeonDoorBlock.BLOCK_TYPE;

    public AztecDungeonDoorBlockItem(Block block) {
        super(block, new Item.Properties().tab(AQCreativeTabs.DECORATION));
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState blockState) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();

        switch (direction) {
            default:
            case NORTH:
                world.setBlock(pos.west(), blockState.setValue(typeProperty, BlockType.LOWER_LEFT), 2);
                world.setBlock(pos.east(), blockState.setValue(typeProperty, BlockType.LOWER_RIGHT), 2);
                world.setBlock(pos.above(), blockState.setValue(typeProperty, BlockType.MIDDLE), 2);
                world.setBlock(pos.above().west(), blockState.setValue(typeProperty, BlockType.LEFT), 2);
                world.setBlock(pos.above().east(), blockState.setValue(typeProperty, BlockType.RIGHT), 2);
                world.setBlock(pos.above(2), blockState.setValue(typeProperty, BlockType.TOP), 2);
                world.setBlock(pos.above(2).west(), blockState.setValue(typeProperty, BlockType.LEFT_TOP), 2);
                world.setBlock(pos.above(2).east(), blockState.setValue(typeProperty, BlockType.RIGHT), 2);


                break;
            case WEST:
                break;
            case SOUTH:
                break;
            case EAST:
                break;
        }

        return super.placeBlock(context, blockState);
    }
}
