package com.obsidian_core.archaic_quest.common.event;

import com.obsidian_core.archaic_quest.common.worldgen.feature.AQConfiguredFeatures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.client.model.generators.loaders.OBJLoaderBuilder;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeEvents {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onBiomeLoad(BiomeLoadingEvent event) {
        ResourceLocation biomeName = event.getName();

        if (biomeName == null)
            return;

        BiomeGenerationSettingsBuilder generationSettings = event.getGeneration();
        MobSpawnInfoBuilder spawnInfo = event.getSpawns();

        if (BiomeDictionary.hasType(getRegistryKey(biomeName), BiomeDictionary.Type.OVERWORLD)) {
            generationSettings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, AQConfiguredFeatures.TIN_ORE);
            generationSettings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, AQConfiguredFeatures.SILVER_ORE);
        }
    }

    private static RegistryKey<Biome> getRegistryKey(ResourceLocation biomeName) {
        return RegistryKey.create(ForgeRegistries.Keys.BIOMES, biomeName);
    }
}
