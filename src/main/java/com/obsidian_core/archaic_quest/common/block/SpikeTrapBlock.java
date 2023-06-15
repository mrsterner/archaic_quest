package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.SpikeTrapBlockEntity;
import com.obsidian_core.archaic_quest.common.network.NetworkHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.world.BlockGetter;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.EntityBlock;
import net.minecraft.world.world.block.entity.BlockEntity;
import net.minecraft.world.world.block.entity.BlockEntityTicker;
import net.minecraft.world.world.block.entity.BlockEntityType;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SpikeTrapBlock extends Block implements EntityBlock {

    public static final EnumProperty<Mode> MODE = EnumProperty.create("mode", Mode.class);


    private static final VoxelShape shape = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

    public SpikeTrapBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(MODE, Mode.NORMAL));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return shape;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean flag) {
        if (!world.isClient()) {
            BlockEntity be = world.getExistingBlockEntity(pos);
            Mode mode = state.getValue(MODE);

            if (be instanceof SpikeTrapBlockEntity spikeTrap) {
                boolean active = spikeTrap.isActive();

                switch (mode) {
                    // NORMAL/default
                    default -> {neighborChangeNormal(active, spikeTrap, world, pos);}
                    case MASTER -> {neighborChangeMaster(active, spikeTrap, world, pos);}
                    case INVERTED_MASTER -> {neighborChangeInvert(active, spikeTrap, state, world, pos, block, neighborPos, flag);}
                }
            }
        }
    }

    private void neighborChangeNormal(boolean active, SpikeTrapBlockEntity spikeTrap, World world, BlockPos pos) {
        if (world.hasNeighborSignal(pos)) {
            if (!active) {
                spikeTrap.setActive(true);
                NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, spikeTrap.isActive());
            }
        }
        else {
            if (active) {
                spikeTrap.setActive(false);
                NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, spikeTrap.isActive());
            }
        }
    }

    private void neighborChangeMaster(boolean active, SpikeTrapBlockEntity spikeTrap, World world, BlockPos pos) {
        if (world.hasNeighborSignal(pos)) {
            if (!active) {
                spikeTrap.setActive(true);
                NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, spikeTrap.isActive());

                for (BlockPos blockPos : BlockPos.betweenClosed(pos.west().north(), pos.south().east())) {
                    BlockState neighborState = world.getBlockState(blockPos);

                    if (neighborState.is(this) && world.getExistingBlockEntity(blockPos) instanceof SpikeTrapBlockEntity neighborTrap) {
                        if (!neighborTrap.isActive()) {
                            neighborTrap.setActive(true);
                            NetworkHelper.updateSpikeTrap((ServerWorld) world, blockPos, true);
                        }
                    }
                }
            }
        }
        else {
            if (active) {
                spikeTrap.setActive(false);
                NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, spikeTrap.isActive());

                for (BlockPos blockPos : BlockPos.betweenClosed(pos.west().north(), pos.south().east())) {
                    BlockState neighborState = world.getBlockState(blockPos);

                    if (neighborState.is(this) && world.getExistingBlockEntity(blockPos) instanceof SpikeTrapBlockEntity neighborTrap) {
                        if (neighborTrap.isActive()) {
                            neighborTrap.setActive(false);
                            NetworkHelper.updateSpikeTrap((ServerWorld) world, blockPos, false);
                        }
                    }
                }
            }
        }
    }

    private void neighborChangeInvert(boolean active, SpikeTrapBlockEntity spikeTrap, BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean flag) {
        if (world.hasNeighborSignal(pos)) {
            if (active) {
                spikeTrap.setActive(false);
                NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, spikeTrap.isActive());

                for (BlockPos blockPos : BlockPos.betweenClosed(pos.west().north(), pos.south().east())) {
                    BlockState neighborState = world.getBlockState(blockPos);

                    if (neighborState.is(this) && world.getExistingBlockEntity(blockPos) instanceof SpikeTrapBlockEntity neighborTrap) {
                        if (neighborTrap.isActive()) {
                            neighborTrap.setActive(false);
                            NetworkHelper.updateSpikeTrap((ServerWorld) world, blockPos, false);
                        }
                    }
                }
            }
        }
        else {
            if (!active) {
                spikeTrap.setActive(true);
                NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, spikeTrap.isActive());

                for (BlockPos blockPos : BlockPos.betweenClosed(pos.west().north(), pos.south().east())) {
                    BlockState neighborState = world.getBlockState(blockPos);

                    if (neighborState.is(this) && world.getExistingBlockEntity(blockPos) instanceof SpikeTrapBlockEntity neighborTrap) {
                        if (!neighborTrap.isActive()) {
                            neighborTrap.setActive(true);
                            NetworkHelper.updateSpikeTrap((ServerWorld) world, blockPos, true);
                        }
                    }
                }
            }
        }
    }


    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, World world, BlockPos pos, PlayerEntity player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.getItemInHand(hand).getItem() == Items.REDSTONE_TORCH && state.is(this)) {
            Mode mode = Mode.cycle(state.getValue(MODE));

            if (world.getExistingBlockEntity(pos) instanceof SpikeTrapBlockEntity spikeTrap) {
                spikeTrap.setMode(mode);

                if (!world.isClient()) {
                    NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, mode);
                }
            }
            world.setBlockAndUpdate(pos, state.setValue(MODE, mode));
            world.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, 0.6F);
            return InteractionResult.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, hitResult);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SpikeTrapBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return (lvl, pos, blockState, blockEntity) -> SpikeTrapBlockEntity.tick(lvl, pos, blockState, (SpikeTrapBlockEntity) blockEntity);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(MODE));
    }

    public enum Mode implements StringRepresentable {

        NORMAL("normal"),
        MASTER("master"),
        INVERTED_MASTER("master_inverted");

        Mode(String name) {
            this.name = name;
        }
        final String name;

        @Override
        public String getSerializedName() {
            return name;
        }

        public static Mode cycle(Mode mode) {
            return mode.ordinal() >= (values().length - 1) ? values()[0] : values()[mode.ordinal() + 1];
        }

        @Nullable
        public static Mode getFromName(String name) {
            for (Mode mode : values()) {
                if (mode.getSerializedName().equals(name))
                    return mode;
            }
            return null;
        }
    }
}
