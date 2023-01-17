package com.obsidian_core.archaic_quest.common.tile;

import com.obsidian_core.archaic_quest.common.register.AQTileEntities;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class FoundryTileEntity extends TileEntity implements ITickableTileEntity {

    public FoundryTileEntity() {
        super(AQTileEntities.FOUNDRY.get());
    }

    @Override
    public void tick() {

    }
}
