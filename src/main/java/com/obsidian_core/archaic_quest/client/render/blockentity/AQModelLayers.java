package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class AQModelLayers {

    public static final EntityModelLayer AZTEC_CRAFTING_STATION = create("aztec_crafting_station");
    public static final EntityModelLayer AZTEC_DUNGEON_DOOR = create("aztec_dungeon_door");
    public static final EntityModelLayer AZTEC_THRONE = create("aztec_throne");
    public static final EntityModelLayer SPIKE_TRAP = create("spike_trap");
    public static final EntityModelLayer SPIKE_TRAP_OVERLAY = create("spike_trap_overlay");
    public static final EntityModelLayer AZTEC_DUNGEON_CHEST = create("aztec_dungeon_chest");


    public static final EntityModelLayer TLATLAOMI = create("tlatlaomi");



    private static EntityModelLayer create(String path) {
        return create(path, "main");
    }

    private static EntityModelLayer create(String path, String layerName) {
        return new EntityModelLayer(ArchaicQuest.id(path), layerName);
    }

    public static void init() {}

    private AQModelLayers() {}
}
