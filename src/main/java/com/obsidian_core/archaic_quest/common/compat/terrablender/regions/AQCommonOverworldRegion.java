package com.obsidian_core.archaic_quest.common.compat.terrablender.regions;

import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.List;
import java.util.function.Consumer;

public class AQCommonOverworldRegion extends Region {

    public AQCommonOverworldRegion() {
        super(ArchaicQuest.resourceLoc("common_region"), RegionType.OVERWORLD, 4);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // AZTEC JUNGLE
        List<Climate.ParameterPoint> parameterPoints = new ParameterUtils.ParameterPointListBuilder()
                .continentalness(ParameterUtils.Continentalness.INLAND, ParameterUtils.Continentalness.FAR_INLAND, ParameterUtils.Continentalness.COAST)
                        .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                                .erosion(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1)
                                        .humidity(ParameterUtils.Humidity.HUMID, ParameterUtils.Humidity.WET)
                                                .weirdness(ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING)
                                                        .temperature(ParameterUtils.Temperature.WARM)
                                                                .build();

        parameterPoints.forEach((parameterPoint) -> addBiome(mapper, parameterPoint, AQBiomes.AZTEC_JUNGLE.getKey()));


        /*
        addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.DESERT, AQBiomes.AZTEC_JUNGLE.getKey());
            builder.replaceBiome(Biomes.JUNGLE, AQBiomes.AZTEC_JUNGLE.getKey());
            builder.replaceBiome(Biomes.SPARSE_JUNGLE, AQBiomes.AZTEC_JUNGLE.getKey());
        });

         */
    }
}
