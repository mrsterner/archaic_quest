package com.obsidian_core.archaic_quest.common.tag;


import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AQItemTags {

    public static final TagKey<Item> ORE_TIN = forgeTag("ores/tin");
    public static final TagKey<Item> ORE_SILVER = forgeTag("ores/silver");
    public static final TagKey<Item> ORE_QUARTZ = forgeTag("ores/quartz");

    public static final TagKey<Item> INGOT_TIN = forgeTag("ingots/tin");
    public static final TagKey<Item> INGOT_SILVER = forgeTag("ingots/silver");

    private static TagKey<Item> forgeTag(String path) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier("c", path));
    }
}
