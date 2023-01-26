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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AztecWoodPillarBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty EXTENDED = AQStateProperties.EXTENDED;
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty CONNECTED_X = BooleanProperty.create("connected_x");
    public static final BooleanProperty CONNECTED_Z = BooleanProperty.create("connected_z");

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
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
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
    public BlockState updateShape(BlockState newState, Direction neighborDir, BlockState state, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        boolean waterlogged = level.getBlockState(pos).getFluidState().is(FluidTags.WATER);
        Direction.Axis axis = newState.getValue(AXIS);
        boolean extended = newState.getValue(EXTENDED);
        boolean slabTopAbove = level.getBlockState(pos.above()).getBlock() instanceof SlabBlock && level.getBlockState(pos.above()).getValue(SlabBlock.TYPE) == SlabType.TOP;

        if (!extended) {
            if (slabTopAbove)
                newState = newState.setValue(EXTENDED, true);
        }
        else {
            if (!slabTopAbove)
                newState = newState.setValue(EXTENDED, false);
        }
        if ((level.getBlockState(pos.north()).is(this) || level.getBlockState(pos.south()).is(this)) && axis == Direction.Axis.Y) {
            newState = newState.setValue(CONNECTED_Z, true);
        }
        if ((level.getBlockState(pos.east()).is(this) || level.getBlockState(pos.west()).is(this)) && axis == Direction.Axis.Y) {
            newState = newState.setValue(CONNECTED_X, true);
        }
        return newState.setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();
        Direction.Axis axis = context.getClickedFace().getAxis();
        boolean waterlogged = level.getBlockState(clickedPos).getFluidState().is(FluidTags.WATER);
        BlockState placeState = defaultBlockState().setValue(AXIS, axis);

        if (level.getBlockState(clickedPos.above()).getBlock() instanceof SlabBlock) {
            if (level.getBlockState(clickedPos.above()).getValue(SlabBlock.TYPE) == SlabType.TOP)
                placeState = placeState.setValue(EXTENDED, true);
        }
        if ((level.getBlockState(clickedPos.north()).is(this) || level.getBlockState(clickedPos.south()).is(this)) && axis == Direction.Axis.Y) {
            placeState = placeState.setValue(CONNECTED_Z, true);
        }
        if ((level.getBlockState(clickedPos.east()).is(this) || level.getBlockState(clickedPos.west()).is(this)) && axis == Direction.Axis.Y) {
            placeState = placeState.setValue(CONNECTED_X, true);
        }
        ArchaicQuest.LOGGER.info("State: " + placeState);

        return placeState.setValue(WATERLOGGED, waterlogged);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathType) {
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
