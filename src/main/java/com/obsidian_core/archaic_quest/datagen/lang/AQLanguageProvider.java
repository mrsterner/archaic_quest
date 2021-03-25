package com.obsidian_core.archaic_quest.datagen.lang;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import com.obsidian_core.archaic_quest.common.register.AQItems;
import net.minecraft.data.DataGenerator;

public class AQLanguageProvider extends AbstractLanguageProvider {

    public AQLanguageProvider(DataGenerator gen) {
        super(gen, ArchaicQuest.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.addItemGroup(AQCreativeTabs.BLOCKS, "Archaic Quest - Blocks");
        this.addItemGroup(AQCreativeTabs.ITEMS, "Archaic Quest - Items");
        this.addItemGroup(AQCreativeTabs.DECORATION, "Archaic Quest - Decoration");
        this.addItemGroup(AQCreativeTabs.TOOLS, "Archaic Quest - Tools");
        this.addItemGroup(AQCreativeTabs.WEAPONS, "Archaic Quest - Weapons");
        this.addItemGroup(AQCreativeTabs.ARMOR, "Archaic Quest - Armor");

        this.addItem(AQItems.PEBBLE, "Pebble");
    }
}
