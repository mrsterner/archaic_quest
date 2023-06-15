package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.EntityBlock;
import net.minecraft.world.world.block.SoundType;
import net.minecraft.world.world.block.entity.BlockEntity;
import net.minecraft.world.world.block.entity.BlockEntityTicker;
import net.minecraft.world.world.block.entity.BlockEntityType;
import net.minecraft.world.world.block.state.BlockBehaviour;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.material.Material;

import javax.annotation.Nullable;

public class FoundryBlock extends Block implements EntityBlock {

    public FoundryBlock() {
        super(BlockBehaviour.Settings.of(Material.STONE)
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
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> blockEntityType) {
        return null;
        //return (lvl, blockState, pos, blockEntity) -> FoundryBlockEntity.tick(lvl, blockState, pos, (FoundryBlockEntity) blockEntity);
    }
}
