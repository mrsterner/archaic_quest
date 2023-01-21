package com.obsidian_core.archaic_quest.common.misc;

import com.obsidian_core.archaic_quest.common.compat.jei.AQJeiPlugin;
import com.obsidian_core.archaic_quest.common.register.AQItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TranslationReferences {

    public static final Component AZTEC_CRAFTING_STATION_CONTAINER_NAME = Component.translatable("container.archaic_quest.aztec_crafting_station.name");
    public static final Component KNAPPING_TABLE_CONTAINER_NAME = Component.translatable("container.archaic_quest.knapping_table.name");

    public static final Component MACHETE_JEI_DEC = jeiDesc(AQItems.MACHETE.get());


    private static Component jeiDesc(Item item) {
        Component component = Component.translatable(item.getDescriptionId() + ".jei_desc");
        AQJeiPlugin.ITEM_INGREDIENT_INFO.put(new ItemStack(item), component);
        return component;
    }
}
