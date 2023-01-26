package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.AztecCraftingStationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AztecCraftingStationBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    // Whether this block state is the "master" block or a sub-block for collision purposes.
    public static final EnumProperty<BlockType> BLOCK_TYPE = EnumProperty.create("block_type", BlockType.class);

    private static final VoxelShape[] shapes = new VoxelShape[] {
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
    };


    public AztecCraftingStationBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BLOCK_TYPE, BlockType.MASTER));
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        else {
            this.openContainer(level, pos, player);
            return InteractionResult.CONSUME;
        }
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        BlockType type = state.getValue(BLOCK_TYPE);

        if (type == BlockType.MASTER) {
            return Shapes.block();
        }
        else {
            int i = state.getValue(FACING).get2DDataValue();
            return shapes[type == BlockType.LEFT ? i : i + 4];
        }
    }

    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getExistingBlockEntity(pos);

        if (blockEntity instanceof AztecCraftingStationBlockEntity) {
            player.openMenu((MenuProvider) blockEntity);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext useContext) {
        return this.defaultBlockState().setValue(FACING, useContext.getHorizontalDirection()).setValue(BLOCK_TYPE, BlockType.MASTER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean b) {
        super.onPlace(state, level, pos, oldState, b);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return state.getValue(BLOCK_TYPE) == BlockType.MASTER
                ? new AztecCraftingStationBlockEntity(pos, state)
                : null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
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
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathType) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, BLOCK_TYPE);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return EntityBlock.super.getTicker(level, state, blockEntityType);
    }

    private enum BlockType implements StringRepresentable {
        MASTER("master"),
        LEFT("left"),
        RIGHT("right");

        BlockType(String name) {
            this.name = name;
        }

        private final String name;

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
