package com.obsidian_core.archaic_quest.common.compat.terrablender.regions;

import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.world.biome.Biome;
import net.minecraft.world.world.biome.Biomes;
import net.minecraft.world.world.biome.Climate;
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

        addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.JUNGLE, AQBiomes.AZTEC_JUNGLE.getKey());
            builder.replaceBiome(Biomes.SPARSE_JUNGLE, AQBiomes.AZTEC_JUNGLE.getKey());
            builder.replaceBiome(Biomes.BAMBOO_JUNGLE, AQBiomes.AZTEC_JUNGLE.getKey());
            builder.replaceBiome(Biomes.DESERT, AQBiomes.AZTEC_JUNGLE.getKey());
            builder.replaceBiome(Biomes.PLAINS, AQBiomes.AZTEC_JUNGLE.getKey());
        });
    }
}
