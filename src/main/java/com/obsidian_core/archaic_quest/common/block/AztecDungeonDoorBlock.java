package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.data.DungeonDoorType;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
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

import static com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity.DoorState;

public class AztecDungeonDoorBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<BlockType> BLOCK_TYPE = EnumProperty.create("block_type", BlockType.class);
    public static final BooleanProperty IS_OPEN = BooleanProperty.create("open");

    private static final Map<BlockType, VoxelShape[]> shapes = new HashMap<>();




    // Hitbox hell incoming
    //
    // Order:
    // 0 = North & West (closed door)
    // 1 = West & East (closed door)
    // 2 = North & West (open door)
    // 3 = West & East (open door)
    static {
        shapes.put(BlockType.MASTER, new VoxelShape[] {
                Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D),
                Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D),
                VoxelShapes.empty(),
                VoxelShapes.empty()
        });
        shapes.put(BlockType.LOWER_RIGHT, new VoxelShape[] {
                VoxelShapes.or(
                        Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D)
                ),
        });
        shapes.put(BlockType.LOWER_LEFT, new VoxelShape[] {
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.MIDDLE, new VoxelShape[] {
                Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D),
                Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D),
                VoxelShapes.empty(),
                VoxelShapes.empty()
        });
        shapes.put(BlockType.RIGHT, new VoxelShape[] {
                VoxelShapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D)
                )
        });
        shapes.put(BlockType.LEFT, new VoxelShape[] {
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.TOP, new VoxelShape[] {
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 10.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.LEFT_TOP, new VoxelShape[] {
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 10.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 10.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.RIGHT_TOP, new VoxelShape[] {
                VoxelShapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 10.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 10.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
    }

    private final DungeonDoorType doorType;


    public AztecDungeonDoorBlock(DungeonDoorType doorType) {
        super(AbstractBlock.Properties.copy(AQBlocks.ANDESITE_AZTEC_BRICKS_0.get()).noOcclusion());
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BLOCK_TYPE, BlockType.MASTER).setValue(IS_OPEN, false));
        this.doorType = doorType;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        BlockType type = state.getValue(BLOCK_TYPE);
        Direction facing = state.getValue(FACING);
        boolean isOpen = state.getValue(IS_OPEN);

        if (doorType.isFrame()) {
            switch (facing) {
                case NORTH:
                case SOUTH:
                    return shapes.get(type)[2];
                default:
                    return shapes.get(type)[3];
            }
        }

        switch (facing) {
            case NORTH:
            case SOUTH:
                return isOpen ? shapes.get(type)[2] : shapes.get(type)[0];
            default:
                return isOpen ? shapes.get(type)[3] : shapes.get(type)[1];
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean flag) {
        if (state.is(this) && !newState.is(this)) {
            BlockPos masterPos = calculateMasterPos(state, pos);

            if (masterPos != null) {
                BlockState masterState = isMaster(state) ? state : world.getBlockState(masterPos);

                if (masterState.is(this)) {
                    Direction dir = masterState.getValue(FACING);

                    if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                        for (BlockPos p : BlockPos.betweenClosed(masterPos.west(), masterPos.east().above(2))) {
                            world.destroyBlock(p, false);
                        }
                    }
                    else {
                        for (BlockPos p : BlockPos.betweenClosed(masterPos.south(), masterPos.north().above(2))) {
                            world.destroyBlock(p, false);
                        }
                    }
                }
            }
        }
        super.onRemove(state, world, pos, newState, flag);
    }

    @Override
    @SuppressWarnings("deprecation")
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean flag) {
        if (doorType.isFrame()) {
            super.neighborChanged(state, world, pos, block, neighborPos, flag);
            return;
        }
        BlockPos masterPos;

        if (isMaster(state)) {
            masterPos = pos;
        }
        else {
            masterPos = calculateMasterPos(state, pos);
        }

        if (masterPos == null)
            return;

        if (isMaster(world.getBlockState(masterPos)) && !world.isClientSide) {
            if (world.hasNeighborSignal(pos)) {
                TileEntity te = world.getBlockEntity(masterPos);

                if (te instanceof AztecDungeonDoorTileEntity) {
                    AztecDungeonDoorTileEntity dungeonDoor = (AztecDungeonDoorTileEntity) te;

                    if (dungeonDoor.isOpen()) {
                        dungeonDoor.setDoorState(DoorState.CLOSING);
                        dungeonDoor.sendDoorStateUpdate();
                    }
                    else if (dungeonDoor.isClosed()) {
                        dungeonDoor.setDoorState(DoorState.OPENING);
                        dungeonDoor.sendDoorStateUpdate();
                    }
                }
            }
        }
    }

    /**
     * Helper method for locating the door's master block from
     * the position of a sub-block. Looks ugly, but omits the need
     * of additional tile entities to store the master position in.
     */
    @Nullable
    private BlockPos calculateMasterPos(BlockState fromState, BlockPos fromPos) {
        if (!fromState.is(this)) return null;

        switch (fromState.getValue(FACING)) {
            default:
            case NORTH:
            case SOUTH:
            {
                switch (fromState.getValue(BLOCK_TYPE)) {
                    case TOP: return fromPos.below(2);
                    case LEFT_TOP: return fromPos.below(2).east();
                    case RIGHT_TOP: return fromPos.below(2).west();
                    case MIDDLE: return fromPos.below();
                    case LEFT: return fromPos.below().east();
                    case RIGHT: return fromPos.below().west();
                    case LOWER_LEFT: return fromPos.east();
                    case LOWER_RIGHT: return fromPos.west();
                    case MASTER: return fromPos;
                }
            }
                break;
            case EAST:
            case WEST:
            {
                switch (fromState.getValue(BLOCK_TYPE)) {
                    case TOP: return fromPos.below(2);
                    case LEFT_TOP: return fromPos.below(2).north();
                    case RIGHT_TOP: return fromPos.below(2).south();
                    case MIDDLE: return fromPos.below();
                    case LEFT: return fromPos.below().north();
                    case RIGHT: return fromPos.below().south();
                    case LOWER_LEFT: return fromPos.north();
                    case LOWER_RIGHT: return fromPos.south();
                    case MASTER: return fromPos;
                }
            }
                break;
        }
        return null;
    }

    private boolean isMaster(BlockState state) {
        return state.is(this) && state.getValue(BLOCK_TYPE) == BlockType.MASTER;
    }

    @Nullable
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AztecDungeonDoorTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.is(this) && isMaster(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
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

    public DungeonDoorType getDoorType() {
        return doorType;
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
        stateBuilder.add(FACING, BLOCK_TYPE, IS_OPEN);
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
