package com.obsidian_core.archaic_quest.common.worldgen.feature;


import net.minecraft.world.world.block.Blocks;
import net.minecraft.world.world.worldgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.world.worldgen.structure.templatesystem.RuleTest;

public class AQFillerBlockTypes {

    public static final RuleTest GRANITE = new BlockMatchTest(Blocks.GRANITE);
    public static final RuleTest DIORITE = new BlockMatchTest(Blocks.DIORITE);
    public static final RuleTest ANDESITE = new BlockMatchTest(Blocks.ANDESITE);
}
