package com.obsidian_core.archaic_quest.common.network.work;

import com.obsidian_core.archaic_quest.client.screen.KnappingTableScreen;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
import com.obsidian_core.archaic_quest.common.misc.TranslationReferences;
import com.obsidian_core.archaic_quest.common.network.message.S2COpenInventoryType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;

import static com.obsidian_core.archaic_quest.common.util.SimpleCraftingContainers.KNAPPING_ID;

public class ClientWork {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public static void handleOpenInventoryType(S2COpenInventoryType message) {
        ClientPlayerEntity player = Minecraft.getInstance().player;

        if (player == null || !(player.getUUID().equals(message.uuid)))
            return;

        ClientWorld world = Minecraft.getInstance().level;

        if (world == null)
            return;

        PlayerInventory playerInventory = Minecraft.getInstance().player.inventory;

        switch (message.inventoryType) {
            case KNAPPING_ID:
                KnappingTableContainer container = new KnappingTableContainer(message.containerId, playerInventory);
                Minecraft.getInstance().player.containerMenu = container;
                Minecraft.getInstance().setScreen(new KnappingTableScreen(container, playerInventory, TranslationReferences.KNAPPING_TABLE_CONTAINER_NAME));
                break;
            default:
                ArchaicQuest.LOGGER.error("Attempted to open an invalid inventory type. Type id: {}", message.inventoryType);
                break;
        }
    }
}
