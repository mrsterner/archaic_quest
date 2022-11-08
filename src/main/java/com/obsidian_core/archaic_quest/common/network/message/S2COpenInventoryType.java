package com.obsidian_core.archaic_quest.common.network.message;

import com.obsidian_core.archaic_quest.common.network.work.ClientWork;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class S2COpenInventoryType {

    public final UUID uuid;
    public final int containerId;
    public final int inventoryType;

    public S2COpenInventoryType(UUID playerUUID, int containerId, int inventoryType) {
        uuid = playerUUID;
        this.containerId = containerId;
        this.inventoryType = inventoryType;
    }

    public static void handle(S2COpenInventoryType message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> ClientWork.handleOpenInventoryType(message));
        }
        context.setPacketHandled(true);
    }

    public static S2COpenInventoryType decode(PacketBuffer buffer) {
        return new S2COpenInventoryType(buffer.readUUID(), buffer.readInt(), buffer.readInt());
    }

    public static void encode(S2COpenInventoryType message, PacketBuffer buffer) {
        buffer.writeUUID(message.uuid);
        buffer.writeInt(message.containerId);
        buffer.writeInt(message.inventoryType);
    }
}
