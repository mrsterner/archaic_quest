package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonChestBlockEntity;

@SuppressWarnings("deprecation")
public class AztecDungeonChestBlock extends Block implements EntityBlock {

    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;

    public AztecDungeonChestBlock(Properties properties) {
        super(properties.noOcclusion());
        registerDefaultState(stateDefinition.any().setValue(ROTATION, 0));
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(ROTATION, MathHelper.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(ROTATION, rotation.rotate(state.getValue(ROTATION), 16));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(ROTATION, mirror.mirror(state.getValue(ROTATION), 16));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56329_) {
        p_56329_.add(ROTATION);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public InteractionResult use(BlockState state, World world, BlockPos pos, PlayerEntity player, InteractionHand hand, BlockHitResult hitResult) {
        if (world.isClient()) {
            return InteractionResult.SUCCESS;
        }
        else {
            MenuProvider menuProvider = getMenuProvider(state, world, pos);

            if (menuProvider != null) {
                player.openMenu(menuProvider);
                player.awardStat(Stats.CUSTOM.get(Stats.OPEN_CHEST));
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean b) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof Container container) {
                Containers.dropContents(world, pos, container);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, b);
        }
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType computationType) {
        return false;
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof MenuProvider ? (MenuProvider) blockEntity : null;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AztecDungeonChestBlockEntity(pos, state);
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, RandomSource random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof AztecDungeonChestBlockEntity chest) {
            chest.recheckOpen();
        }
    }

    @Override
    public boolean triggerEvent(BlockState state, World world, BlockPos pos, int id, int data) {
        super.triggerEvent(state, world, pos, id, data);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity != null && blockEntity.triggerEvent(id, data);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState blockState, BlockEntityType<T> type) {
        return (lvl, pos, state, blockEntity) -> AztecDungeonChestBlockEntity.lidAnimateTick(lvl, pos, state, (AztecDungeonChestBlockEntity) blockEntity);
    }
}
