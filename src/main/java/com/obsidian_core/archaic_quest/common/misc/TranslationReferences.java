package com.obsidian_core.archaic_quest.common.misc;

import com.obsidian_core.archaic_quest.common.compat.jei.AQJeiPlugin;
import com.obsidian_core.archaic_quest.common.register.AQItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

public class TranslationReferences {

    public static final TranslationTextComponent AZTEC_CRAFTING_STATION_CONTAINER_NAME = new TranslationTextComponent("container.archaic_quest.aztec_crafting_station.name");
    public static final TranslationTextComponent KNAPPING_TABLE_CONTAINER_NAME = new TranslationTextComponent("container.archaic_quest.knapping_table.name");

    public static final TranslationTextComponent MACHETE_JEI_DEC = jeiDesc(AQItems.MACHETE.get());


    private static TranslationTextComponent jeiDesc(Item item) {
        TranslationTextComponent textComponent = new TranslationTextComponent(item.getDescriptionId() + ".jei_desc");
        AQJeiPlugin.ITEM_INGREDIENT_INFO.put(new ItemStack(item), textComponent);
        return textComponent;
    }
}
