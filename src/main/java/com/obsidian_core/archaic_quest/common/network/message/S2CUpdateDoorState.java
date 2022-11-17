package com.obsidian_core.archaic_quest.common.network.message;

import com.obsidian_core.archaic_quest.common.network.work.ClientWork;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CUpdateDoorState {

    public final BlockPos doorPos;
    public final int doorState;

    public S2CUpdateDoorState(BlockPos doorPos, int doorState) {
        this.doorPos = doorPos;
        this.doorState = doorState;
    }

    public static void handle(S2CUpdateDoorState message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> ClientWork.handleUpdateDoorState(message));
        }
        context.setPacketHandled(true);
    }

    public static void encode(S2CUpdateDoorState message, PacketBuffer packetBuffer) {
        packetBuffer.writeBlockPos(message.doorPos);
        packetBuffer.writeInt(message.doorState);
    }

    public static S2CUpdateDoorState decode(PacketBuffer packetBuffer) {
        return new S2CUpdateDoorState(packetBuffer.readBlockPos(), packetBuffer.readInt());
    }
}
