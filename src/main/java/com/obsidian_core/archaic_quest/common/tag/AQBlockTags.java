package com.obsidian_core.archaic_quest.common.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class AQBlockTags {

    public static final TagKey<Block> ORE_TIN = forgeTag("ores/tin");
    public static final TagKey<Block> ORE_SILVER = forgeTag("ores/silver");
    public static final TagKey<Block> ORE_QUARTZ = forgeTag("ores/quartz");

    private static TagKey<Block> forgeTag(String path) {
        return BlockTags.create(new ResourceLocation("forge", path));
    }
}
