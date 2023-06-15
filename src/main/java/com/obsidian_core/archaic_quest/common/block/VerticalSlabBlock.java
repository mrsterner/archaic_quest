package com.obsidian_core.archaic_quest.common.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.BlockGetter;
import net.minecraft.world.world.WorldAccessor;
import net.minecraft.world.world.block.*;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.Properties;
import net.minecraft.world.world.block.state.properties.BooleanProperty;
import net.minecraft.world.world.block.state.properties.EnumProperty;
import net.minecraft.world.world.material.Fluid;
import net.minecraft.world.world.material.FluidState;
import net.minecraft.world.world.material.Fluids;
import net.minecraft.world.world.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

/** Essentially just copy-paste code from Quark */
public class VerticalSlabBlock extends Block implements SimpleWaterloggedBlock {

    public static final EnumProperty<SlabState> SLAB_STATE = EnumProperty.create("type", SlabState.class);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    private static final VoxelShape DOUBLE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public VerticalSlabBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(SLAB_STATE, SlabState.NORTH).with(WATERLOGGED, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.get(SLAB_STATE)) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            case EAST -> EAST;
            case DOUBLE -> DOUBLE;
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return blockState.get(SLAB_STATE) != SlabState.DOUBLE;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getBlockPos();
        BlockState state = context.getWorld().getBlockState(blockpos);

        if(state.getBlock() == this) {
            return state.with(SLAB_STATE, SlabState.DOUBLE).with(WATERLOGGED, false);
        }

        FluidState fluid = context.getWorld().getFluidState(blockpos);
        BlockState newState = getDefaultState().with(WATERLOGGED, fluid.getType() == Fluids.WATER);
        Direction direction = getDirectionForPlacement(context);

        return newState.with(SLAB_STATE, SlabState.getTypeFromDirection(direction));
    }

    private Direction getDirectionForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();

        if(direction.getAxis() != Direction.Axis.Y)
            return direction;

        BlockPos pos = context.getBlockPos();
        Vec3d vec = context.getClickLocation().subtract(new Vec3(pos.getX(), pos.getY(), pos.getZ())).subtract(0.5, 0, 0.5);
        double angle = Math.atan2(vec.x, vec.z) * -180.0 / Math.PI;
        return Direction.fromYRot(angle).getOpposite();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        ItemStack itemstack = useContext.getStackInHand();
        SlabState slabState = state.get(SLAB_STATE);
        Direction direction = slabState.getDirection();

        return slabState != SlabState.DOUBLE && itemstack.getItem() == asItem() && useContext.replacingClickedOnBlock() &&
                (useContext.getClickedFace() == direction && getDirectionForPlacement(useContext) == direction);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, WorldAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if(state.get(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(WorldAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(SLAB_STATE) != SlabState.DOUBLE && SimpleWaterloggedBlock.super.placeLiquid(world, pos, state, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(SLAB_STATE) != SlabState.DOUBLE && SimpleWaterloggedBlock.super.canPlaceLiquid(world, pos, state, fluid);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
        return type == PathComputationType.WATER && world.getFluidState(pos).is(FluidTags.WATER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) {
        if (state.get(SLAB_STATE) == SlabState.DOUBLE) {
            return state;
        }
        return state.with(SLAB_STATE, SlabState.getTypeFromDirection(rot.rotate(state.get(SLAB_STATE).getDirection())));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        if (state.get(SLAB_STATE) == SlabState.DOUBLE) {
            return state;
        }
        return state.rotate(mirror.getRotation(state.get(SLAB_STATE).getDirection()));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SLAB_STATE, WATERLOGGED);
    }

    public enum SlabState implements StringRepresentable {

        NORTH(Direction.NORTH),
        EAST(Direction.EAST),
        SOUTH(Direction.SOUTH),
        WEST(Direction.WEST),
        DOUBLE(null);

        SlabState(@Nullable Direction direction) {
            this.name = direction == null ? "double" : direction.toString();
            this.direction = direction;
        }

        private final Direction direction;
        private final String name;

        @Override
        public String getSerializedName() {
            return this.name;
        }

        public Direction getDirection() {
            return this.direction;
        }

        public static SlabState getTypeFromDirection(Direction direction) {
            return switch (direction) {
                case UP, DOWN, NORTH -> NORTH;
                case WEST -> WEST;
                case EAST -> EAST;
                case SOUTH -> SOUTH;
            };
        }
    }
}
