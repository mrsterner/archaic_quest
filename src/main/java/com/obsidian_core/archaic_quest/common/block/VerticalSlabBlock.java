package com.obsidian_core.archaic_quest.common.block;


import io.github.fabricators_of_create.porting_lib.extensions.BlockExtensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class VerticalSlabBlock extends Block implements Waterloggable {

    public static final EnumProperty<SlabState> SLAB_STATE = EnumProperty.of("type", SlabState.class);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape NORTH = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SOUTH = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    private static final VoxelShape DOUBLE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public VerticalSlabBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(SLAB_STATE, SlabState.NORTH).with(WATERLOGGED, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
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
    public boolean hasSidedTransparency(BlockState blockState) {
        return blockState.get(SLAB_STATE) != SlabState.DOUBLE;
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockPos blockpos = context.getBlockPos();
        BlockState state = context.getWorld().getBlockState(blockpos);

        if(state.getBlock() == this) {
            return state.with(SLAB_STATE, SlabState.DOUBLE).with(WATERLOGGED, false);
        }

        FluidState fluid = context.getWorld().getFluidState(blockpos);
        BlockState newState = getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
        Direction direction = getDirectionForPlacement(context);

        return newState.with(SLAB_STATE, SlabState.getTypeFromDirection(direction));
    }

    private Direction getDirectionForPlacement(ItemPlacementContext context) {
        Direction direction = context.getSide();

        if(direction.getAxis() != Direction.Axis.Y)
            return direction;

        BlockPos pos = context.getBlockPos();
        Vec3d vec = context.getHitPos().subtract(new Vec3d(pos.getX(), pos.getY(), pos.getZ())).subtract(0.5, 0, 0.5);
        double angle = Math.atan2(vec.x, vec.z) * -180.0 / Math.PI;
        return Direction.fromRotation(angle).getOpposite();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canReplace(BlockState state, ItemPlacementContext useContext) {
        ItemStack itemstack = useContext.getStack();
        SlabState slabState = state.get(SLAB_STATE);
        Direction direction = slabState.getDirection();

        return slabState != SlabState.DOUBLE && itemstack.getItem() == asItem() && useContext.canReplaceExisting() &&
                (useContext.getSide() == direction && getDirectionForPlacement(useContext) == direction);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess world, BlockPos currentPos, BlockPos facingPos) {
        if(state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(SLAB_STATE) != SlabState.DOUBLE && Waterloggable.super.canFillWithFluid(world, pos, state, fluid);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type == NavigationType.WATER && world.getFluidState(pos).isIn(FluidTags.WATER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, BlockRotation rot) {
        if (state.get(SLAB_STATE) == SlabState.DOUBLE) {
            return state;
        }
        return state.with(SLAB_STATE, SlabState.getTypeFromDirection(rot.rotate(state.get(SLAB_STATE).getDirection())));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        if (state.get(SLAB_STATE) == SlabState.DOUBLE) {
            return state;
        }
        return state.rotate(mirror.getRotation(state.get(SLAB_STATE).getDirection()));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SLAB_STATE, WATERLOGGED);
    }

    public enum SlabState implements StringIdentifiable {

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
        public String asString() {
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
