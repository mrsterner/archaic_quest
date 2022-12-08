package com.obsidian_core.archaic_quest.datagen.lang;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public abstract class AbstractLanguageProvider extends LanguageProvider {

    public AbstractLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    protected void addItemGroup(ItemGroup itemGroup, String localized) {
        this.add("itemGroup." + itemGroup.getRecipeFolderName(), "Archaic Quest - " + localized);
    }

    protected void addJeiInfo(Item item, String localized) {
        this.add(item.getDescriptionId() + ".jei_desc", localized);
    }
}
