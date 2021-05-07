package com.obsidian_core.archaic_quest.common.worldgen.feature;

import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class AQConfiguredFeatures {

    public static ConfiguredFeature<?, ?> TIN_ORE;
    public static ConfiguredFeature<?, ?> SILVER_ORE;

    public static void register() {
        TIN_ORE = register("archaic_quest:ore_tin", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AQBlocks.TIN_ORE.get().defaultBlockState(), 9)).range(64).squared().count(15));
        SILVER_ORE = register("archaic_quest:silver_copper", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AQBlocks.SILVER_ORE.get().defaultBlockState(), 9)).range(64).squared().count(15));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }
}
