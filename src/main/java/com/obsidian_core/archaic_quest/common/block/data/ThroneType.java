package com.obsidian_core.archaic_quest.common.block.data;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public enum ThroneType {

    THRONE("throne"),
    MOSSY_THRONE("mossy_throne");


    ThroneType(@NotNull String name) {
        this.name = name;
        this.texture = ArchaicQuest.id("textures/tile/throne/" + name + ".png");
    }
    private final String name;
    private final Identifier texture;

    public Identifier getTextureLocation() {
        return texture;
    }

    public String getName() {
        return name;
    }

    public static ThroneType getFromName(String name) {
        if (name == null)
            return null;

        for (ThroneType type : ThroneType.values()) {
            if (type.getName().equals(name))
                return type;
        }
        return null;
    }
}
