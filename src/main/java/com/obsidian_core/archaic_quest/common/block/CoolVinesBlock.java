package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Shearable;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CoolVinesBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty CUT = BooleanProperty.of("cut");
    public static final BooleanProperty CAN_GROW = BooleanProperty.of("can_grow");

    private static final VoxelShape[] shapes = {
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D),
            Block.createCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 1.0D),
            Block.createCuboidShape(15.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 8.0D, 15.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 8.0D, 0.0D, 1.0D, 16.0D, 16.0D),
    };


    public CoolVinesBlock(Settings properties) {
        super(properties);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(CUT, false).with(CAN_GROW, true));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shapes[state.get(FACING).getHorizontal() + (state.get(CUT) ? 4 : 0)];
    }

    public boolean canGrow(BlockState state) {
        return state.get(CAN_GROW);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return canGrow(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isAreaLoaded(pos, 1)) return;

        if (random.nextInt(10) == 0) {
            BlockPos downPos = pos.down();

            if (downPos.getY() > 0 && world.getBlockState(downPos).isAir()) {
                world.setBlockState(downPos, state, 2);
            }
        }
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction face = context.getSide();
        BlockPos pos = context.getBlockPos();
        BlockPos behindPos = pos.offset(face.getOpposite());
        World world = context.getWorld();

        if (face == Direction.DOWN || face == Direction.UP) {
            face = context.getPlayerFacing().getOpposite();
        }
        BlockState behindState = world.getBlockState(behindPos);
        BlockState upState = world.getBlockState(pos.up());

        if (Block.isFaceFullSquare(world.getBlockState(behindPos).getCollisionShape(world, behindPos), face)
                || behindState.isSideSolidFullSquare(world, behindPos, face)
                || (behindState.getBlock() instanceof SlabBlock && behindState.get(SlabBlock.TYPE) == SlabType.TOP)
                || (upState.getBlock() instanceof VerticalSlabBlock && upState.get(VerticalSlabBlock.SLAB_STATE).getDirection() == face )
                || upState.isSideSolidFullSquare(world, pos.up(), Direction.DOWN)) {
            return getDefaultState().with(FACING, face);
        }
        return null;
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction face = state.get(FACING);
        BlockPos behindPos = pos.offset(face.getOpposite());
        BlockState behindState = world.getBlockState(behindPos);
        BlockState upState = world.getBlockState(pos.up());

        return Block.isFaceFullSquare(world.getBlockState(behindPos).getCollisionShape(world, behindPos), face)
                || behindState.isSideSolidFullSquare(world, behindPos, face)
                || (behindState.getBlock() instanceof SlabBlock && behindState.get(SlabBlock.TYPE) == SlabType.TOP)
                || (upState.getBlock() instanceof VerticalSlabBlock && upState.get(VerticalSlabBlock.SLAB_STATE).getDirection() == face )
                || upState.isSideSolidFullSquare(world, pos.up(), Direction.DOWN)
                || (upState.isOf(this) && upState.get(FACING) == face && !upState.get(CUT));
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    @Override
    public BlockState getStateForNeighborUpdate(BlockState newState, Direction direction, BlockState state, WorldAccess world, BlockPos pos, BlockPos pos1) {
        return !newState.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(newState, direction, state, world, pos, pos1);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, CUT, CAN_GROW);
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
}
