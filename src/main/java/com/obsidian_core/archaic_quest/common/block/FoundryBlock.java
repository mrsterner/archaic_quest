package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class FoundryBlock extends Block {

    public FoundryBlock() {
        super(AbstractBlock.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops()
                .randomTicks()
                .sound(SoundType.STONE)
                .strength(2.0F));
    }

    @Nullable
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AztecDungeonDoorTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
