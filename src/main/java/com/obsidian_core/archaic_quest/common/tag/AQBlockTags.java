package com.obsidian_core.archaic_quest.common.tag;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AQBlockTags {

    public static final TagKey<Block> ORE_TIN = forgeTag("ores/tin");
    public static final TagKey<Block> ORE_SILVER = forgeTag("ores/silver");
    public static final TagKey<Block> ORE_QUARTZ = forgeTag("ores/quartz");

    public static final TagKey<Block> VERT_SLAB = modTag("vert_slabs");


    private static TagKey<Block> forgeTag(String path) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", path));
    }

    private static TagKey<Block> modTag(String path) {
        return TagKey.of(Registry.BLOCK_KEY, ArchaicQuest.id(path));
    }
}
