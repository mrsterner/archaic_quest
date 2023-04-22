package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import com.obsidian_core.archaic_quest.common.blockentity.SpikeTrapBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQSoundEvents;
import com.obsidian_core.archaic_quest.common.network.NetworkHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SpikeTrapBlock extends Block implements EntityBlock {


    private static final VoxelShape shape = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

    public SpikeTrapBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean flag) {
        if (!level.isClientSide) {
            BlockEntity be = level.getExistingBlockEntity(pos);

            if (be instanceof SpikeTrapBlockEntity spikeTrap) {
                if (level.hasNeighborSignal(pos)) {
                    if (!spikeTrap.isActive()) {
                        spikeTrap.setActive(true);
                        NetworkHelper.updateSpikeTrap((ServerLevel) level, pos, spikeTrap.isActive());
                    }
                }
                else {
                    if (spikeTrap.isActive()) {
                        spikeTrap.setActive(false);
                        NetworkHelper.updateSpikeTrap((ServerLevel) level, pos, spikeTrap.isActive());
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SpikeTrapBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (lvl, pos, blockState, blockEntity) -> SpikeTrapBlockEntity.tick(lvl, pos, blockState, (SpikeTrapBlockEntity) blockEntity);
    }
}
