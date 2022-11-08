package com.obsidian_core.archaic_quest.common.util;

import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
import com.obsidian_core.archaic_quest.common.network.NetworkHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;

public class SimpleCraftingContainers {

    public static final int KNAPPING_ID = 0;

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public static void openContainerForPlayer(ServerPlayerEntity player, int inventoryType) {
        if (player.containerMenu != player.inventoryMenu) {
            player.closeContainer();
        }
        player.nextContainerCounter();
        Container container;

        switch (inventoryType) {
            case KNAPPING_ID:
                container = new KnappingTableContainer(player.containerCounter, player.inventory);
                break;
            default:
                container = null;
                break;
        }
        if (container == null)
            return;

        NetworkHelper.openInventoryType(player, container.containerId, inventoryType);
        player.containerMenu = container;
        container.addSlotListener(player);

        MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(player, player.containerMenu));
    }
}
