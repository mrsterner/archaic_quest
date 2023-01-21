package com.obsidian_core.archaic_quest.common.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class AQItemTags {

    public static final TagKey<Item> ORE_TIN = forgeTag("ores/tin");
    public static final TagKey<Item> ORE_SILVER = forgeTag("ores/silver");
    public static final TagKey<Item> ORE_QUARTZ = forgeTag("ores/quartz");

    public static final TagKey<Item> INGOT_TIN = forgeTag("ingots/tin");
    public static final TagKey<Item> INGOT_SILVER = forgeTag("ingots/silver");

    private static TagKey<Item> forgeTag(String path) {
        return ItemTags.create(new ResourceLocation("forge", path));
    }
}
