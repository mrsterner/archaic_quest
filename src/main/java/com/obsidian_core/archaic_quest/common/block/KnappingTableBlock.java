package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
import com.obsidian_core.archaic_quest.common.misc.TranslationReferences;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ActionResult;
import net.minecraft.world.NamedScreenHandlerFactory;
import net.minecraft.world.SimpleNamedScreenHandlerFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.Blocks;
import net.minecraft.world.world.block.Mirror;
import net.minecraft.world.world.block.Rotation;
import net.minecraft.world.world.block.state.BlockBehaviour;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.Properties;
import net.minecraft.world.world.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class KnappingTableBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public KnappingTableBlock() {
        super(BlockBehaviour.Settings.copy(Blocks.CRAFTING_TABLE));
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getDefaultState().with(FACING, context.getPlayerFacing().getOpposite());
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult use(BlockState state, World world, BlockPos pos, PlayerEntity player, InteractionHand hand, BlockHitResult blockHit) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }
        else {
            player.openHandledScreen(state.getNamedScreenHandlerFactory(world, pos));
            return ActionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    @SuppressWarnings("deprecation")
    public NamedScreenHandlerFactory getNamedScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((id, inventory, player)
                -> new KnappingTableContainer(id, inventory, pos), TranslationReferences.KNAPPING_TABLE_CONTAINER_NAME);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }
}
