package com.obsidian_core.archaic_quest.common.network;

import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateDoorState;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class NetworkHelper {

    /**
     * Sends a message from server to client
     * to update an Aztec Dungeon Door's door state.
     */
    public static void updateDoorState(ServerLevel world, BlockPos pos, AztecDungeonDoorBlockEntity.DoorState state) {
        for (ServerPlayer player : world.players()) {
            PacketHandler.sendToClient(new S2CUpdateDoorState(pos, state.ordinal()), player);
        }
    }
}
