package com.obsidian_core.archaic_quest.common.worldgen.feature;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class AQConfiguredFeatures {

    public static Holder<ConfiguredFeature<OreConfiguration, ?>> TIN_ORE;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> SILVER_ORE;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> GRANITE_QUARTZ_ORE;

    public static void register() {
        TIN_ORE = FeatureUtils.register(regName("ore_tin"), Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, AQBlocks.TIN_ORE.get().defaultBlockState(), 9));
        SILVER_ORE = FeatureUtils.register(regName("ore_silver"), Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, AQBlocks.SILVER_ORE.get().defaultBlockState(), 9));
        GRANITE_QUARTZ_ORE = FeatureUtils.register(regName("ore_granite_quartz"), Feature.ORE, new OreConfiguration(AQFillerBlockTypes.GRANITE, AQBlocks.GRANITE_QUARTZ_ORE.get().defaultBlockState(), 9));
   }

    private static String regName(String name) {
        return new ResourceLocation(ArchaicQuest.MODID, name).toString();
    }
}
