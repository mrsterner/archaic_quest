package com.obsidian_core.archaic_quest.common.network.message;

import com.obsidian_core.archaic_quest.common.network.work.ClientWork;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CUpdateDoorState {

    public BlockPos doorPos;
    public int doorState;

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

    public static void encode(S2CUpdateDoorState message, FriendlyByteBuf byteBuf) {
        byteBuf.writeBlockPos(message.doorPos);
        byteBuf.writeInt(message.doorState);
    }

    public static S2CUpdateDoorState decode(FriendlyByteBuf byteBuf) {
        return new S2CUpdateDoorState(byteBuf.readBlockPos(), byteBuf.readInt());
    }
}
