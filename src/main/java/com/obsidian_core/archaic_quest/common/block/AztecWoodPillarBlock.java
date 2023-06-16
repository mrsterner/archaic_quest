package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.state.AQStateSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class AztecWoodPillarBlock extends Block implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty EXTENDED = AQStateSettings.EXTENDED;
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final BooleanProperty CONNECTED_X = BooleanProperty.of("connected_x");
    public static final BooleanProperty CONNECTED_Z = BooleanProperty.of("connected_z");


    private static final VoxelShape[] shapes = new VoxelShape[] {
            // normal
            Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
            // Z axis
            Block.createCuboidShape(0.0D, 10.0D, 0.0D, 6.0D, 16.0D, 16.0D),
            // X axis
            Block.createCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 10.0D, 6.0D),
            // Connect Z
            VoxelShapes.union(Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
                    Block.createCuboidShape(0.0D, 10.0D, 0.0D, 6.0D, 16.0D, 16.0D)),
            // Connect X
            VoxelShapes.union(Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
                    Block.createCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 10.0D, 6.0D)),
            // Connect XZ
            VoxelShapes.union(Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
                Block.createCuboidShape(0.0D, 10.0D, 0.0D, 6.0D, 16.0D, 16.0D),
                Block.createCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 10.0D, 6.0D))
    };


    private static final VoxelShape SHAPE = Block.createCuboidShape(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);

    public AztecWoodPillarBlock(Settings properties) {
        super(properties);
        this.setDefaultState(getDefaultState()
                .with(WATERLOGGED, false)
                .with(AXIS, Direction.Axis.Y)
                .with(EXTENDED, false)
                .with(CONNECTED_X, false)
                .with(CONNECTED_Z, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        boolean connectedX = state.get(CONNECTED_X);
        boolean connectedZ = state.get(CONNECTED_Z);
        Direction.Axis axis = state.get(AXIS);

        switch (axis) {
            case Z: return shapes[1];
            case X: return shapes[2];
            default: {
                if (connectedX && connectedZ)
                    return shapes[5];
                else if (connectedZ)
                    return shapes[3];

                return connectedX ? shapes[4] : shapes[0];
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return rotatePillar(state, rotation);
    }

    public static BlockState rotatePillar(BlockState state, BlockRotation rotation) {
        return switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.get(AXIS)) {
                case X -> state.with(AXIS, Direction.Axis.Z);
                case Z -> state.with(AXIS, Direction.Axis.X);
                default -> state;
            };
            default -> state;
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState newState, Direction neighborDir, BlockState state, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        boolean waterlogged = world.getBlockState(pos).getFluidState().isIn(FluidTags.WATER);
        Direction.Axis axis = newState.get(AXIS);

        boolean slabTopAbove = world.getBlockState(pos.up()).getBlock() instanceof SlabBlock && world.getBlockState(pos.up()).get(SlabBlock.TYPE) == SlabType.TOP;
        boolean zConnectable = areZNeighborsConnectable(world, pos) && axis == Direction.Axis.Y;
        boolean xConnectable = areXNeighborsConnectable(world, pos) && axis == Direction.Axis.Y;

        newState = newState.with(EXTENDED, slabTopAbove).with(CONNECTED_Z, zConnectable).with(CONNECTED_X, xConnectable);

        return newState.with(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockPos clickedPos = context.getBlockPos();
        World world = context.getWorld();
        Direction.Axis axis = context.getSide().getAxis();
        boolean waterlogged = world.getBlockState(clickedPos).getFluidState().isIn(FluidTags.WATER);
        BlockState placeState = getDefaultState().with(AXIS, axis);

        if (world.getBlockState(clickedPos.up()).getBlock() instanceof SlabBlock) {
            if (world.getBlockState(clickedPos.up()).get(SlabBlock.TYPE) == SlabType.TOP)
                placeState = placeState.with(EXTENDED, true);
        }
        if (areZNeighborsConnectable(world, clickedPos) && axis == Direction.Axis.Y) {
            placeState = placeState.with(CONNECTED_Z, true);
        }
        if (areXNeighborsConnectable(world, clickedPos) && axis == Direction.Axis.Y) {
            placeState = placeState.with(CONNECTED_X, true);
        }
        return placeState.with(WATERLOGGED, waterlogged);
    }

    private boolean areZNeighborsConnectable(WorldAccess world, BlockPos origin) {
        BlockState northState = world.getBlockState(origin.north());
        BlockState southState = world.getBlockState(origin.south());

        return (northState.isOf(this) && northState.get(AXIS) != Direction.Axis.Y) || (southState.isOf(this) && southState.get(AXIS) != Direction.Axis.Y);
    }

    private boolean areXNeighborsConnectable(WorldAccess world, BlockPos origin) {
        BlockState eastState = world.getBlockState(origin.east());
        BlockState westState = world.getBlockState(origin.west());

        return (eastState.isOf(this) && eastState.get(AXIS) != Direction.Axis.Y) || (westState.isOf(this) && westState.get(AXIS) != Direction.Axis.Y);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATERLOGGED, AXIS, EXTENDED, CONNECTED_X, CONNECTED_Z);
    }
}
