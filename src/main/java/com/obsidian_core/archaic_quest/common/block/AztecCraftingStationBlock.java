package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.AztecCraftingStationBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AztecCraftingStationBlock extends Block implements BlockEntityProvider {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    // Whether this block state is the "master" block or a sub-block for collision purposes.
    public static final EnumProperty<BlockType> BLOCK_TYPE = EnumProperty.of("block_type", BlockType.class);

    private static final VoxelShape[] shapes = new VoxelShape[] {
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
    };


    public AztecCraftingStationBlock(Settings properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BLOCK_TYPE, BlockType.MASTER));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }
        else {
            this.openContainer(world, pos, player);
            return ActionResult.CONSUME;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        BlockType type = state.get(BLOCK_TYPE);

        if (type == BlockType.MASTER) {
            return VoxelShapes.fullCube();
        }
        else {
            int i = state.get(FACING).getHorizontal();
            return shapes[type == BlockType.LEFT ? i : i + 4];
        }
    }

    protected void openContainer(World world, BlockPos pos, PlayerEntity player) {
        if (world.getBlockEntity(pos) instanceof AztecCraftingStationBlockEntity aztecCraftingStationBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory) aztecCraftingStationBlockEntity);
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerLookDirection()).with(BLOCK_TYPE, BlockType.MASTER);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.get(BLOCK_TYPE) == BlockType.MASTER ? new AztecCraftingStationBlockEntity(pos, state) : null;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, BLOCK_TYPE);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return BlockEntityProvider.super.getTicker(world, state, type);
    }

    private enum BlockType implements StringIdentifiable {
        MASTER("master"),
        LEFT("left"),
        RIGHT("right");

        BlockType(String name) {
            this.name = name;
        }

        private final String name;

        @Override
        public String asString() {
            return name;
        }
    }
}
