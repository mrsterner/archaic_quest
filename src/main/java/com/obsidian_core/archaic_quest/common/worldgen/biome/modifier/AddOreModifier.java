package com.obsidian_core.archaic_quest.common.worldgen.biome.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.obsidian_core.archaic_quest.common.core.register.AQBiomeModifiers;
import com.obsidian_core.archaic_quest.common.worldgen.feature.AQConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.RegistryObject;

public record AddOreModifier(HolderSet<Biome> biomes, GenerationStep.Decoration step, ModOre ore) implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && biomes.contains(biome)) {
            BiomeGenerationSettingsBuilder settings = builder.getGenerationSettings();
            RegistryObject<PlacedFeature> oreFeature = AQConfiguredFeatures.getOreForType(ore);

            if (oreFeature != null) {
                settings.addFeature(step, oreFeature.getHolder().orElse(OrePlacements.ORE_COAL_LOWER));
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return AQBiomeModifiers.ADD_ORE.get();
    }

    public static Codec<AddOreModifier> create() {
        return RecordCodecBuilder.create(builder -> builder.group(
                Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddOreModifier::biomes),
                GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(AddOreModifier::step),
                ModOre.CODEC.fieldOf("ore").forGetter(AddOreModifier::ore)
        ).apply(builder, AddOreModifier::new));
    }
}
