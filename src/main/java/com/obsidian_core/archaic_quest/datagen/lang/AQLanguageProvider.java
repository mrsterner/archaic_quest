package com.obsidian_core.archaic_quest.datagen.lang;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import com.obsidian_core.archaic_quest.common.misc.TranslationReferences;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.register.AQItems;
import net.minecraft.data.DataGenerator;

public class AQLanguageProvider extends AbstractLanguageProvider {

    public AQLanguageProvider(DataGenerator gen) {
        super(gen, ArchaicQuest.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.addItemGroup(AQCreativeTabs.BLOCKS, "Blocks");
        this.addItemGroup(AQCreativeTabs.ITEMS, "Items");
        this.addItemGroup(AQCreativeTabs.DECORATION, "Decoration");
        this.addItemGroup(AQCreativeTabs.FOOD, "Food");
        this.addItemGroup(AQCreativeTabs.TOOLS, "Tools");
        this.addItemGroup(AQCreativeTabs.WEAPONS, "Weapons");
        this.addItemGroup(AQCreativeTabs.ARMOR, "Armor");

        this.addBlock(AQBlocks.TIN_ORE, "Tin Ore");
        this.addBlock(AQBlocks.SILVER_ORE, "Silver Ore");
        this.addBlock(AQBlocks.BASALT_ORE, "Basalt Ore");
        this.addBlock(AQBlocks.DIORITE_JADE_ORE, "Diorite Jade Ore");
        this.addBlock(AQBlocks.ANDESITE_TURQUOISE_ORE, "Andesite Turquoise Ore");
        this.addBlock(AQBlocks.GRANITE_QUARTZ_ORE, "Granite Quartz Ore");
        this.addBlock(AQBlocks.ONYX, "Onyx");

        this.addBlock(AQBlocks.AZTEC_PILLAR, "Aztec Pillar");
        this.addBlock(AQBlocks.AZTEC_SPRUCE_WOOD_PILLAR, "Aztec Spruce Pillar");
        this.addBlock(AQBlocks.AZTEC_SPRUCE_WOOD_PILLAR_ANDESITE_BASE, "Aztec Spruce Pillar Base");
        this.addBlock(AQBlocks.AZTEC_DUNGEON_DOOR_0, "Aztec Dungeon Door 0");
        this.addBlock(AQBlocks.AZTEC_DUNGEON_DOOR_1, "Aztec Dungeon Door 1");
        this.addBlock(AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_0, "Aztec Dungeon Door Frame 0");
        this.addBlock(AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_1, "Aztec Dungeon Door Frame 1");

        this.addBlock(AQBlocks.KNAPPING_TABLE, "Knapping Table");
        this.addBlock(AQBlocks.AZTEC_CRAFTING_STATION, "Aztec Crafting Station");

        this.addItem(AQItems.CORN, "Corn Cob");

        this.addItem(AQItems.TIN_INGOT, "Tin Ingot");
        this.addItem(AQItems.SILVER_INGOT, "Silver Ingot");
        this.addItem(AQItems.JADE, "Jade");
        this.addItem(AQItems.TURQUOISE, "Turquoise");
        this.addItem(AQItems.PEBBLE, "Pebble");

        this.addItem(AQItems.ADVENTURERS_BAG, "Adventurers Bag");
        this.addItem(AQItems.ADVENTURERS_GLOBE, "Adventurers Globe");
        this.addItem(AQItems.ADVENTURERS_HAT, "Adventurers Hat");
        this.addItem(AQItems.ADVENTURERS_MAGNIFYING_GLASS, "Adventurers Magnifying Glass");
        this.addItem(AQItems.ADVENTURERS_SPYGLASS, "Adventurers Spyglass");
        this.addItem(AQItems.AMBER, "Amber");
        this.addItem(AQItems.AMBER_FOSSIL_0, "Amber Fossil 0");
        this.addItem(AQItems.AMBER_FOSSIL_1, "Amber Fossil 1");
        this.addItem(AQItems.AZTEC_GOLD_TALISMAN, "Aztec Gold Talisman");
        this.addItem(AQItems.AZTEC_GUIDE_BOOK, "Aztec Guide Book");
        this.addItem(AQItems.AZTEC_JADE_TALISMAN, "Aztec Jade Talisman");
        this.addItem(AQItems.AZTEC_PAN_FLUTE, "Aztec Pan Flute");
        this.addItem(AQItems.AZTEC_PIPE, "Aztec Pipe");
        this.addItem(AQItems.AZTEC_RITUAL_CHALICE, "Aztec Ritual Chalice");
        this.addItem(AQItems.AZTEC_RITUAL_STAFF, "Aztec Ritual Staff");
        this.addItem(AQItems.BONE_BLOWPIPE, "Bone Blowpipe");
        this.addItem(AQItems.WOODEN_BLOWPIPE, "Wooden Blowpipe");
        this.addItem(AQItems.AZTEC_DEATH_WHISTLE, "Aztec Death Whistle");

        this.addItem(AQItems.HEART, "Heart");
        this.addItem(AQItems.JAGUAR_HIDE, "Jaguar Hide");
        this.addItem(AQItems.LEATHER_QUIVER, "Leather Quiver");
        this.addItem(AQItems.AZTEC_JAGUAR_QUIVER, "Aztec Jaguar Quiver");
        this.addItem(AQItems.OLD_BONE, "Old Bone");
        this.addItem(AQItems.POISONOUS_FROG_0, "Poisonous Frog 0");
        this.addItem(AQItems.POISONOUS_FROG_1, "Poisonous Frog 1");
        this.addItem(AQItems.SKULL_0, "Skull 0");
        this.addItem(AQItems.SKULL_1, "Skull 1");
        this.addItem(AQItems.CRYSTAL_SKULL, "Crystal Skull");
        this.addItem(AQItems.STONE_SKULL, "Stone Skull");

        this.addItem(AQItems.MACHETE, "Machete");
        this.addItem(AQItems.HAMMER_AND_CHISEL, "Hammer and Chisel");
        this.addItem(AQItems.WHIP, "Whip");

        this.addItem(AQItems.WOOD_BONE_DAGGER, "Wooden Bone Dagger");
        this.addItem(AQItems.WOOD_JADE_DAGGER, "Wooden Jade Dagger");
        this.addItem(AQItems.WOOD_OBSIDIAN_DAGGER, "Wooden Obsidian Dagger");

        this.addItem(AQItems.WOODEN_DART, "Wooden Dart");
        this.addItem(AQItems.BONE_DART, "Bone Club");

        this.addItem(AQItems.BONE_CLUB, "Bone Club");


        this.add(TranslationReferences.KNAPPING_TABLE_CONTAINER_NAME.getKey(), "Knapping Table");

        this.addJeiInfo(AQItems.MACHETE.get(), "The machete is a light weapon that can be swung faster, but deals less damage than a sword. It can also be used to cut vines. " +
                "Right-clicking will cut a vine shorter and stop it from growing, while sneak-right-clicking will only make the vine stop growing.");
    }
}
