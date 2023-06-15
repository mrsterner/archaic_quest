package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.tag.AQBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.world.block.state.properties.DirectionProperty;
import net.minecraft.world.world.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;
import java.util.Random;

public class CoolVinesBlock extends Block implements IForgeShearable {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    public static final BooleanProperty CAN_GROW = BooleanProperty.create("can_grow");

    private static final VoxelShape[] shapes = {
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D),
            Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D),
            Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 1.0D),
            Block.box(15.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 8.0D, 15.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 8.0D, 0.0D, 1.0D, 16.0D, 16.0D),
    };


    public CoolVinesBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(CUT, false).setValue(CAN_GROW, true));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return shapes[state.getValue(FACING).get2DDataValue() + (state.getValue(CUT) ? 4 : 0)];
    }

    public boolean isCut(BlockState state) {
        return state.getValue(CUT);
    }

    public boolean canGrow(BlockState state) {
        return state.getValue(CAN_GROW);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return canGrow(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomSource random) {
        if (!world.isAreaLoaded(pos, 1)) return;

        if (random.nextInt(10) == 0) {
            BlockPos belowPos = pos.below();

            if (belowPos.getY() > 0 && world.getBlockState(belowPos).isAir()) {
                world.setBlock(belowPos, state, 2);
            }
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction face = context.getClickedFace();
        BlockPos pos = context.getClickedPos();
        BlockPos behindPos = pos.relative(face.getOpposite());
        World world = context.getWorld();

        if (face == Direction.DOWN || face == Direction.UP) {
            face = context.getHorizontalDirection().getOpposite();
        }
        BlockState behindState = world.getBlockState(behindPos);
        BlockState aboveState = world.getBlockState(pos.above());

        if (Block.isFaceFull(world.getBlockState(behindPos).getCollisionShape(world, behindPos), face)
                || behindState.isFaceSturdy(world, behindPos, face)
                || (behindState.getBlock() instanceof SlabBlock && behindState.getValue(SlabBlock.TYPE) == SlabType.TOP)
                || (aboveState.getBlock() instanceof VerticalSlabBlock && aboveState.getValue(VerticalSlabBlock.SLAB_STATE).getDirection() == face )
                || aboveState.isFaceSturdy(world, pos.above(), Direction.DOWN)) {
            return defaultBlockState().setValue(FACING, face);
        }
        return null;
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, WorldReader world, BlockPos pos) {
        Direction face = state.getValue(FACING);
        BlockPos behindPos = pos.relative(face.getOpposite());
        BlockState behindState = world.getBlockState(behindPos);
        BlockState aboveState = world.getBlockState(pos.above());

        return Block.isFaceFull(world.getBlockState(behindPos).getCollisionShape(world, behindPos), face)
                || behindState.isFaceSturdy(world, behindPos, face)
                || (behindState.getBlock() instanceof SlabBlock && behindState.getValue(SlabBlock.TYPE) == SlabType.TOP)
                || (aboveState.getBlock() instanceof VerticalSlabBlock && aboveState.getValue(VerticalSlabBlock.SLAB_STATE).getDirection() == face )
                || aboveState.isFaceSturdy(world, pos.above(), Direction.DOWN)
                || (aboveState.is(this) && aboveState.getValue(FACING) == face && !aboveState.getValue(CUT));
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState newState, Direction direction, BlockState state, WorldAccessor world, BlockPos pos, BlockPos pos1) {
        return !newState.canSurvive(world, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(newState, direction, state, world, pos, pos1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, CUT, CAN_GROW);
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
}
