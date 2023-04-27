package com.obsidian_core.archaic_quest.common.network;

import com.obsidian_core.archaic_quest.common.block.SpikeTrapBlock;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateDoorState;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateSpikeTrap;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateSpikeTrapMode;
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

    /**
     * Sends a message from server to client
     * to update a Spike Trap.
     */
    public static void updateSpikeTrap(ServerLevel world, BlockPos pos, boolean active) {
        for (ServerPlayer player : world.players()) {
            PacketHandler.sendToClient(new S2CUpdateSpikeTrap(pos, active), player);
        }
    }

    public static void updateSpikeTrap(ServerLevel world, BlockPos pos, SpikeTrapBlock.Mode mode) {
        for (ServerPlayer player : world.players()) {
            PacketHandler.sendToClient(new S2CUpdateSpikeTrapMode(pos, mode.ordinal()), player);
        }
    }
}
