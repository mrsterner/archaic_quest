package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.SpikeTrapBlockEntity;
import com.obsidian_core.archaic_quest.common.network.NetworkHelper;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SpikeTrapBlock extends Block implements BlockEntityProvider {

    public static final EnumProperty<Mode> MODE = EnumProperty.of("mode", Mode.class);


    private static final VoxelShape shape = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

    public SpikeTrapBlock(Settings properties) {
        super(properties);
        setDefaultState(getDefaultState().with(MODE, Mode.NORMAL));
    }


    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean flag) {
        if (!world.isClient()) {
            BlockEntity be = world.getBlockEntity(pos);
            Mode mode = state.get(MODE);

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
        if (world.isReceivingRedstonePower(pos)) {
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
        if (world.isReceivingRedstonePower(pos)) {
            if (!active) {
                spikeTrap.setActive(true);
                NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, spikeTrap.isActive());

                for (BlockPos blockPos : BlockPos.iterate(pos.west().north(), pos.south().east())) {
                    BlockState neighborState = world.getBlockState(blockPos);

                    if (neighborState.isOf(this) && world.getBlockEntity(blockPos) instanceof SpikeTrapBlockEntity neighborTrap) {
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

                for (BlockPos blockPos : BlockPos.iterate(pos.west().north(), pos.south().east())) {
                    BlockState neighborState = world.getBlockState(blockPos);

                    if (neighborState.isOf(this) && world.getBlockEntity(blockPos) instanceof SpikeTrapBlockEntity neighborTrap) {
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
        if (world.isReceivingRedstonePower(pos)) {
            if (active) {
                spikeTrap.setActive(false);
                NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, spikeTrap.isActive());

                for (BlockPos blockPos : BlockPos.iterate(pos.west().north(), pos.south().east())) {
                    BlockState neighborState = world.getBlockState(blockPos);

                    if (neighborState.isOf(this) && world.getBlockEntity(blockPos) instanceof SpikeTrapBlockEntity neighborTrap) {
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

                for (BlockPos blockPos : BlockPos.iterate(pos.west().north(), pos.south().east())) {
                    BlockState neighborState = world.getBlockState(blockPos);

                    if (neighborState.isOf(this) && world.getBlockEntity(blockPos) instanceof SpikeTrapBlockEntity neighborTrap) {
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (player.getStackInHand(hand).getItem() == Items.REDSTONE_TORCH && state.isOf(this)) {
            Mode mode = Mode.cycle(state.get(MODE));

            if (world.getBlockEntity(pos) instanceof SpikeTrapBlockEntity spikeTrap) {
                spikeTrap.setMode(mode);

                if (!world.isClient()) {
                    NetworkHelper.updateSpikeTrap((ServerWorld) world, pos, mode);
                }
            }
            world.setBlockState(pos, state.with(MODE, mode));
            world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6F);
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hitResult);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SpikeTrapBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return (lvl, pos, blockState, blockEntity) -> SpikeTrapBlockEntity.tick(lvl, pos, blockState, (SpikeTrapBlockEntity) blockEntity);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(MODE));
    }

    public enum Mode implements StringIdentifiable {

        NORMAL("normal"),
        MASTER("master"),
        INVERTED_MASTER("master_inverted");

        Mode(String name) {
            this.name = name;
        }
        final String name;

        @Override
        public String asString() {
            return name;
        }

        public static Mode cycle(Mode mode) {
            return mode.ordinal() >= (values().length - 1) ? values()[0] : values()[mode.ordinal() + 1];
        }

        @Nullable
        public static Mode getFromName(String name) {
            for (Mode mode : values()) {
                if (mode.asString().equals(name))
                    return mode;
            }
            return null;
        }
    }
}
