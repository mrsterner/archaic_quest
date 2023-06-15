package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class FoundryBlock extends Block implements EntityBlock {

    public FoundryBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops()
                .randomTicks()
                .sound(SoundType.STONE)
                .strength(2.0F));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World level, BlockState state, BlockEntityType<T> blockEntityType) {
        return null;
        //return (lvl, blockState, pos, blockEntity) -> FoundryBlockEntity.tick(lvl, blockState, pos, (FoundryBlockEntity) blockEntity);
    }
}
