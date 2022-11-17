package com.obsidian_core.archaic_quest.common.inventory.container;

import com.obsidian_core.archaic_quest.common.register.AQContainers;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class KnappingTableContainer extends Container {

    private final IInventory container;
    @Nullable
    private final BlockPos openedPos;

    public KnappingTableContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, null);
    }

    public KnappingTableContainer(int id, PlayerInventory playerInventory, @Nullable BlockPos openedPos) {
        super(AQContainers.KNAPPING.get(), id);
        this.openedPos = openedPos;
        container = new Inventory(11) {
            @Override
            public void stopOpen(PlayerEntity player) {
                for (int slot = 0; slot < container.getContainerSize(); slot++) {
                    if (!container.getItem(slot).isEmpty())
                        Block.popResource(player.level, player.blockPosition(), container.getItem(slot));
                }
            }
        };
        checkContainerSize(container, 11);
        container.startOpen(playerInventory.player);

        // Crafting slots
        int slotId = 0;
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(container, slotId, 62 + j * 18, i * 18 + 17));
                ++slotId;
            }
        }
        // Tool slot
        this.addSlot(new Slot(container, 9, 26, 53));
        // Result slot
        this.addSlot(new ResultSlot(container, 10, 143, 35));

        // Player inventory
        for(int l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 84));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int slotId) {
        ItemStack returnedItem = ItemStack.EMPTY;
        Slot slot = slots.get(slotId);

        if (slot != null && slot.hasItem()) {
            ItemStack slotItem = slot.getItem();
            returnedItem = slotItem.copy();

            if (slotId < container.getContainerSize()) {
                if (!this.moveItemStackTo(slotItem, container.getContainerSize(), slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(slotItem, 9, 10, false)) {
                return ItemStack.EMPTY;
            }

            if (slotItem.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            else {
                slot.setChanged();
            }
        }
        return returnedItem;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return openedPos == null || player.distanceToSqr(openedPos.getX() + 0.5D, openedPos.getY() + 0.5D, openedPos.getZ() + 0.5D) < 64.0D;
    }

    private static class ResultSlot extends Slot {

        public ResultSlot(IInventory inventory, int id, int x, int y) {
            super(inventory, id, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return false;
        }
    }
}
