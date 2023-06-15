package com.obsidian_core.archaic_quest.common.block.data;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public enum DungeonDoorType {

    AZTEC_DOOR_0(false, "aztec_dungeon_door_0"),
    AZTEC_DOOR_1(false, "aztec_dungeon_door_1"),
    AZTEC_DOOR_FRAME_0(true, "aztec_dungeon_door_frame_0"),
    AZTEC_DOOR_FRAME_1(true, "aztec_dungeon_door_frame_1");



    DungeonDoorType(boolean isFrame, @NotNull String textureName) {
        this.isFrame = isFrame;
        this.texture = ArchaicQuest.id("textures/tile/dungeon_door/" + textureName + ".png");
    }

    private final boolean isFrame;
    private final Identifier texture;

    public boolean isFrame() {
        return isFrame;
    }

    public Identifier getTextureLocation() {
        return texture;
    }
}
