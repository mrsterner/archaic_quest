package com.obsidian_core.archaic_quest.datagen.lang;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class AbstractLanguageProvider extends LanguageProvider {

    public AbstractLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    protected void addItemGroup(ItemGroup itemGroup, String localized) {
        this.add("itemGroup." + itemGroup.getRecipeFolderName(), localized);
    }
}
