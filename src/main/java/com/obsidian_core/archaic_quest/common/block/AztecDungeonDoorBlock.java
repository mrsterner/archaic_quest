package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.data.DungeonDoorType;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import com.obsidian_core.archaic_quest.registry.AQObjects;
import com.obsidian_core.archaic_quest.registry.AQSoundEvents;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity.DoorState;

public class AztecDungeonDoorBlock extends Block implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<BlockType> BLOCK_TPOSITIVE_YE = EnumProperty.of("block_type", BlockType.class);
    public static final BooleanProperty IS_OPEN = BooleanProperty.of("open");

    private static final Map<BlockType, VoxelShape[]> shapes = new HashMap<>();

    // HitcreateCuboidShape hell incoming
    //
    // Order:
    // 0 = North & West (closed door)
    // 1 = West & East (closed door)
    // 2 = North & West (open door)
    // 3 = West & East (open door)
    static {
        shapes.put(BlockType.MASTER, new VoxelShape[] {
                Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D),
                Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D),
                VoxelShapes.empty(),
                VoxelShapes.empty()
        });
        shapes.put(BlockType.LOWER_RIGHT, new VoxelShape[] {
                VoxelShapes.union(
                        Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D),
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D)
                ),
        });
        shapes.put(BlockType.LOWER_LEFT, new VoxelShape[] {
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.MIDDLE, new VoxelShape[] {
                Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D),
                Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D),
                VoxelShapes.empty(),
                VoxelShapes.empty()
        });
        shapes.put(BlockType.RIGHT, new VoxelShape[] {
                VoxelShapes.union(
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D),
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D)
                )
        });
        shapes.put(BlockType.LEFT, new VoxelShape[] {
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.TOP, new VoxelShape[] {
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 10.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 8.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.LEFT_TOP, new VoxelShape[] {
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 10.0D, 8.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 10.0D, 8.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.RIGHT_TOP, new VoxelShape[] {
                VoxelShapes.union(
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 10.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(6.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 10.0D),
                        Block.createCuboidShape(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
    }

    private final DungeonDoorType doorType;


    public AztecDungeonDoorBlock(DungeonDoorType doorType) {
        super(Settings.copy(AQObjects.ANDESITE_AZTEC_BRICKS_0));
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(BLOCK_TPOSITIVE_YE, BlockType.MASTER).with(IS_OPEN, false));
        this.doorType = doorType;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        BlockType type = state.get(BLOCK_TPOSITIVE_YE);
        Direction facing = state.get(FACING);
        boolean isOpen = state.get(IS_OPEN);

        if (doorType.isFrame()) {
            return switch (facing) {
                case NORTH, SOUTH -> shapes.get(type)[2];
                default -> shapes.get(type)[3];
            };
        }

        return switch (facing) {
            case NORTH, SOUTH -> isOpen ? shapes.get(type)[2] : shapes.get(type)[0];
            default -> isOpen ? shapes.get(type)[3] : shapes.get(type)[1];
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean flag) {
        if (state.isOf(this) && !newState.isOf(this)) {
            BlockPos masterPos = calculateMasterPos(state, pos);

            if (masterPos != null) {
                BlockState masterState = isMaster(state) ? state : world.getBlockState(masterPos);

                if (masterState.isOf(this)) {
                    Direction dir = masterState.get(FACING);

                    if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                        for (BlockPos p : BlockPos.iterate(masterPos.west(), masterPos.east().up(2))) {
                            world.breakBlock(p, false);
                        }
                    } else {
                        for (BlockPos p : BlockPos.iterate(masterPos.south(), masterPos.north().up(2))) {
                            world.breakBlock(p, false);
                        }
                    }
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, flag);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.BLOCK;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean flag) {
        if (doorType.isFrame()) {
            super.neighborUpdate(state, world, pos, block, neighborPos, flag);
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

        if (isMaster(world.getBlockState(masterPos)) && !world.isClient()) {
            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = pos.offset(direction);

                if (world.isReceivingRedstonePower(offsetPos)) {
                    BlockEntity be = world.getBlockEntity(masterPos);

                    if (be instanceof AztecDungeonDoorBlockEntity dungeonDoor) {
                        if (dungeonDoor.isOpen()) {
                            dungeonDoor.setDoorState(DoorState.CLOSING);
                            dungeonDoor.sendDoorStateUpdate();
                            world.playSound(null, masterPos, AQSoundEvents.AZTEC_DOOR_CLOSING, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        }
                        else if (dungeonDoor.isClosed()) {
                            dungeonDoor.setDoorState(DoorState.OPENING);
                            dungeonDoor.sendDoorStateUpdate();
                            world.playSound(null, masterPos, AQSoundEvents.AZTEC_DOOR_OPENING, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        }
                    }
                    break;
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
        if (!fromState.isOf(this)) return null;

        switch (fromState.get(FACING)) {
            case NORTH, SOUTH -> {
                return switch (fromState.get(BLOCK_TPOSITIVE_YE)) {
                    case TOP -> fromPos.down(2);
                    case LEFT_TOP -> fromPos.down(2).east();
                    case RIGHT_TOP -> fromPos.down(2).west();
                    case MIDDLE -> fromPos.down();
                    case LEFT -> fromPos.down().east();
                    case RIGHT -> fromPos.down().west();
                    case LOWER_LEFT -> fromPos.east();
                    case LOWER_RIGHT -> fromPos.west();
                    case MASTER -> fromPos;
                };
            }
            case EAST, WEST -> {
                return switch (fromState.get(BLOCK_TPOSITIVE_YE)) {
                    case TOP -> fromPos.down(2);
                    case LEFT_TOP -> fromPos.down(2).north();
                    case RIGHT_TOP -> fromPos.down(2).south();
                    case MIDDLE -> fromPos.down();
                    case LEFT -> fromPos.down().north();
                    case RIGHT -> fromPos.down().south();
                    case LOWER_LEFT -> fromPos.north();
                    case LOWER_RIGHT -> fromPos.south();
                    case MASTER -> fromPos;
                };
            }
        }
        return null;
    }

    private boolean isMaster(BlockState state) {
        return state.isOf(this) && state.get(BLOCK_TPOSITIVE_YE) == BlockType.MASTER;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.isOf(this) && isMaster(state)
                ? new AztecDungeonDoorBlockEntity(pos, state)
                : null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Direction direction = context.getPlayerFacing().getOpposite();

        boolean foundSpace = true;

        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            for (BlockPos blockPos : BlockPos.iterate(pos.east(), pos.west().up(2))) {
                if (!world.getBlockState(blockPos).canReplace(context)) {
                    foundSpace = false;
                    break;
                }
            }
        }
        else if (direction == Direction.WEST || direction == Direction.EAST) {
            for (BlockPos blockPos : BlockPos.iterate(pos.south(), pos.north().up(2))) {
                if (!world.getBlockState(blockPos).canReplace(context)) {
                    foundSpace = false;
                    break;
                }
            }
        }
        return foundSpace ? getDefaultState().with(FACING, direction) : null;
    }

    public DungeonDoorType getDoorType() {
        return doorType;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, BLOCK_TPOSITIVE_YE, IS_OPEN);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return (lvl, pos, state, blockEntity) -> AztecDungeonDoorBlockEntity.tick(lvl, pos, state, (AztecDungeonDoorBlockEntity) blockEntity);
    }


    public enum BlockType implements StringIdentifiable {
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
        public String asString() {
            return name;
        }
    }
}
