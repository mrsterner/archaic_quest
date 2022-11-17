package com.obsidian_core.archaic_quest.common.network;

import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateDoorState;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class NetworkHelper {

    /**
     * Sends a message from server to client
     * to update an Aztec Dungeon Door's door state.
     */
    public static void updateDoorState(ServerWorld world, BlockPos pos, AztecDungeonDoorTileEntity.DoorState state) {
        for (ServerPlayerEntity player : world.players()) {
            PacketHandler.sendToClient(new S2CUpdateDoorState(pos, state.ordinal()), player);
        }
    }
}
