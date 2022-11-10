package com.obsidian_core.archaic_quest.common.tile;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.register.AQTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class AztecDungeonDoorTileEntity extends TileEntity implements ITickableTileEntity {

    private DoorState doorState = DoorState.STAND_BY;
    private int doorPosition = 0;
    private int previousDoorPos = doorPosition;


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
            if (doorPosition >= 60 && doorPosition != previousDoorPos) {
                toggleDoorBlocks(true);
            }
            else if (doorPosition <= 0 && doorPosition != previousDoorPos) {
                toggleDoorBlocks(false);
            }
        }
    }

    /**
     * Toggles this dungeon door tile entity's
     * physical blocks' collision.
     *
     * @param open Whether the door should be opened or closed.
     */
    private void toggleDoorBlocks(boolean open) {
        level.setBlock(getBlockPos(), level.getBlockState(getBlockPos()).setValue(AztecDungeonDoorBlock.HAS_COLLISION, open), 2);
        if (level.getBlockState(getBlockPos().above()).is(AQBlocks.AZTEC_DUNGEON_DOOR.get())) {
            level.setBlock(getBlockPos().above(), level.getBlockState(getBlockPos().above()).setValue(AztecDungeonDoorBlock.HAS_COLLISION, open), 2);
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
