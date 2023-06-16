package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonChestBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class AztecDungeonChestBlock extends Block implements BlockEntityProvider {

    public static final IntProperty ROTATION = Properties.ROTATION;

    public AztecDungeonChestBlock(Settings properties) {
        super(properties.nonOpaque());
        setDefaultState(getDefaultState().with(ROTATION, 0));
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(ROTATION, MathHelper.floor((double) (context.getPlayerYaw() * 16.0F / 360.0F) + 0.5D) & 15);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(ROTATION, rotation.rotate(state.get(ROTATION), 16));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.with(ROTATION, mirror.mirror(state.get(ROTATION), 16));
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }
        else {
            NamedScreenHandlerFactory menuProvider = createScreenHandlerFactory(state, world, pos);

            if (menuProvider != null) {
                player.openHandledScreen(menuProvider);
                player.incrementStat(Stats.CUSTOM.getOrCreateStat(Stats.OPEN_CHEST));
            }
            return ActionResult.CONSUME;
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof Inventory container) {
                ItemScatterer.spawn(world, pos, container);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Nullable
    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory) blockEntity : null;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AztecDungeonChestBlockEntity(pos, state);
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof AztecDungeonChestBlockEntity chest) {
            chest.recheckOpen();
        }
    }

    @Override
    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int id, int data) {
        super.onSyncedBlockEvent(state, world, pos, id, data);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity != null && blockEntity.onSyncedBlockEvent(id, data);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState blockState, BlockEntityType<T> type) {
        return (lvl, pos, state, blockEntity) -> AztecDungeonChestBlockEntity.lidAnimateTick(lvl, pos, state, (AztecDungeonChestBlockEntity) blockEntity);
    }
}
