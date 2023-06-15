package com.obsidian_core.archaic_quest.common.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.WorldAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

/** Essentially just copy-paste code from Quark */
public class VerticalSlabBlock extends Block implements SimpleWaterloggedBlock {

    public static final EnumProperty<SlabState> SLAB_STATE = EnumProperty.create("type", SlabState.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    private static final VoxelShape DOUBLE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public VerticalSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(SLAB_STATE, SlabState.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(SLAB_STATE)) {
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
        return blockState.getValue(SLAB_STATE) != SlabState.DOUBLE;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState state = context.getWorld().getBlockState(blockpos);

        if(state.getBlock() == this) {
            return state.setValue(SLAB_STATE, SlabState.DOUBLE).setValue(WATERLOGGED, false);
        }

        FluidState fluid = context.getWorld().getFluidState(blockpos);
        BlockState newState = defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
        Direction direction = getDirectionForPlacement(context);

        return newState.setValue(SLAB_STATE, SlabState.getTypeFromDirection(direction));
    }

    private Direction getDirectionForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();

        if(direction.getAxis() != Direction.Axis.Y)
            return direction;

        BlockPos pos = context.getClickedPos();
        Vec3 vec = context.getClickLocation().subtract(new Vec3(pos.getX(), pos.getY(), pos.getZ())).subtract(0.5, 0, 0.5);
        double angle = Math.atan2(vec.x, vec.z) * -180.0 / Math.PI;
        return Direction.fromYRot(angle).getOpposite();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        ItemStack itemstack = useContext.getItemInHand();
        SlabState slabState = state.getValue(SLAB_STATE);
        Direction direction = slabState.getDirection();

        return slabState != SlabState.DOUBLE && itemstack.getItem() == asItem() && useContext.replacingClickedOnBlock() &&
                (useContext.getClickedFace() == direction && getDirectionForPlacement(useContext) == direction);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, WorldAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if(state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(WorldAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.getValue(SLAB_STATE) != SlabState.DOUBLE && SimpleWaterloggedBlock.super.placeLiquid(level, pos, state, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        return state.getValue(SLAB_STATE) != SlabState.DOUBLE && SimpleWaterloggedBlock.super.canPlaceLiquid(level, pos, state, fluid);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return type == PathComputationType.WATER && level.getFluidState(pos).is(FluidTags.WATER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) {
        if (state.getValue(SLAB_STATE) == SlabState.DOUBLE) {
            return state;
        }
        return state.setValue(SLAB_STATE, SlabState.getTypeFromDirection(rot.rotate(state.getValue(SLAB_STATE).getDirection())));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        if (state.getValue(SLAB_STATE) == SlabState.DOUBLE) {
            return state;
        }
        return state.rotate(mirror.getRotation(state.getValue(SLAB_STATE).getDirection()));
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
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
