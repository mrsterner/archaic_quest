package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.register.AQItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
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
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isAreaLoaded(pos, 1)) return;

        if (random.nextInt(10) == 0) {
            BlockPos belowPos = pos.below();

            if (belowPos.getY() > 0 && world.getBlockState(belowPos).isAir(world, belowPos)) {
                world.setBlock(belowPos, state, 2);
            }
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction face = context.getClickedFace();
        BlockPos behindPos = context.getClickedPos().relative(face.getOpposite());
        World world = context.getLevel();

        if (face != Direction.DOWN && face != Direction.UP) {
            if (Block.isFaceFull(world.getBlockState(behindPos).getCollisionShape(world, behindPos), face)
                    || world.getBlockState(behindPos).isFaceSturdy(world, behindPos, face)) {
                return defaultBlockState().setValue(FACING, face);
            }
        }
        return null;
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        Direction face = state.getValue(FACING);
        BlockPos behindPos = pos.relative(face.getOpposite());
        BlockState aboveState = world.getBlockState(pos.above());

        return Block.isFaceFull(world.getBlockState(behindPos).getCollisionShape(world, behindPos), face)
                || world.getBlockState(behindPos).isFaceSturdy(world, behindPos, face)
                || (aboveState.is(this) && aboveState.getValue(FACING) == face && !aboveState.getValue(CUT));
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState newState, Direction direction, BlockState state, IWorld world, BlockPos pos, BlockPos pos1) {
        return !newState.canSurvive(world, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(newState, direction, state, world, pos, pos1);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
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
