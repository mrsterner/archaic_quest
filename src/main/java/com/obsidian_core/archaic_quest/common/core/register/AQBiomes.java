package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.worldgen.feature.AQConfiguredFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.MathHelper;
import net.minecraft.world.world.biome.*;
import net.minecraft.world.world.worldgen.GenerationStep;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AQBiomes {

    public static final DeferredRegister<Biome> REGISTRY = DeferredRegister.create(ForgeRegistries.BIOMES, ArchaicQuest.MODID);


    /*
      RAW_GENERATION("raw_generation"),
      LAKES("lakes"),
      LOCAL_MODIFICATIONS("local_modifications"),
      UNDERGROUND_STRUCTURES("underground_structures"),
      SURFACE_STRUCTURES("surface_structures"),
      STRONGHOLDS("strongholds"),
      UNDERGROUND_ORES("underground_ores"),
      UNDERGROUND_DECORATION("underground_decoration"),
      FLUID_SPRINGS("fluid_springs"),
      VEGETAL_DECORATION("vegetal_decoration"),
      TOP_LAYER_MODIFICATION("top_layer_modification");
     */

    /**
     * IMPORTANT!!!<
     * <p></p>
     * The order in which features are added to a biome is mega important.
     * Make sure our biomes generate stuff in the same EXACT order as vanilla biomes.
     * Let us say that the Aztec Jungle generates next to a vanilla Sparse Jungle,
     * we would crash if our jungle biome generates a melon feature before or after the Sparse Jungle does it.
     * <p></p>
     * It is smart to look to vanilla biomes for which order to do things, as other mods
     * should be doing the same.
     */

    public static final RegistryObject<Biome> AZTEC_JUNGLE = register("aztec_jungle", () -> {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.baseJungleSpawns(spawnSettings);

        globalOverworldGeneration(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addSparseJungleTrees(generationSettings);
        //generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AQConfiguredFeatures.PLACED_AZTEC_JUNGLE_TREES);
        BiomeDefaultFeatures.addWarmFlowers(generationSettings);
        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addSparseJungleMelons(generationSettings);


        return new Biome.BiomeBuilder()
                .generationSettings(generationSettings.build())
                .mobSpawnSettings(spawnSettings.build())
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(0.95F)
                .downfall(0.9F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(calculateSkyColor(0.95F))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JUNGLE_AND_FOREST))
                        .build())
                .build();
    });


    private static RegistryObject<Biome> register(String name, Supplier<Biome> biomeSupplier) {
        return REGISTRY.register(name, biomeSupplier);
    }
    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    protected static int calculateSkyColor(float f) {
        float $$1 = f / 3.0F;
        $$1 = MathHelper.clamp($$1, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }
}
