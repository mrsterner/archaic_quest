package com.obsidian_core.archaic_quest.common.network.message;

import com.obsidian_core.archaic_quest.common.network.work.ClientWork;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CUpdateSpikeTrap {

    public BlockPos doorPos;
    public boolean active;

    public S2CUpdateSpikeTrap(BlockPos doorPos, boolean active) {
        this.doorPos = doorPos;
        this.active = active;
    }

    public static void handle(S2CUpdateSpikeTrap message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> ClientWork.handleUpdateSpikeTrap(message));
        }
        context.setPacketHandled(true);
    }

    public static void encode(S2CUpdateSpikeTrap message, FriendlyByteBuf byteBuf) {
        byteBuf.writeBlockPos(message.doorPos);
        byteBuf.writeBoolean(message.active);
    }

    public static S2CUpdateSpikeTrap decode(FriendlyByteBuf byteBuf) {
        return new S2CUpdateSpikeTrap(byteBuf.readBlockPos(), byteBuf.readBoolean());
    }
}
