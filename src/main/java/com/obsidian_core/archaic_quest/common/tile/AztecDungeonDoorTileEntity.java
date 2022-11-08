package com.obsidian_core.archaic_quest.common.tile;

import com.obsidian_core.archaic_quest.common.register.AQTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class AztecDungeonDoorTileEntity extends TileEntity implements ITickableTileEntity {

    private DoorState doorState = DoorState.STAND_BY;
    private int doorPosition = 0;


    public AztecDungeonDoorTileEntity() {
        super(AQTileEntities.AZTEC_DUNGEON_DOOR.get());
    }

    @Override
    public void tick() {
        if (level != null) {
            doorState = DoorState.STAND_BY;

            if (!level.getEntitiesOfClass(PlayerEntity.class, new AxisAlignedBB(getBlockPos()).inflate(4.0D)).isEmpty()) {
                if (doorPosition < 60) {
                    ++doorPosition;
                    doorState = DoorState.OPENING;
                }
            }
            else {
                if (doorPosition > 0) {
                    --doorPosition;
                    doorState = DoorState.CLOSING;
                }
            }
        }
    }

    public DoorState getDoorState() {
        return doorState;
    }

    public int getDoorPosition() {
        return doorPosition;
    }

    public enum DoorState {
        OPENING,
        CLOSING,
        STAND_BY
    }
}
