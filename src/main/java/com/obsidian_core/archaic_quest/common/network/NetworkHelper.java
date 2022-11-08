package com.obsidian_core.archaic_quest.common.network;

import com.obsidian_core.archaic_quest.common.network.message.S2COpenInventoryType;
import net.minecraft.entity.player.ServerPlayerEntity;

import javax.annotation.Nonnull;

public class NetworkHelper {

    public static void openInventoryType(@Nonnull ServerPlayerEntity player, int containerId, int inventoryType) {
        PacketHandler.sendToClient(new S2COpenInventoryType(player.getUUID(), containerId, inventoryType), player);
    }
}
