package com.obsidian_core.archaic_quest.common.worldgen.biome.modifier;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.world.worldgen.GenerationStep;

public enum ModOre implements StringRepresentable {
    TIN("tin"),
    SILVER("silver"),
    GRANITE_QUARTZ("granite_quartz"),
    JADE("jade"),
    TURQUOISE("turquoise");

    ModOre(String name) {
        this.name = name;
    }

    public static final Codec<ModOre> CODEC = StringRepresentable.fromEnum(ModOre::values);
    private final String name;

    @Override
    public String getSerializedName() {
        return name;
    }
}
