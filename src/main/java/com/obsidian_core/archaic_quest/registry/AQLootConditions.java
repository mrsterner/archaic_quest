package com.obsidian_core.archaic_quest.registry;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.registry.Registry;

public interface AQLootConditions {

    //public static final LootConditionType E = register("e", new ScionIsPlayerLootCondition.Serializer());

    static <T extends LootCondition> LootConditionType register(String id, JsonSerializer<T> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, ArchaicQuest.id(id), new LootConditionType(serializer));
    }

    static void init() {

    }
}
