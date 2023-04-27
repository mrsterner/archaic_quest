package com.obsidian_core.archaic_quest.common.network.work;

import com.obsidian_core.archaic_quest.common.block.SpikeTrapBlock;
import com.obsidian_core.archaic_quest.common.blockentity.SpikeTrapBlockEntity;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateDoorState;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateSpikeTrap;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateSpikeTrapMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import static com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity.DoorState;

public class ClientWork {

    public static void handleUpdateDoorState(S2CUpdateDoorState message) {
        BlockPos pos = message.doorPos;
        DoorState doorState = DoorState.byId(message.doorState);
        ClientLevel level = Minecraft.getInstance().level;

        if (level == null) return;

        BlockEntity blockEntity = level.getExistingBlockEntity(pos);

        if (blockEntity instanceof AztecDungeonDoorBlockEntity dungeonDoor) {
            if (doorState == null) return;
            dungeonDoor.setDoorState(doorState);
        }
    }

    public static void handleUpdateSpikeTrap(S2CUpdateSpikeTrap message) {
        BlockPos pos = message.doorPos;
        boolean active = message.active;
        ClientLevel level = Minecraft.getInstance().level;

        if (level == null) return;

        BlockEntity blockEntity = level.getExistingBlockEntity(pos);

        if (blockEntity instanceof SpikeTrapBlockEntity spikeTrap) {
            spikeTrap.setActive(active);
        }
    }

    public static void handleUpdateSpikeTrap(S2CUpdateSpikeTrapMode message) {
        BlockPos pos = message.doorPos;
        int mode = message.mode;
        ClientLevel level = Minecraft.getInstance().level;

        if (level == null) return;

        BlockEntity blockEntity = level.getExistingBlockEntity(pos);

        if (blockEntity instanceof SpikeTrapBlockEntity spikeTrap) {
            spikeTrap.setMode(SpikeTrapBlock.Mode.values()[mode]);
        }
    }
}
