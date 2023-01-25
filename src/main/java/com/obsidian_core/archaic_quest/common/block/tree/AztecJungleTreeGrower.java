package com.obsidian_core.archaic_quest.common.block.tree;

import com.obsidian_core.archaic_quest.common.worldgen.feature.AQConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class AztecJungleTreeGrower extends AbstractMegaTreeGrower {


    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return TreeFeatures.JUNGLE_TREE_NO_VINE;
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource randomSource) {
        return AQConfiguredFeatures.AZTEC_JUNGLE_TREE.getHolder().orElse(null);
    }
}
