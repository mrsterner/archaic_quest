package com.obsidian_core.archaic_quest.registry;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.block.*;
import com.obsidian_core.archaic_quest.common.block.data.DungeonDoorType;
import com.obsidian_core.archaic_quest.common.block.data.ThroneType;
import com.obsidian_core.archaic_quest.common.block.tree.AztecJungleTreeGrower;
import com.obsidian_core.archaic_quest.common.item.*;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecCraftingStationBlockItem;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecDungeonChestBlockItem;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecDungeonDoorBlockItem;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecThroneBlockItem;
import com.obsidian_core.archaic_quest.common.item.data.AQToolMaterials;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AQObjects {
    Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    AbstractBlock.Settings ANDESITE_BRICKS_PROP = AbstractBlock.Settings.of(Material.STONE, MapColor.STONE_GRAY).requiresTool().strength(1.5F, 6.0F);
    AbstractBlock.Settings GOLD_BRICKS_PROP = AbstractBlock.Settings.of(Material.METAL, MapColor.GOLD).requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.METAL);

    // VEGETATION
    Block VINES_1 = register("vines_1", new CoolVinesBlock(AbstractBlock.Settings.copy(Blocks.VINE)), settings(), true);
    Block AZTEC_JUNGLE_SAPLING = register("aztec_jungle_sapling", new SaplingBlock(new AztecJungleTreeGrower(), AbstractBlock.Settings.copy(Blocks.JUNGLE_SAPLING)), settings(), true);

    //CROPS
    Block CORN_CROP = register("corn_crop", new CornCropBlock(), settings(), true);

    //ORES
    Block TIN_ORE = register("tin_ore", new AQOreBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(3,3)), settings(), true);
    Block SILVER_ORE = register("silver_ore", new AQOreBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(3,3)), settings(), true);
    Block BASALT_ORE = register("basalt_ore", new AQOreBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(3,3)), settings(), true);
    Block GRANITE_QUARTZ_ORE = register("granite_quartz_ore", new AQOreBlock(2, 5, AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(3,3)), settings(), true);
    Block ANDESITE_TURQUOISE_ORE = register("andesite_turquoise_ore", new AQOreBlock(2 , 5, AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(3,3)), settings(), true);
    Block DIORITE_JADE_ORE = register("diorite_jade_ore", new AQOreBlock(2, 5, AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(3,3)), settings(), true);
    Block ONYX = register("onyx", new AQOreBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(3,3)), settings(), true);


    Block ANDESITE_BRICKS = registerVariants("andesite_bricks", ANDESITE_BRICKS_PROP);
    Block MOSSY_ANDESITE_BRICKS = registerVariants("mossy_andesite_bricks", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_0 = registerVariants("andesite_aztec_bricks_0", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_1 = registerVariants("andesite_aztec_bricks_1", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_2 = registerVariants("andesite_aztec_bricks_2", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_2_CRACKED = registerVariants("andesite_aztec_bricks_2_cracked", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_3 = registerVariants("andesite_aztec_bricks_3", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_4 = registerVariants("andesite_aztec_bricks_4", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_5 = registerVariants("andesite_aztec_bricks_5", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_6 = registerVariants("andesite_aztec_bricks_6", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_7 = registerVariants("andesite_aztec_bricks_7", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_8 = registerVariants("andesite_aztec_bricks_8", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_9 = registerVariants("andesite_aztec_bricks_9", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_10 = registerVariants("andesite_aztec_bricks_10", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_11 = registerVariants("andesite_aztec_bricks_11", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_12 = registerVariants("andesite_aztec_bricks_12", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_13 = registerVariants("andesite_aztec_bricks_13", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_14 = registerVariants("andesite_aztec_bricks_14", ANDESITE_BRICKS_PROP);
    Block MOSSY_ANDESITE_AZTEC_BRICKS_14 = registerVariants("mossy_andesite_aztec_bricks_14", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_15 = registerVariants("andesite_aztec_bricks_15", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_16 = register("andesite_aztec_bricks_16", new Block(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_16_VERT_SLAB = register("andesite_aztec_bricks_16_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_17 = registerVariants("andesite_aztec_bricks_17", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_18 = registerVariants("andesite_aztec_bricks_18", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_19 = registerVariants("andesite_aztec_bricks_19", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_20 = registerVariants("andesite_aztec_bricks_20", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_21 = registerVariants("andesite_aztec_bricks_21", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_22 = registerVariants("andesite_aztec_bricks_22", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_23 = registerVariants("andesite_aztec_bricks_23", ANDESITE_BRICKS_PROP);

    Block ANDESITE_AZTEC_BRICKS_24 = register("andesite_aztec_bricks_24", new Block(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_24_VERT_SLAB = register("andesite_aztec_bricks_24_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_25 = registerVariants("andesite_aztec_bricks_25", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_26 = register("andesite_aztec_bricks_26", new Block(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_26_VERT_SLAB = register("andesite_aztec_bricks_26_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_27 = register("andesite_aztec_bricks_27", new SimpleHorizontalBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_27_VERT_SLAB = register("andesite_aztec_bricks_27_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_28 = registerVariants("andesite_aztec_bricks_28", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_29 = register("andesite_aztec_bricks_29", new SimpleHorizontalBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_29_VERT_SLAB = register("andesite_aztec_bricks_29_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_30 = registerVariants("andesite_aztec_bricks_30", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_31 = registerVariants("andesite_aztec_bricks_31", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_32 = register("andesite_aztec_bricks_32", new SimpleHorizontalBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_32_VERT_SLAB = register("andesite_aztec_bricks_32_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_33 = register("andesite_aztec_bricks_33", new Block(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_33_VERT_SLAB = register("andesite_aztec_bricks_33_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_34 = register("andesite_aztec_bricks_34", new Block(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_34_VERT_SLAB = register("andesite_aztec_bricks_34_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_35 = registerVariants("andesite_aztec_bricks_35", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_36 = register("andesite_aztec_bricks_36", new SimpleHorizontalBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_36_VERT_SLAB = register("andesite_aztec_bricks_36_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);

    Block ANDESITE_AZTEC_BRICKS_37 = registerVariants("andesite_aztec_bricks_37", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_38 = registerVariants("andesite_aztec_bricks_38", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_39 = registerVariants("andesite_aztec_bricks_39", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_40 = registerVariants("andesite_aztec_bricks_40", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_41 = registerVariants("andesite_aztec_bricks_41", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_42 = registerVariants("andesite_aztec_bricks_42", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_43 = registerVariants("andesite_aztec_bricks_43", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_44 = registerVariants("andesite_aztec_bricks_44", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_45 = registerVariants("andesite_aztec_bricks_45", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_46 = registerVariants("andesite_aztec_bricks_46", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_47 = registerVariants("andesite_aztec_bricks_47", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_48 = registerVariants("andesite_aztec_bricks_48", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_49 = registerVariants("andesite_aztec_bricks_49", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_50 = registerVariants("andesite_aztec_bricks_50", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_51 = registerVariants("andesite_aztec_bricks_51", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_52 = registerVariants("andesite_aztec_bricks_52", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_53 = registerVariants("andesite_aztec_bricks_53", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_54 = registerVariants("andesite_aztec_bricks_54", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_55 = registerVariants("andesite_aztec_bricks_55", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_56 = registerVariants("andesite_aztec_bricks_56", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_57 = registerVariants("andesite_aztec_bricks_57", ANDESITE_BRICKS_PROP);
    Block MOSSY_ANDESITE_AZTEC_BRICKS_57 = registerVariants("mossy_andesite_aztec_bricks_57", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_58 = registerVariants("andesite_aztec_bricks_58", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_59 = registerVariants("andesite_aztec_bricks_59", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_60 = registerVariants("andesite_aztec_bricks_60", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_61 = registerVariants("andesite_aztec_bricks_61", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_62 = registerVariants("andesite_aztec_bricks_62", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_63 = registerVariants("andesite_aztec_bricks_63", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_64 = registerVariants("andesite_aztec_bricks_64", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_65 = registerVariants("andesite_aztec_bricks_65", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_66 = registerVariants("andesite_aztec_bricks_66", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_67 = registerVariants("andesite_aztec_bricks_67", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_68 = registerVariants("andesite_aztec_bricks_68", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_69 = registerVariants("andesite_aztec_bricks_69", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_70 = registerVariants("andesite_aztec_bricks_70", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_71 = registerVariants("andesite_aztec_bricks_71", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_72 = registerVariants("andesite_aztec_bricks_72", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_73 = registerVariants("andesite_aztec_bricks_73", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_74 = registerVariants("andesite_aztec_bricks_74", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_75 = registerVariants("andesite_aztec_bricks_75", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_76 = registerVariants("andesite_aztec_bricks_76", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_77 = register("andesite_aztec_bricks_77", new SimpleHorizontalBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_77_VERT_SLAB = register("andesite_aztec_bricks_77_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_78 = registerVariants("andesite_aztec_bricks_78", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_79 = register("andesite_aztec_bricks_79", new SimpleHorizontalBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_79_VERT_SLAB = register("andesite_aztec_bricks_79_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_80 = register("andesite_aztec_bricks_80", new SimpleHorizontalBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_80_VERT_SLAB = register("andesite_aztec_bricks_80_vertical_slab", new VerticalSlabBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_BRICKS_81 = registerVariants("andesite_aztec_bricks_81", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_82 = registerVariants("andesite_aztec_bricks_82", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_83 = registerVariants("andesite_aztec_bricks_83", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_84 = registerVariants("andesite_aztec_bricks_84", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_85 = registerVariants("andesite_aztec_bricks_85", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_86 = registerVariants("andesite_aztec_bricks_86", ANDESITE_BRICKS_PROP);
    Block ANDESITE_AZTEC_BRICKS_87 = registerVariants("andesite_aztec_bricks_87", ANDESITE_BRICKS_PROP);

    Block AZTEC_PILLAR = register("aztec_pillar", new ChiselPillarBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block AZTEC_DUNGEON_DOOR_0 = registerDungeonDoor("aztec_dungeon_door_0", DungeonDoorType.AZTEC_DOOR_0);
    Block AZTEC_DUNGEON_DOOR_1 = registerDungeonDoor("aztec_dungeon_door_1", DungeonDoorType.AZTEC_DOOR_1);
    Block AZTEC_DUNGEON_DOOR_FRAME_0 = registerDungeonDoor("aztec_dungeon_door_frame_0", DungeonDoorType.AZTEC_DOOR_FRAME_0);
    Block AZTEC_DUNGEON_DOOR_FRAME_1 = registerDungeonDoor("aztec_dungeon_door_frame_1", DungeonDoorType.AZTEC_DOOR_FRAME_1);
    Block AZTEC_THRONE = registerThrone("aztec_throne", ThroneType.THRONE);
    Block MOSSY_AZTEC_THRONE = registerThrone("mossy_aztec_throne", ThroneType.MOSSY_THRONE);

    Block AZTEC_DUNGEON_CHEST = registerChest("aztec_dungeon_chest");


    Block BRONZE_SPEAR_TRAP = register("bronze_spear_trap", new SpearTrapBlock(2.0F, AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN).strength(1.0F).sounds(BlockSoundGroup.WOOD)), settings(), true);
    Block GOLD_SPEAR_TRAP = register("gold_spear_trap", new SpearTrapBlock(1.75F, AbstractBlock.Settings.of(Material.WOOD, MapColor.GOLD).strength(0.9F).sounds(BlockSoundGroup.WOOD)), settings(), true);
    Block AZTEC_POISON_TRAP = register("aztec_poison_trap", new AztecPoisonTrapBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block AZTEC_ANDESITE_SPIKE_TRAP = register("aztec_andesite_spike_trap", new SpikeTrapBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_TRAP_0 = register("andesite_aztec_trap_0", new AztecTrapBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block ANDESITE_AZTEC_TRAP_1 = register("andesite_aztec_trap_1", new AztecTrapBlock(ANDESITE_BRICKS_PROP), settings(), true);
    Block STONE_AZTEC_BRICKS_0 = registerVariants("stone_aztec_bricks_0", ANDESITE_BRICKS_PROP);

    Block AZTEC_SPRUCE_WOOD_PILLAR = register("aztec_spruce_wood_pillar", new AztecWoodPillarBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_WOOD)), settings(), true);
    Block AZTEC_SPRUCE_WOOD_PILLAR_ANDESITE_BASE = register("aztec_spruce_wood_pillar_andesite_base", new AztecWoodPillarBaseBlock(AbstractBlock.Settings.copy(Blocks.COBBLESTONE)), settings(), true);

    Block AZTEC_VASE = register("aztec_vase", new CeramicVaseBlock(AbstractBlock.Settings.of(Material.STONE).sounds(AQSoundEvents.VASE_BREAK_GROUP).strength(0.7F)), settings(), true);
    Block INFESTED_VASE = register("aztec_infested_vase", new InfestedVaseBlock(AbstractBlock.Settings.of(Material.STONE).sounds(AQSoundEvents.VASE_BREAK_GROUP).strength(0.35F)), settings(), true);

    Block AZTEC_CRAFTING_STATION = registerCrafting("aztec_crafting_station");

    Block GOLD_AZTEC_BRICKS = registerVariants("gold_aztec_bricks", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_0 = registerVariants("gold_aztec_bricks_0", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_1 = registerVariants("gold_aztec_bricks_1", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_2 = registerVariants("gold_aztec_bricks_2", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_3 = registerVariants("gold_aztec_bricks_3", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_4 = registerVariants("gold_aztec_bricks_4", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_5 = registerVariants("gold_aztec_bricks_5", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_6 = registerVariants("gold_aztec_bricks_6", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_7 = registerVariants("gold_aztec_bricks_7", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_8 = registerVariants("gold_aztec_bricks_8", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_9 = registerVariants("gold_aztec_bricks_9", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_10 = registerVariants("gold_aztec_bricks_10", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_11 = registerVariants("gold_aztec_bricks_11", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_12 = registerVariants("gold_aztec_bricks_12", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_13 = registerVariants("gold_aztec_bricks_13", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_14 = registerVariants("gold_aztec_bricks_14", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_15 = registerVariants("gold_aztec_bricks_15", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_16 = registerVariants("gold_aztec_bricks_16", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_17 = registerVariants("gold_aztec_bricks_17", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_18 = registerVariants("gold_aztec_bricks_18", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_19 = registerVariants("gold_aztec_bricks_19", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_20 = registerVariants("gold_aztec_bricks_20", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_21 = registerVariants("gold_aztec_bricks_21", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_22 = registerVariants("gold_aztec_bricks_22", GOLD_BRICKS_PROP);
    Block GOLD_AZTEC_BRICKS_23 = registerVariants("gold_aztec_bricks_23", GOLD_BRICKS_PROP);

    Block GOLD_AZTEC_TRAP_0 = register("gold_aztec_trap_0", new AztecTrapBlock(GOLD_BRICKS_PROP), settings(), true);
    Block GOLD_AZTEC_TRAP_1 = register("gold_aztec_trap_1", new AztecTrapBlock(GOLD_BRICKS_PROP), settings(), true);

    Block KNAPPING_TABLE = register("knapping_table", new KnappingTableBlock(), settings(), true);
    Block FOUNDRY = register("foundry", new FoundryBlock(), settings(), true);




    //ITEMS
    // FOOD
    Item CORN = register("corn_cob",new AliasedBlockItem(CORN_CROP, new Item.Settings()
            .food(new FoodComponent.Builder()
                    .hunger(2)
                    .saturationModifier(1.0F)
                    .build())));

    // MISC
    Item TIN_INGOT = register("tin_ingot", AQCreativeTabs.ITEMS);
    Item SILVER_INGOT = register("silver_ingot", AQCreativeTabs.ITEMS);
    Item TURQUOISE = register("turquoise", AQCreativeTabs.ITEMS);
    Item JADE = register("jade", AQCreativeTabs.ITEMS);
    Item PEBBLE = register("pebble", new PebbleItem());

    Item ADVENTURERS_BAG = register("adventurers_bag", AQCreativeTabs.ITEMS);
    Item ADVENTURERS_GLOBE = register("adventurers_globe", AQCreativeTabs.ITEMS);
    Item ADVENTURERS_HAT = register("adventurers_hat", AQCreativeTabs.ITEMS);
    Item ADVENTURERS_MAGNIFYING_GLASS = register("adventurers_magnifying_glass", AQCreativeTabs.ITEMS);
    Item ADVENTURERS_SPYGLASS = register("adventurers_spyglass", AQCreativeTabs.ITEMS);
    Item ADVENTURERS_TORCH = register("adventurers_torch", new AdventurersTorchItem());
    Item AMBER = register("amber", AQCreativeTabs.ITEMS);
    Item AMBER_FOSSIL_0 = register("amber_fossil_0", AQCreativeTabs.ITEMS);
    Item AMBER_FOSSIL_1 = register("amber_fossil_1", AQCreativeTabs.ITEMS);
    Item AZTEC_GOLD_TALISMAN = register("aztec_gold_talisman", AQCreativeTabs.ITEMS);
    Item AZTEC_GUIDE_BOOK = register("aztec_guide_book", AQCreativeTabs.ITEMS);
    Item AZTEC_JADE_TALISMAN = register("aztec_jade_talisman", AQCreativeTabs.ITEMS);
    Item AZTEC_PAN_FLUTE = register("aztec_pan_flute", AQCreativeTabs.ITEMS);
    Item AZTEC_PIPE = register("aztec_pipe", AQCreativeTabs.ITEMS);
    Item AZTEC_RITUAL_CHALICE = register("aztec_ritual_chalice", AQCreativeTabs.ITEMS);
    Item AZTEC_RITUAL_STAFF = register("aztec_ritual_staff", AQCreativeTabs.ITEMS);
    Item AZTEC_DEATH_WHISTLE = register("aztec_death_whistle", new AztecDeathWhistleItem());
    Item HEART = register("heart", AQCreativeTabs.ITEMS);
    Item JAGUAR_HIDE = register("jaguar_hide", AQCreativeTabs.ITEMS);
    Item LEATHER_QUIVER = register("leather_quiver", AQCreativeTabs.ITEMS);
    Item AZTEC_JAGUAR_QUIVER = register("aztec_jaguar_quiver", AQCreativeTabs.ITEMS);
    Item OLD_BONE = register("old_bone", AQCreativeTabs.ITEMS);
    Item POISONOUS_FROG_0 = register("poisonous_frog_0", AQCreativeTabs.ITEMS);
    Item POISONOUS_FROG_1 = register("poisonous_frog_1", AQCreativeTabs.ITEMS);
    Item SKULL_0 = register("skull_0", AQCreativeTabs.ITEMS);
    Item SKULL_1 = register("skull_1", AQCreativeTabs.ITEMS);
    Item CRYSTAL_SKULL = register("crystal_skull", AQCreativeTabs.ITEMS);
    Item STONE_SKULL = register("stone_skull", AQCreativeTabs.ITEMS);


    // TOOLS & WEAPONS
    Item MACHETE = register("machete", new MacheteItem(ToolMaterials.IRON, 200, 2, -1.0F));
    Item HAMMER_AND_CHISEL = register("hammer_and_chisel", new HammerAndChiselItem());
    Item WHIP = register("whip", AQCreativeTabs.ITEMS);

    Item WOOD_BONE_DAGGER = register("wood_bone_dagger", new AQSimpleWeaponItem(ToolMaterials.WOOD, 60, 3, 0.0F));
    Item WOOD_JADE_DAGGER = register("wood_jade_dagger", new AQSimpleWeaponItem(AQToolMaterials.JADE, 60, 3, 0.0F));
    Item WOOD_OBSIDIAN_DAGGER = register("wood_obsidian_dagger", new AQSimpleWeaponItem(AQToolMaterials.OBSIDIAN, 60, 3, 0.0F));
    Item BONE_CLUB = register("bone_club", new AQSimpleWeaponItem(ToolMaterials.WOOD, 60, 5, -3.0F));

    Item BONE_BLOWPIPE = register("bone_blowpipe", AQCreativeTabs.WEAPONS);
    Item WOODEN_BLOWPIPE = register("wooden_blowpipe", AQCreativeTabs.WEAPONS);
    Item WOODEN_DART = register("wooden_dart", AQCreativeTabs.WEAPONS);
    Item BONE_DART = register("bone_dart", AQCreativeTabs.WEAPONS);

    static Item.Settings settings() {
        return new Item.Settings();
    }

    static <T extends Item> T register(String name, T item) {
        ITEMS.put(item, ArchaicQuest.id(name));
        return item;
    }

    static <T extends Block> T register(String name, T block, Item.Settings settings, boolean createItem) {
        BLOCKS.put(block, ArchaicQuest.id(name));
        if (createItem) {
            ITEMS.put(new BlockItem(block, settings), BLOCKS.get(block));
        }
        return block;
    }

    static Block registerVariants(String name, AbstractBlock.Settings settings){
        Block block = new Block(settings);
        register(name, block, settings(), true);
        register(name + "_slab", new SlabBlock(settings), settings(), true);
        register(name + "_vertical_slab", new VerticalSlabBlock(settings), settings(), true);
        register(name + "_stairs", new StairsBlock(block.getDefaultState(), settings), settings(), true);
        return block;
    }

    static Block registerDungeonDoor(String name, DungeonDoorType doorType) {
        Block block = new AztecDungeonDoorBlock(doorType);
        BLOCKS.put(block, ArchaicQuest.id(name));
        ITEMS.put(new AztecDungeonDoorBlockItem(block), BLOCKS.get(block));
        return block;
    }

    static Block registerThrone(String name, ThroneType throneType) {
        Block block = new AztecThroneBlock(throneType);
        BLOCKS.put(block, ArchaicQuest.id(name));
        ITEMS.put(new AztecThroneBlockItem(block, throneType), BLOCKS.get(block));
        return block;
    }

    static Block registerChest(String name) {
        Block block = new AztecDungeonChestBlock(ANDESITE_BRICKS_PROP);
        BLOCKS.put(block, ArchaicQuest.id(name));
        ITEMS.put(new AztecDungeonChestBlockItem(block, settings()), BLOCKS.get(block));
        return block;
    }

    static Block registerCrafting(String name) {
        Block block = new AztecCraftingStationBlock(AbstractBlock.Settings.of(Material.STONE).nonOpaque().strength(1.5F, 4.0F).sounds(BlockSoundGroup.STONE));
        BLOCKS.put(block, ArchaicQuest.id(name));
        ITEMS.put(new AztecCraftingStationBlockItem(block, settings()), BLOCKS.get(block));
        return block;
    }

    static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
    }

}
