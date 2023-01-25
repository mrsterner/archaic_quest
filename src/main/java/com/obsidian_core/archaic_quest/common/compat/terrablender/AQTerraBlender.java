package com.obsidian_core.archaic_quest.common.compat.terrablender;

import com.obsidian_core.archaic_quest.common.compat.terrablender.regions.AQCommonOverworldRegion;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

public class AQTerraBlender {

    public static void setup() {
        Regions.register(new AQCommonOverworldRegion());

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, ArchaicQuest.MODID, AQSurfaceRuleData.makeRules());
    }
}
