package com.obsidian_core.archaic_quest.common.tag;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class AQItemTags {

    public static final Tags.IOptionalNamedTag<Item> ORE_TIN = forgeTag("ores/tin");
    public static final Tags.IOptionalNamedTag<Item> ORE_SILVER = forgeTag("ores/silver");
    public static final Tags.IOptionalNamedTag<Item> ORE_QUARTZ = forgeTag("ores/quartz");

    public static final Tags.IOptionalNamedTag<Item> INGOT_TIN = forgeTag("ingots/tin");
    public static final Tags.IOptionalNamedTag<Item> INGOT_SILVER = forgeTag("ingots/silver");

    private static Tags.IOptionalNamedTag<Item> forgeTag(String path) {
        return ItemTags.createOptional(new ResourceLocation("forge", path));
    }
}
