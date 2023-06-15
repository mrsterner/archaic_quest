package com.obsidian_core.archaic_quest.datagen.loot_modifier;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.loot_modifier.LootModifierAdd;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.Identifier;
import net.minecraft.world.world.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.world.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class AQGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public AQGlobalLootModifierProvider(DataGenerator gen) {
        super(gen, ArchaicQuest.MODID);
    }

    @Override
    protected void start() {
        this.add("cobblestone_modifier", new LootModifierAdd(
                new LootItemCondition[] {MatchTool.toolMatches(ItemPredicate.Builder.item()).build()},
                AQItems.PEBBLE.get(),
                9,
                5,
                new Identifier("blocks/cobblestone")));
    }
}
