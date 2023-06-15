package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import static com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock.BlockType;

public class AztecDungeonDoorBlockItem extends BlockItem {

    private static final EnumProperty<BlockType> typeProperty = AztecDungeonDoorBlock.BLOCK_TPOSITIVE_YE;

    public AztecDungeonDoorBlockItem(Block block) {
        super(block, new Settings().group(AQCreativeTabs.DECORATION));
    }

    @Override
    protected boolean place(ItemPlacementContext context, BlockState blockState) {
        Direction direction = context.getPlayerLookDirection().getOpposite();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        switch (direction) {
            case SOUTH, NORTH -> {
                world.setBlockState(pos.west(), blockState.with(typeProperty, BlockType.LOWER_LEFT), 2);
                world.setBlockState(pos.east(), blockState.with(typeProperty, BlockType.LOWER_RIGHT), 2);
                world.setBlockState(pos.up(), blockState.with(typeProperty, BlockType.MIDDLE), 2);
                world.setBlockState(pos.up().west(), blockState.with(typeProperty, BlockType.LEFT), 2);
                world.setBlockState(pos.up().east(), blockState.with(typeProperty, BlockType.RIGHT), 2);
                world.setBlockState(pos.up(2), blockState.with(typeProperty, BlockType.TOP), 2);
                world.setBlockState(pos.up(2).west(), blockState.with(typeProperty, BlockType.LEFT_TOP), 2);
                world.setBlockState(pos.up(2).east(), blockState.with(typeProperty, BlockType.RIGHT_TOP), 2);
            }
            case EAST, WEST -> {
                world.setBlockState(pos.south(), blockState.with(typeProperty, BlockType.LOWER_LEFT), 2);
                world.setBlockState(pos.north(), blockState.with(typeProperty, BlockType.LOWER_RIGHT), 2);
                world.setBlockState(pos.up(), blockState.with(typeProperty, BlockType.MIDDLE), 2);
                world.setBlockState(pos.up().south(), blockState.with(typeProperty, BlockType.LEFT), 2);
                world.setBlockState(pos.up().north(), blockState.with(typeProperty, BlockType.RIGHT), 2);
                world.setBlockState(pos.up(2), blockState.with(typeProperty, BlockType.TOP), 2);
                world.setBlockState(pos.up(2).south(), blockState.with(typeProperty, BlockType.LEFT_TOP), 2);
                world.setBlockState(pos.up(2).north(), blockState.with(typeProperty, BlockType.RIGHT_TOP), 2);
            }
        }

        return super.place(context, blockState);
    }
}
