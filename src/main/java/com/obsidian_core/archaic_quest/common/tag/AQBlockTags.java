package com.obsidian_core.archaic_quest.common.tag;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class AQBlockTags {

    public static final Tags.IOptionalNamedTag<Block> ORE_TIN = forgeTag("ores/tin");
    public static final Tags.IOptionalNamedTag<Block> ORE_SILVER = forgeTag("ores/silver");
    public static final Tags.IOptionalNamedTag<Block> ORE_QUARTZ = forgeTag("ores/quartz");

    private static Tags.IOptionalNamedTag<Block> forgeTag(String path) {
        return BlockTags.createOptional(new ResourceLocation("forge", path));
    }
}
