package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class AztecDungeonDoorBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<BlockType> BLOCK_TYPE = EnumProperty.create("block_type", BlockType.class);
    public static final BooleanProperty HAS_COLLISION = BooleanProperty.create("has_collision");

    private static final Map<BlockType, VoxelShape[]> shapes = new HashMap<>();

    // Order: NORTH, WEST, SOUTH, EAST
    static {
        shapes.put(BlockType.MASTER, new VoxelShape[] {
                Block.box(8.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D)
        });
        shapes.put(BlockType.MIDDLE, new VoxelShape[] {
                Block.box(8.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D)
        });
        shapes.put(BlockType.TOP, new VoxelShape[] {
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 10.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
    }

    /*
    static {
        shapes.put(BlockType.MASTER, new VoxelShape[] {
                Block.box(8.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D)
        });
        shapes.put(BlockType.MIDDLE, new VoxelShape[] {
                Block.box(8.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D)
        });
        shapes.put(BlockType.TOP, new VoxelShape[] {
                VoxelShapes.or(
                        Block.box(8.0D, 0.0D, 0.0D, 12.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
    }

     */


    public AztecDungeonDoorBlock() {
        super(AbstractBlock.Properties.copy(AQBlocks.ANDESITE_AZTEC_BRICKS_0.get()).noOcclusion());
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BLOCK_TYPE, BlockType.MASTER).setValue(HAS_COLLISION, true));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        if (state.getValue(FACING) == Direction.NORTH) {
            return  VoxelShapes.or(
                    Block.box(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 10.0D),
                    Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
            );
        }
        return box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    @Nullable
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AztecDungeonDoorTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.getValue(BLOCK_TYPE) == BlockType.MASTER;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection().getOpposite();

        boolean foundSpace = true;

        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.east(), pos.west().above(2))) {
                if (!world.getBlockState(blockPos).canBeReplaced(context)) {
                    foundSpace = false;
                    break;
                }
            }
        }
        else if (direction == Direction.WEST || direction == Direction.EAST) {
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.south(), pos.north().above(2))) {
                if (!world.getBlockState(blockPos).canBeReplaced(context)) {
                    foundSpace = false;
                    break;
                }
            }
        }
        return foundSpace ? defaultBlockState().setValue(FACING, direction) : null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, BLOCK_TYPE, HAS_COLLISION);
    }

    public enum BlockType implements IStringSerializable {
        MASTER("master"),
        MIDDLE("middle"),
        TOP("top"),
        LEFT_TOP("left_top"),
        RIGHT_TOP("right_top"),
        LEFT("left"),
        RIGHT("right"),
        LOWER_LEFT("lower_left"),
        LOWER_RIGHT("lower_right");

        BlockType(String name) {
            this.name = name;
        }

        private final String name;

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
