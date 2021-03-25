package com.obsidian_core.archaic_quest.datagen.loot_modifier;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.loot_modifier.LootModifierAdd;
import com.obsidian_core.archaic_quest.common.register.AQGlobalLootModifiers;
import com.obsidian_core.archaic_quest.common.register.AQItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class AQGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public AQGlobalLootModifierProvider(DataGenerator gen) {
        super(gen, ArchaicQuest.MODID);
    }

    @Override
    protected void start() {
        this.add("cobblestone_modifier", AQGlobalLootModifiers.ADD_ITEM_MODIFIER.get(),
                new LootModifierAdd(new ILootCondition[] {RandomChance.randomChance(0.4F).build()},
                        new ResourceLocation("blocks/cobblestone"),
                        AQItems.PEBBLE.get(),
                        9,
                        5));
    }
}
