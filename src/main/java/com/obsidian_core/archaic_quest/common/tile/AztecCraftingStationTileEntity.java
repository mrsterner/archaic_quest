package com.obsidian_core.archaic_quest.common.tile;

import com.obsidian_core.archaic_quest.common.misc.TranslationReferences;
import com.obsidian_core.archaic_quest.common.register.AQTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class AztecCraftingStationTileEntity extends TileEntity implements INamedContainerProvider {

    public AztecCraftingStationTileEntity() {
        super(AQTileEntities.AZTEC_CRAFTING_STATION.get());
    }

    @Override
    public ITextComponent getDisplayName() {
        return TranslationReferences.AZTEC_CRAFTING_STATION_CONTAINER_NAME;
    }

    @Nullable
    @Override
    public Container createMenu(int containerCounter, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return null;
    }
}
