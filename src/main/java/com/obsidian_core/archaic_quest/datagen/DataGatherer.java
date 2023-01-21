package com.obsidian_core.archaic_quest.datagen;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.datagen.blockstate.AQBlockStateProvider;
import com.obsidian_core.archaic_quest.datagen.lang.AQLanguageProvider;
import com.obsidian_core.archaic_quest.datagen.loot_modifier.AQGlobalLootModifierProvider;
import com.obsidian_core.archaic_quest.datagen.loot_table.AQLootTableProvider;
import com.obsidian_core.archaic_quest.datagen.model.AQItemModelProvider;
import com.obsidian_core.archaic_quest.datagen.recipe.AQRecipeProvider;
import com.obsidian_core.archaic_quest.datagen.tag.AQBlockTagProvider;
import com.obsidian_core.archaic_quest.datagen.tag.AQItemTagProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArchaicQuest.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGatherer {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            dataGenerator.addProvider(true, new AQLanguageProvider(dataGenerator));
            dataGenerator.addProvider(true, new AQBlockStateProvider(dataGenerator, fileHelper));
            dataGenerator.addProvider(true, new AQItemModelProvider(dataGenerator, fileHelper));
        }
        if (event.includeServer()) {
            dataGenerator.addProvider(true, new AQGlobalLootModifierProvider(dataGenerator));
            dataGenerator.addProvider(true, new AQRecipeProvider(dataGenerator));
            dataGenerator.addProvider(true, new AQLootTableProvider(dataGenerator));
            AQBlockTagProvider blockTagProvider = new AQBlockTagProvider(dataGenerator, fileHelper);
            dataGenerator.addProvider(true, blockTagProvider);
            dataGenerator.addProvider(true, new AQItemTagProvider(dataGenerator, blockTagProvider, fileHelper));
        }
    }
}
