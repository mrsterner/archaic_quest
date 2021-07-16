package com.obsidian_core.archaic_quest.datagen.lang;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
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

        this.addBlock(AQBlocks.TIN_ORE, "Tin Ore");
        this.addBlock(AQBlocks.SILVER_ORE, "Silver Ore");
        this.addBlock(AQBlocks.BASALT_ORE, "Basalt Ore");
        this.addBlock(AQBlocks.DIORITE_JADE_ORE, "Diorite Jade Ore");
        this.addBlock(AQBlocks.ANDESITE_TURQUOISE_ORE, "Andesite Turquoise Ore");
        this.addBlock(AQBlocks.GRANITE_QUARTZ_ORE, "Granite Quartz Ore");
        this.addBlock(AQBlocks.ONYX, "Onyx");

        this.addItem(AQItems.TIN_INGOT, "Tin Ingot");
        this.addItem(AQItems.SILVER_INGOT, "Silver Ingot");
        this.addItem(AQItems.BONE_CLUB, "Bone Club");
        this.addItem(AQItems.PEBBLE, "Pebble");
        this.addItem(AQItems.CRYSTAL_SKULL, "Crystal Skull");
        this.addItem(AQItems.AZTEC_DEATH_WHISTLE, "Aztec Death Whistle");
    }
}
