package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
import com.obsidian_core.archaic_quest.common.misc.TranslationReferences;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
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
import net.minecraft.world.world.block.state.properties.BlockStateProperties;
import net.minecraft.world.world.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class KnappingTableBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public KnappingTableBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE));
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, World world, BlockPos pos, PlayerEntity player, InteractionHand hand, BlockHitResult blockHit) {
        if (world.isClient()) {
            return InteractionResult.SUCCESS;
        }
        else {
            player.openMenu(state.getMenuProvider(world, pos));
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    @SuppressWarnings("deprecation")
    public MenuProvider getMenuProvider(BlockState state, World world, BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player)
                -> new KnappingTableContainer(id, inventory, pos), TranslationReferences.KNAPPING_TABLE_CONTAINER_NAME);
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
}
