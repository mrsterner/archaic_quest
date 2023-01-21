package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AQBiomes {

    public static final DeferredRegister<Biome> REGISTRY = DeferredRegister.create(ForgeRegistries.BIOMES, ArchaicQuest.MODID);


    public static final RegistryObject<Biome> AZTEC_JUNGLE = register("aztec_jungle", () -> {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.baseJungleSpawns(spawnSettings);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addSparseJungleTrees(generationSettings);
        BiomeDefaultFeatures.addSparseJungleMelons(generationSettings);
        BiomeDefaultFeatures.addWarmFlowers(generationSettings);
        BiomeDefaultFeatures.addJungleGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

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

    protected static int calculateSkyColor(float f) {
        float $$1 = f / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }
}
