package com.obsidian_core.archaic_quest.datagen;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.datagen.blockstate.AQBlockStateProvider;
import com.obsidian_core.archaic_quest.datagen.lang.AQLanguageProvider;
import com.obsidian_core.archaic_quest.datagen.loot_modifier.AQGlobalLootModifierProvider;
import com.obsidian_core.archaic_quest.datagen.loot_table.AQLootTableProvider;
import com.obsidian_core.archaic_quest.datagen.recipe.AQRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = ArchaicQuest.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGatherer {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();

        if (event.includeClient()) {
            dataGenerator.addProvider(new AQLanguageProvider(dataGenerator));
            dataGenerator.addProvider(new AQBlockStateProvider(dataGenerator, event.getExistingFileHelper()));
        }
        if (event.includeServer()) {
            dataGenerator.addProvider(new AQGlobalLootModifierProvider(dataGenerator));
            dataGenerator.addProvider(new AQRecipeProvider(dataGenerator));
            dataGenerator.addProvider(new AQLootTableProvider(dataGenerator));
        }
    }
}
