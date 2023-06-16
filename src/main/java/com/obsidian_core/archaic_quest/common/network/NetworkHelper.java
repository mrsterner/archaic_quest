package com.obsidian_core.archaic_quest.common.network;

import com.obsidian_core.archaic_quest.common.block.SpikeTrapBlock;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateDoorState;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateSpikeTrap;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateSpikeTrapMode;
import net.minecraft.core.BlockPos;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class NetworkHelper {

    /**
     * Sends a message from server to client
     * to update an Aztec Dungeon Door's door state.
     */
    public static void updateDoorState(ServerWorld world, BlockPos pos, AztecDungeonDoorBlockEntity.DoorState state) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            PacketHandler.sendToClient(new S2CUpdateDoorState(pos, state.ordinal()), player);
        }
    }

    /**
     * Sends a message from server to client
     * to update a Spike Trap.
     */
    public static void updateSpikeTrap(ServerWorld world, BlockPos pos, boolean active) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            PacketHandler.sendToClient(new S2CUpdateSpikeTrap(pos, active), player);
        }
    }

    public static void updateSpikeTrap(ServerWorld world, BlockPos pos, SpikeTrapBlock.Mode mode) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            PacketHandler.sendToClient(new S2CUpdateSpikeTrapMode(pos, mode.ordinal()), player);
        }
    }
}
