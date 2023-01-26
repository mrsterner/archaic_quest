package com.obsidian_core.archaic_quest.common.block.data;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public enum ThroneType {

    THRONE("throne"),
    MOSSY_THRONE("mossy_throne");


    ThroneType(@Nonnull String textureName) {
        this.texture = ArchaicQuest.resourceLoc("textures/tile/throne/" + textureName + ".png");
    }
    private final ResourceLocation texture;

    public ResourceLocation getTextureLocation() {
        return texture;
    }
}
