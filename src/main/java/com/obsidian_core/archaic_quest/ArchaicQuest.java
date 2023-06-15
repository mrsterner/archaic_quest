package com.obsidian_core.archaic_quest;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ArchaicQuest implements ModInitializer {
    public static final String MODID = "archaic_quest";

    public static Identifier id(String name){
        return new Identifier(MODID, name);
    }

    @Override
    public void onInitialize() {

    }
}
