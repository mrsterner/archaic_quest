package com.obsidian_core.archaic_quest.common.block.data;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public enum DungeonDoorType {

    AZTEC_DOOR_0(false, "aztec_dungeon_door_0"),
    AZTEC_DOOR_1(false, "aztec_dungeon_door_1"),
    AZTEC_DOOR_FRAME_0(true, "aztec_dungeon_door_frame_0"),
    AZTEC_DOOR_FRAME_1(true, "aztec_dungeon_door_frame_1");



    DungeonDoorType(boolean isFrame, @Nonnull String textureName) {
        this.isFrame = isFrame;
        this.texture = ArchaicQuest.resourceLoc("textures/tile/dungeon_door/" + textureName + ".png");
    }

    private final boolean isFrame;
    private final ResourceLocation texture;

    public boolean isFrame() {
        return isFrame;
    }

    public ResourceLocation getTextureLocation() {
        return texture;
    }
}
