package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.state.AQStateProperties;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.BlockGetter;
import net.minecraft.world.world.World;
import net.minecraft.world.world.WorldAccessor;
import net.minecraft.world.world.WorldReader;
import net.minecraft.world.world.block.*;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.BlockStateProperties;
import net.minecraft.world.world.block.state.properties.BooleanProperty;
import net.minecraft.world.world.block.state.properties.EnumProperty;
import net.minecraft.world.world.block.state.properties.SlabType;
import net.minecraft.world.world.material.FluidState;
import net.minecraft.world.world.material.Fluids;
import net.minecraft.world.world.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AztecWoodPillarBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty EXTENDED = AQStateProperties.EXTENDED;
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty CONNECTED_X = BooleanProperty.create("connected_x");
    public static final BooleanProperty CONNECTED_Z = BooleanProperty.create("connected_z");


    private static final VoxelShape[] shapes = new VoxelShape[] {
            // normal
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
            // Z axis
            Block.box(0.0D, 10.0D, 0.0D, 6.0D, 16.0D, 16.0D),
            // X axis
            Block.box(0.0D, 4.0D, 0.0D, 16.0D, 10.0D, 6.0D),
            // Connect Z
            Shapes.or(Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
                    Block.box(0.0D, 10.0D, 0.0D, 6.0D, 16.0D, 16.0D)),
            // Connect X
            Shapes.or(Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
                    Block.box(0.0D, 4.0D, 0.0D, 16.0D, 10.0D, 6.0D)),
            // Connect XZ
            Shapes.or(Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D),
                Block.box(0.0D, 10.0D, 0.0D, 6.0D, 16.0D, 16.0D),
                Block.box(0.0D, 4.0D, 0.0D, 16.0D, 10.0D, 6.0D))
    };


    private static final VoxelShape SHAPE = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);

    public AztecWoodPillarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(EXTENDED, false)
                .setValue(CONNECTED_X, false)
                .setValue(CONNECTED_Z, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        boolean connectedX = state.getValue(CONNECTED_X);
        boolean connectedZ = state.getValue(CONNECTED_Z);
        Direction.Axis axis = state.getValue(AXIS);

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
    public BlockState rotate(BlockState state, Rotation rotation) {
        return rotatePillar(state, rotation);
    }

    public static BlockState rotatePillar(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(AXIS)) {
                case X -> state.setValue(AXIS, Direction.Axis.Z);
                case Z -> state.setValue(AXIS, Direction.Axis.X);
                default -> state;
            };
            default -> state;
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState newState, Direction neighborDir, BlockState state, WorldAccessor world, BlockPos pos, BlockPos neighborPos) {
        boolean waterlogged = world.getBlockState(pos).getFluidState().is(FluidTags.WATER);
        Direction.Axis axis = newState.getValue(AXIS);

        boolean slabTopAbove = world.getBlockState(pos.above()).getBlock() instanceof SlabBlock && world.getBlockState(pos.above()).getValue(SlabBlock.TYPE) == SlabType.TOP;
        boolean zConnectable = areZNeighborsConnectable(world, pos) && axis == Direction.Axis.Y;
        boolean xConnectable = areXNeighborsConnectable(world, pos) && axis == Direction.Axis.Y;

        newState = newState.setValue(EXTENDED, slabTopAbove).setValue(CONNECTED_Z, zConnectable).setValue(CONNECTED_X, xConnectable);

        return newState.setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos clickedPos = context.getClickedPos();
        World world = context.getWorld();
        Direction.Axis axis = context.getClickedFace().getAxis();
        boolean waterlogged = world.getBlockState(clickedPos).getFluidState().is(FluidTags.WATER);
        BlockState placeState = defaultBlockState().setValue(AXIS, axis);

        if (world.getBlockState(clickedPos.above()).getBlock() instanceof SlabBlock) {
            if (world.getBlockState(clickedPos.above()).getValue(SlabBlock.TYPE) == SlabType.TOP)
                placeState = placeState.setValue(EXTENDED, true);
        }
        if (areZNeighborsConnectable(world, clickedPos) && axis == Direction.Axis.Y) {
            placeState = placeState.setValue(CONNECTED_Z, true);
        }
        if (areXNeighborsConnectable(world, clickedPos) && axis == Direction.Axis.Y) {
            placeState = placeState.setValue(CONNECTED_X, true);
        }
        return placeState.setValue(WATERLOGGED, waterlogged);
    }

    private boolean areZNeighborsConnectable(WorldAccessor world, BlockPos origin) {
        BlockState northState = world.getBlockState(origin.north());
        BlockState southState = world.getBlockState(origin.south());

        return (northState.is(this) && northState.getValue(AXIS) != Direction.Axis.Y) || (southState.is(this) && southState.getValue(AXIS) != Direction.Axis.Y);
    }

    private boolean areXNeighborsConnectable(WorldAccessor world, BlockPos origin) {
        BlockState eastState = world.getBlockState(origin.east());
        BlockState westState = world.getBlockState(origin.west());

        return (eastState.is(this) && eastState.getValue(AXIS) != Direction.Axis.Y) || (westState.is(this) && westState.getValue(AXIS) != Direction.Axis.Y);
    }


    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType pathType) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATERLOGGED, AXIS, EXTENDED, CONNECTED_X, CONNECTED_Z);
    }
}
