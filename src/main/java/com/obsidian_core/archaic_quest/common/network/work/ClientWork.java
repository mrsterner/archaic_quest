package com.obsidian_core.archaic_quest.common.network.work;

import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateDoorState;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import static com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity.DoorState;

public class ClientWork {

    public static void handleUpdateDoorState(S2CUpdateDoorState message) {
        BlockPos pos = message.doorPos;
        DoorState doorState = DoorState.byId(message.doorState);
        ClientWorld world = Minecraft.getInstance().level;

        if (world == null) return;

        TileEntity tileEntity = world.getBlockEntity(pos);

        if (tileEntity instanceof AztecDungeonDoorTileEntity) {
            AztecDungeonDoorTileEntity dungeonDoor = (AztecDungeonDoorTileEntity) tileEntity;

            if (doorState == null) return;

            dungeonDoor.setDoorState(doorState);
        }
    }
}
