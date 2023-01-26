package com.obsidian_core.archaic_quest.datagen.lang;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import com.obsidian_core.archaic_quest.common.misc.TranslationReferences;
import com.obsidian_core.archaic_quest.common.core.register.AQBiomes;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.data.DataGenerator;

public class AQLanguageProvider extends AbstractLanguageProvider {

    public AQLanguageProvider(DataGenerator gen) {
        super(gen, ArchaicQuest.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItemGroup(AQCreativeTabs.BLOCKS, "Blocks");
        addItemGroup(AQCreativeTabs.ITEMS, "Items");
        addItemGroup(AQCreativeTabs.DECORATION, "Decoration");
        addItemGroup(AQCreativeTabs.FOOD, "Food");
        addItemGroup(AQCreativeTabs.TOOLS, "Tools");
        addItemGroup(AQCreativeTabs.WEAPONS, "Weapons");
        addItemGroup(AQCreativeTabs.ARMOR, "Armor");

        addBlock(AQBlocks.AZTEC_JUNGLE_SAPLING, "Aztec Jungle Sapling");

        addBlock(AQBlocks.TIN_ORE, "Tin Ore");
        addBlock(AQBlocks.SILVER_ORE, "Silver Ore");
        addBlock(AQBlocks.BASALT_ORE, "Basalt Ore");
        addBlock(AQBlocks.DIORITE_JADE_ORE, "Diorite Jade Ore");
        addBlock(AQBlocks.ANDESITE_TURQUOISE_ORE, "Andesite Turquoise Ore");
        addBlock(AQBlocks.GRANITE_QUARTZ_ORE, "Granite Quartz Ore");
        addBlock(AQBlocks.ONYX, "Onyx");

        addBlock(AQBlocks.AZTEC_PILLAR, "Aztec Pillar");
        addBlock(AQBlocks.AZTEC_SPRUCE_WOOD_PILLAR, "Aztec Spruce Pillar");
        addBlock(AQBlocks.AZTEC_SPRUCE_WOOD_PILLAR_ANDESITE_BASE, "Aztec Spruce Pillar Base");
        addBlock(AQBlocks.AZTEC_DUNGEON_DOOR_0, "Aztec Dungeon Door 0");
        addBlock(AQBlocks.AZTEC_DUNGEON_DOOR_1, "Aztec Dungeon Door 1");
        addBlock(AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_0, "Aztec Dungeon Door Frame 0");
        addBlock(AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_1, "Aztec Dungeon Door Frame 1");
        addBlock(AQBlocks.AZTEC_THRONE, "Aztec Throne");
        addBlock(AQBlocks.MOSSY_AZTEC_THRONE, "Ass-tech Throne");

        addBlock(AQBlocks.BRONZE_SPEAR_TRAP, "Bronze Spear Trap");
        addBlock(AQBlocks.GOLD_SPEAR_TRAP, "Gold Spear Trap");

        addBlock(AQBlocks.KNAPPING_TABLE, "Knapping Table");
        addBlock(AQBlocks.AZTEC_CRAFTING_STATION, "Aztec Crafting Station");

        addItem(AQItems.CORN, "Corn Cob");

        addItem(AQItems.TIN_INGOT, "Tin Ingot");
        addItem(AQItems.SILVER_INGOT, "Silver Ingot");
        addItem(AQItems.JADE, "Jade");
        addItem(AQItems.TURQUOISE, "Turquoise");
        addItem(AQItems.PEBBLE, "Pebble");

        addItem(AQItems.ADVENTURERS_BAG, "Adventurers Bag");
        addItem(AQItems.ADVENTURERS_GLOBE, "Adventurers Globe");
        addItem(AQItems.ADVENTURERS_HAT, "Adventurers Hat");
        addItem(AQItems.ADVENTURERS_MAGNIFYING_GLASS, "Adventurers Magnifying Glass");
        addItem(AQItems.ADVENTURERS_SPYGLASS, "Adventurers Spyglass");
        addItem(AQItems.AMBER, "Amber");
        addItem(AQItems.AMBER_FOSSIL_0, "Amber Fossil 0");
        addItem(AQItems.AMBER_FOSSIL_1, "Amber Fossil 1");
        addItem(AQItems.AZTEC_GOLD_TALISMAN, "Aztec Gold Talisman");
        addItem(AQItems.AZTEC_GUIDE_BOOK, "Aztec Guide Book");
        addItem(AQItems.AZTEC_JADE_TALISMAN, "Aztec Jade Talisman");
        addItem(AQItems.AZTEC_PAN_FLUTE, "Aztec Pan Flute");
        addItem(AQItems.AZTEC_PIPE, "Aztec Pipe");
        addItem(AQItems.AZTEC_RITUAL_CHALICE, "Aztec Ritual Chalice");
        addItem(AQItems.AZTEC_RITUAL_STAFF, "Aztec Ritual Staff");
        addItem(AQItems.BONE_BLOWPIPE, "Bone Blowpipe");
        addItem(AQItems.WOODEN_BLOWPIPE, "Wooden Blowpipe");
        addItem(AQItems.AZTEC_DEATH_WHISTLE, "Aztec Death Whistle");
        addItem(AQItems.HEART, "Heart");
        addItem(AQItems.JAGUAR_HIDE, "Jaguar Hide");
        addItem(AQItems.LEATHER_QUIVER, "Leather Quiver");
        addItem(AQItems.AZTEC_JAGUAR_QUIVER, "Aztec Jaguar Quiver");
        addItem(AQItems.OLD_BONE, "Old Bone");
        addItem(AQItems.POISONOUS_FROG_0, "Poisonous Frog 0");
        addItem(AQItems.POISONOUS_FROG_1, "Poisonous Frog 1");
        addItem(AQItems.SKULL_0, "Skull 0");
        addItem(AQItems.SKULL_1, "Skull 1");
        addItem(AQItems.CRYSTAL_SKULL, "Crystal Skull");
        addItem(AQItems.STONE_SKULL, "Stone Skull");
        addItem(AQItems.MACHETE, "Machete");
        addItem(AQItems.HAMMER_AND_CHISEL, "Hammer and Chisel");
        addItem(AQItems.WHIP, "Whip");
        addItem(AQItems.WOOD_BONE_DAGGER, "Wooden Bone Dagger");
        addItem(AQItems.WOOD_JADE_DAGGER, "Wooden Jade Dagger");
        addItem(AQItems.WOOD_OBSIDIAN_DAGGER, "Wooden Obsidian Dagger");
        addItem(AQItems.WOODEN_DART, "Wooden Dart");
        addItem(AQItems.BONE_DART, "Bone Dart");
        addItem(AQItems.BONE_CLUB, "Bone Club");

        addBiome(AQBiomes.AZTEC_JUNGLE, "Aztec Jungle");

        addTranslationComponent(TranslationReferences.KNAPPING_TABLE_CONTAINER_NAME, "Knapping Table");

        addJeiInfo(AQItems.MACHETE.get(), "The machete is a light weapon that can be swung faster, but deals less damage than a sword. It can also be used to cut vines. " +
                "Right-clicking will cut a vine shorter and stop it from growing, while sneak-right-clicking will only make the vine stop growing.");
    }
}
