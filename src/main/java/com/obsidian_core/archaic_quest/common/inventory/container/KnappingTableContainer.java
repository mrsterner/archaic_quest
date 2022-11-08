package com.obsidian_core.archaic_quest.common.inventory.container;

import com.obsidian_core.archaic_quest.common.register.AQContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;

public class KnappingTableContainer extends Container {

    private final IInventory container;

    public KnappingTableContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(3));
    }

    public KnappingTableContainer(int id, PlayerInventory playerInventory, IInventory inventory) {
        super(AQContainers.KNAPPING.get(), id);
        checkContainerSize(inventory, 3);
        this.container = inventory;
        container.startOpen(playerInventory.player);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return false;
    }
}
