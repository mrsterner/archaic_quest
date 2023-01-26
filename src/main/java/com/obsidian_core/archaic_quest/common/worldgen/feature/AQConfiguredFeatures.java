package com.obsidian_core.archaic_quest.common.worldgen.feature;

import com.google.common.collect.ImmutableList;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.worldgen.biome.modifier.ModOre;
import com.obsidian_core.archaic_quest.common.worldgen.feature.decorators.AQTreeDecoratorType;
import com.obsidian_core.archaic_quest.common.worldgen.feature.decorators.LeafVineVarDecorator;
import com.obsidian_core.archaic_quest.common.worldgen.feature.decorators.TrunkVineVarDecorator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.TREE_THRESHOLD;

public class AQConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CF_REGISTRY = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ArchaicQuest.MODID);
    public static final DeferredRegister<PlacedFeature> P_REGISTRY = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ArchaicQuest.MODID);


    private static final Map<ModOre, RegistryObject<PlacedFeature>> ORES_BY_TYPE = new HashMap<>();


    //----------------------------- CONFIGURED ----------------------------------
    public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> AZTEC_JUNGLE_TREE = register("aztec_jungle_tree", () -> Feature.TREE,
            () -> (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                    new MegaJungleTrunkPlacer(10, 2, 19), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                    new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                    new TwoLayersFeatureSize(1, 1, 2)))
                    .decorators(ImmutableList.of(TrunkVineVarDecorator.INSTANCE, new LeafVineVarDecorator(0.40F)))
                    .build());

    public static final RegistryObject<ConfiguredFeature<OreConfiguration, ?>> TIN_ORE = registerOre("tin_ore", () -> new OreConfiguration(OreFeatures.NATURAL_STONE, AQBlocks.TIN_ORE.get().defaultBlockState(), 9));
    public static final RegistryObject<ConfiguredFeature<OreConfiguration, ?>> SILVER_ORE = registerOre("silver_ore", () -> new OreConfiguration(OreFeatures.NATURAL_STONE, AQBlocks.SILVER_ORE.get().defaultBlockState(), 9));
    public static final RegistryObject<ConfiguredFeature<OreConfiguration, ?>> GRANITE_QUARTZ_ORE = registerOre("granite_quartz_ore", () -> new OreConfiguration(AQFillerBlockTypes.GRANITE, AQBlocks.GRANITE_QUARTZ_ORE.get().defaultBlockState(), 9));


    //------------------------------- PLACED ------------------------------------
    public static final RegistryObject<PlacedFeature> PLACED_AZTEC_JUNGLE_TREE = registerPlaced("aztec_jungle_tree", AZTEC_JUNGLE_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(AQBlocks.AZTEC_JUNGLE_SAPLING.get())));
    //public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> AZTEC_JUNGLE_TREES;

    public static final RegistryObject<PlacedFeature> PLACED_TIN_ORE = registerOre(ModOre.TIN, "ore_tin", TIN_ORE, () -> commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(70))));
    public static final RegistryObject<PlacedFeature> PLACED_SILVER_ORE = registerOre(ModOre.SILVER, "ore_silver", SILVER_ORE, () -> commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(70))));
    public static final RegistryObject<PlacedFeature> PLACED_GRANITE_QUARTZ_ORE = registerOre(ModOre.GRANITE_QUARTZ, "ore_granite_quartz", GRANITE_QUARTZ_ORE, () -> commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(70))));




    private static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<FC, ?>> register(String name, Supplier<F> feature, Supplier<FC> config) {
        return CF_REGISTRY.register(name, () -> new ConfiguredFeature<>(feature.get(), config.get()));
    }

    private static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> registerOre(String name, Supplier<OreConfiguration> configuration) {
        return register(name, () -> Feature.ORE, configuration);
    }

    private static RegistryObject<PlacedFeature> registerOre(ModOre ore, String name, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, Supplier<List<PlacementModifier>> modifiers) {
        RegistryObject<PlacedFeature> placedFeature = registerPlaced(name, feature, modifiers);
        ORES_BY_TYPE.put(ore, placedFeature);
        return placedFeature;
    }

    private static RegistryObject<PlacedFeature> registerPlaced(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, Supplier<List<PlacementModifier>> modifiers) {
        return P_REGISTRY.register(name, () -> new PlacedFeature(Holder.hackyErase(feature.getHolder().orElseThrow()), modifiers.get()));
    }




    private static List<PlacementModifier> orePlacement(PlacementModifier modifier1, PlacementModifier modifier2) {
        return List.of(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }

    private static List<PlacementModifier> rareOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(count), modifier);
    }



    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier modifier) {
        return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier modifier) {
        return treePlacementBase(modifier).build();
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier modifier, Block block) {
        return treePlacementBase(modifier).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(block.defaultBlockState(), BlockPos.ZERO))).build();
    }


    @Nullable
    public static RegistryObject<PlacedFeature> getOreForType(ModOre ore) {
        return ORES_BY_TYPE.get(ore);
    }
}
