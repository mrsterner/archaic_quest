package com.obsidian_core.archaic_quest.common.compat.terrablender;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.Blocks;
import net.minecraft.world.world.worldgen.SurfaceRules;

public class AQSurfaceRuleData {

    private static final SurfaceRules.RuleSource DIRT = stateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = stateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource STONE = stateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource GRAVEL = stateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource DEEPSLATE = stateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource BEDROCK = stateRule(Blocks.BEDROCK);



    protected static SurfaceRules.RuleSource makeRules() {
        return SurfaceRuleData.overworld();
    }

    private static SurfaceRules.RuleSource stateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
