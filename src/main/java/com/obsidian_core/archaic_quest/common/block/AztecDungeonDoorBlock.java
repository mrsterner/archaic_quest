package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.data.DungeonDoorType;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.BlockGetter;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.*;
import net.minecraft.world.world.block.entity.BlockEntity;
import net.minecraft.world.world.block.entity.BlockEntityTicker;
import net.minecraft.world.world.block.entity.BlockEntityType;
import net.minecraft.world.world.block.state.BlockBehaviour;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.BlockStateProperties;
import net.minecraft.world.world.block.state.properties.BooleanProperty;
import net.minecraft.world.world.block.state.properties.DirectionProperty;
import net.minecraft.world.world.block.state.properties.EnumProperty;
import net.minecraft.world.world.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

import static com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity.DoorState;

public class AztecDungeonDoorBlock extends Block implements EntityBlock {

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
                Shapes.empty(),
                Shapes.empty()
        });
        shapes.put(BlockType.LOWER_RIGHT, new VoxelShape[] {
                Shapes.or(
                        Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D)
                ),
        });
        shapes.put(BlockType.LOWER_LEFT, new VoxelShape[] {
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.MIDDLE, new VoxelShape[] {
                Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D),
                Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D),
                Shapes.empty(),
                Shapes.empty()
        });
        shapes.put(BlockType.RIGHT, new VoxelShape[] {
                Shapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D)
                )
        });
        shapes.put(BlockType.LEFT, new VoxelShape[] {
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.TOP, new VoxelShape[] {
                Shapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 10.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.LEFT_TOP, new VoxelShape[] {
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 10.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 10.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
        shapes.put(BlockType.RIGHT_TOP, new VoxelShape[] {
                Shapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 10.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(6.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                Shapes.or(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 10.0D),
                        Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                )
        });
    }

    private final DungeonDoorType doorType;


    public AztecDungeonDoorBlock(DungeonDoorType doorType) {
        super(BlockBehaviour.Properties.copy(AQBlocks.ANDESITE_AZTEC_BRICKS_0.get()));
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BLOCK_TYPE, BlockType.MASTER).setValue(IS_OPEN, false));
        this.doorType = doorType;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        BlockType type = state.getValue(BLOCK_TYPE);
        Direction facing = state.getValue(FACING);
        boolean isOpen = state.getValue(IS_OPEN);

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
                    } else {
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
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
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

        if (isMaster(world.getBlockState(masterPos)) && !world.isClient()) {
            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = pos.relative(direction);

                if (world.hasNeighborSignal(offsetPos)) {
                    BlockEntity be = world.getExistingBlockEntity(masterPos);

                    if (be instanceof AztecDungeonDoorBlockEntity dungeonDoor) {
                        if (dungeonDoor.isOpen()) {
                            dungeonDoor.setDoorState(DoorState.CLOSING);
                            dungeonDoor.sendDoorStateUpdate();
                            world.playSound(null, masterPos, AQSoundEvents.AZTEC_DOOR_CLOSING.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        }
                        else if (dungeonDoor.isClosed()) {
                            dungeonDoor.setDoorState(DoorState.OPENING);
                            dungeonDoor.sendDoorStateUpdate();
                            world.playSound(null, masterPos, AQSoundEvents.AZTEC_DOOR_OPENING.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
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
        if (!fromState.is(this)) return null;

        switch (fromState.getValue(FACING)) {
            case NORTH, SOUTH -> {
                return switch (fromState.getValue(BLOCK_TYPE)) {
                    case TOP -> fromPos.below(2);
                    case LEFT_TOP -> fromPos.below(2).east();
                    case RIGHT_TOP -> fromPos.below(2).west();
                    case MIDDLE -> fromPos.below();
                    case LEFT -> fromPos.below().east();
                    case RIGHT -> fromPos.below().west();
                    case LOWER_LEFT -> fromPos.east();
                    case LOWER_RIGHT -> fromPos.west();
                    case MASTER -> fromPos;
                };
            }
            case EAST, WEST -> {
                return switch (fromState.getValue(BLOCK_TYPE)) {
                    case TOP -> fromPos.below(2);
                    case LEFT_TOP -> fromPos.below(2).north();
                    case RIGHT_TOP -> fromPos.below(2).south();
                    case MIDDLE -> fromPos.below();
                    case LEFT -> fromPos.below().north();
                    case RIGHT -> fromPos.below().south();
                    case LOWER_LEFT -> fromPos.north();
                    case LOWER_RIGHT -> fromPos.south();
                    case MASTER -> fromPos;
                };
            }
        }
        return null;
    }

    private boolean isMaster(BlockState state) {
        return state.is(this) && state.getValue(BLOCK_TYPE) == BlockType.MASTER;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return state.is(this) && isMaster(state)
                ? new AztecDungeonDoorBlockEntity(pos, state)
                : null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        World world = context.getWorld();
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, BLOCK_TYPE, IS_OPEN);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return (lvl, pos, state, blockEntity) -> AztecDungeonDoorBlockEntity.tick(lvl, pos, state, (AztecDungeonDoorBlockEntity) blockEntity);
    }


    public enum BlockType implements StringRepresentable {
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
