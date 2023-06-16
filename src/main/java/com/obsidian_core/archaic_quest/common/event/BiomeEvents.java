package com.obsidian_core.archaic_quest.common.event;

public class BiomeEvents {

    // TODO - Biome Modifiers
    /*
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onBiomeLoad(BiomeLoad event) {
        Identifier biomeName = event.getName();

        if (biomeName == null)
            return;

        BiomeGenerationSettingsBuilder generationSettings = event.getGeneration();
        MobSpawnInfoBuilder spawnInfo = event.getSpawns();

        if (BiomeDictionary.hasType(getRegistryKey(biomeName), BiomeDictionary.Type.OVERWORLD)) {
            generationSettings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, AQConfiguredFeatures.TIN_ORE);
            generationSettings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, AQConfiguredFeatures.SILVER_ORE);
            generationSettings.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, AQConfiguredFeatures.GRANITE_QUARTZ_ORE);
        }
    }

    private static RegistryKey<Biome> getRegistryKey(Identifier biomeName) {
        return RegistryKey.create(ForgeRegistries.Keys.BIOMES, biomeName);
    }

     */
}
