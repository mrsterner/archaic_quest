package com.obsidian_core.archaic_quest.common.inventory.container;

import com.obsidian_core.archaic_quest.common.core.register.AQContainers;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class KnappingTableContainer extends ScreenHandler {

    private final Inventory container;
    @Nullable
    private final BlockPos openedPos;

    public KnappingTableContainer(int id, PlayerInventory inventory) {
        this(id, inventory, null);
    }

    public KnappingTableContainer(int id, PlayerInventory inventory, @Nullable BlockPos openedPos) {
        super(AQContainers.KNAPPING, id);
        this.openedPos = openedPos;
        container = new SimpleInventory(11) {
            @Override
            public void onClose(PlayerEntity player) {
                for (int slot = 0; slot < container.size(); slot++) {
                    if (!container.getStack(slot).isEmpty())
                        Block.dropStack(player.world, player.getBlockPos(), container.getStack(slot));
                }
            }
        };
        checkSize(container, 11);
        container.onOpen(inventory.player);

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

        // PlayerEntity inventory
        for(int l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 84));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 142));
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public ItemStack transferSlot(PlayerEntity player, int slotId) {
        ItemStack returnedItem = ItemStack.EMPTY;
        Slot slot = slots.get(slotId);

        if (slot != null && slot.hasStack()) {
            ItemStack slotItem = slot.getStack();
            returnedItem = slotItem.copy();

            if (slotId < container.size()) {
                if (!this.insertItem(slotItem, container.size(), slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.insertItem(slotItem, 9, 10, false)) {
                return ItemStack.EMPTY;
            }

            if (slotItem.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }
            else {
                slot.markDirty();
            }
        }
        return returnedItem;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return openedPos == null || player.squaredDistanceTo(openedPos.getX() + 0.5D, openedPos.getY() + 0.5D, openedPos.getZ() + 0.5D) < 64.0D;
    }

    private static class ResultSlot extends Slot {

        public ResultSlot(Inventory container, int id, int x, int y) {
            super(container, id, x, y);
        }

        @Override
        public boolean canInsert(ItemStack itemStack) {
            return false;
        }
    }
}
