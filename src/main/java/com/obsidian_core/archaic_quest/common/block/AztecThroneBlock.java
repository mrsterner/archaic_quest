package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.data.ThroneType;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import com.obsidian_core.archaic_quest.common.blockentity.AztecThroneBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.world.block.state.properties.DirectionProperty;
import net.minecraft.world.world.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AztecThroneBlock extends Block implements EntityBlock {

    public static DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape shape = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D);


    private final ThroneType throneType;

    public AztecThroneBlock(ThroneType throneType) {
        super(BlockBehaviour.Properties.copy(AQBlocks.ANDESITE_AZTEC_BRICKS_0.get()).noOcclusion());
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
        this.throneType = throneType;
    }


    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return shape;
    }

    public ThroneType getThroneType() {
        return throneType;
    }

    @Override
    @SuppressWarnings("deprecation")
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }

    @Override
    @SuppressWarnings("deprecation")
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection();
        return defaultBlockState().setValue(FACING, direction);
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
        stateBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AztecThroneBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return null;
    }
}
