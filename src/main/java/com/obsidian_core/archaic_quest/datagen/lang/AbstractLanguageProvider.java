package com.obsidian_core.archaic_quest.datagen.lang;

import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class AbstractLanguageProvider extends LanguageProvider {

    public AbstractLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    protected void addItemGroup(CreativeModeTab creativeTab, String localized) {
        this.add("itemGroup." + creativeTab.getRecipeFolderName(), "Archaic Quest - " + localized);
    }

    protected void addJeiInfo(Item item, String localized) {
        this.add(item.getDescriptionId() + ".jei_desc", localized);
    }

    protected void addTranslationComponent(Component component, String localized) {
        if (component.getContents() instanceof TranslatableContents translatableContents) {
            add(translatableContents.getKey(), localized);
        }
    }
}
