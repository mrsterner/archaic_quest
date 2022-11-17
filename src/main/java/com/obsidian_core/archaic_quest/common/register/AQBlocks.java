package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.client.render.tile.ISTER;
import com.obsidian_core.archaic_quest.common.block.*;
import com.obsidian_core.archaic_quest.common.block.data.DungeonDoorType;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import com.obsidian_core.archaic_quest.common.item.AztecCraftingStationBlockItem;
import com.obsidian_core.archaic_quest.common.item.AztecDungeonDoorBlockItem;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class AQBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArchaicQuest.MODID);

    public static final List<RegistryObject<Block>> SIMPLE_BLOCKS = new ArrayList<>();
    public static final Map<RegistryObject<Block>, RegistryObject<VerticalSlabBlock>> VERT_SLAB_VARIANTS = new HashMap<>();
    public static final Map<RegistryObject<Block>, RegistryObject<SlabBlock>> SLAB_VARIANTS = new HashMap<>();
    public static final Map<RegistryObject<Block>, RegistryObject<StairsBlock>> STAIRS_VARIANTS = new HashMap<>();

    public static final AbstractBlock.Properties ANDESITE_BRICKS_PROP;
    public static final AbstractBlock.Properties GOLD_BRICKS_PROP;

    static {
        ANDESITE_BRICKS_PROP = AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).harvestTool(ToolType.PICKAXE);
        GOLD_BRICKS_PROP = AbstractBlock.Properties.of(Material.METAL, MaterialColor.GOLD).requiresCorrectToolForDrops().strength(3.0F, 6.0F).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL);
    }

    // VEGETATION
    public static final RegistryObject<Block> VINES_1 = registerBlock("vines_1", AQCreativeTabs.DECORATION, () -> new CoolVinesBlock(AbstractBlock.Properties.copy(Blocks.VINE)));

    // CROPS
    public static final RegistryObject<Block> CORN_CROP = registerBlockNoBlockItem("corn_crop", CornCropBlock::new);

    // ORES
    public static final RegistryObject<Block> TIN_ORE = simpleBlock("tin_ore", AQCreativeTabs.BLOCKS, () -> new AQOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
    public static final RegistryObject<Block> SILVER_ORE = simpleBlock("silver_ore", AQCreativeTabs.BLOCKS, () -> new AQOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> BASALT_ORE = simpleBlock("basalt_ore", AQCreativeTabs.BLOCKS, () -> new AQOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> GRANITE_QUARTZ_ORE = simpleBlock("granite_quartz_ore", AQCreativeTabs.BLOCKS, () -> new AQOreBlock(2, 5, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
    public static final RegistryObject<Block> ANDESITE_TURQUOISE_ORE = simpleBlock("andesite_turquoise_ore", AQCreativeTabs.BLOCKS, () -> new AQOreBlock(2, 5, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> DIORITE_JADE_ORE = simpleBlock("diorite_jade_ore", AQCreativeTabs.BLOCKS, () -> new AQOreBlock(2, 5, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> ONYX = simpleBlock("onyx", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3)));

    // AZTEC STUFF
    public static final RegistryObject<Block> ANDESITE_BRICKS = simpleBlockWithVars("andesite_bricks", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> MOSSY_ANDESITE_BRICKS = simpleBlockWithVars("mossy_andesite_bricks", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_0 = simpleBlockWithVars("andesite_aztec_bricks_0", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_1 = simpleBlockWithVars("andesite_aztec_bricks_1", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_2 = simpleBlockWithVars("andesite_aztec_bricks_2", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_2_CRACKED = simpleBlockWithVars("andesite_aztec_bricks_2_cracked", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_3 = simpleBlockWithVars("andesite_aztec_bricks_3", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_4 = simpleBlockWithVars("andesite_aztec_bricks_4", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_5 = simpleBlockWithVars("andesite_aztec_bricks_5", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_6 = simpleBlockWithVars("andesite_aztec_bricks_6", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_7 = simpleBlockWithVars("andesite_aztec_bricks_7", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_8 = simpleBlockWithVars("andesite_aztec_bricks_8", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_9 = simpleBlockWithVars("andesite_aztec_bricks_9", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_10 = simpleBlockWithVars("andesite_aztec_bricks_10", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_11 = simpleBlockWithVars("andesite_aztec_bricks_11", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_12 = simpleBlockWithVars("andesite_aztec_bricks_12", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_13 = simpleBlockWithVars("andesite_aztec_bricks_13", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_14 = simpleBlockWithVars("andesite_aztec_bricks_14", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> MOSSY_ANDESITE_AZTEC_BRICKS_14 = simpleBlockWithVars("mossy_andesite_aztec_bricks_14", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_15 = simpleBlockWithVars("andesite_aztec_bricks_15", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_16 = registerBlock("andesite_aztec_bricks_16", AQCreativeTabs.BLOCKS, () -> new Block(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_16_VERT_SLAB = registerBlock("andesite_aztec_bricks_16_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_17 = simpleBlockWithVars("andesite_aztec_bricks_17", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_18 = simpleBlockWithVars("andesite_aztec_bricks_18", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_19 = simpleBlockWithVars("andesite_aztec_bricks_19", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_20 = simpleBlockWithVars("andesite_aztec_bricks_20", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_21 = simpleBlockWithVars("andesite_aztec_bricks_21", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_22 = simpleBlockWithVars("andesite_aztec_bricks_22", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_23 = simpleBlockWithVars("andesite_aztec_bricks_23", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_24 = registerBlock("andesite_aztec_bricks_24", AQCreativeTabs.BLOCKS, () -> new Block(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_24_VERT_SLAB = registerBlock("andesite_aztec_bricks_24_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_25 = simpleBlockWithVars("andesite_aztec_bricks_25", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_26 = registerBlock("andesite_aztec_bricks_26", AQCreativeTabs.BLOCKS, () -> new Block(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_26_VERT_SLAB = registerBlock("andesite_aztec_bricks_26_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_27 = registerBlock("andesite_aztec_bricks_27", AQCreativeTabs.BLOCKS, () -> new SimpleHorizontalBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_27_VERT_SLAB = registerBlock("andesite_aztec_bricks_27_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_28 = simpleBlockWithVars("andesite_aztec_bricks_28", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_29 = registerBlock("andesite_aztec_bricks_29", AQCreativeTabs.BLOCKS, () -> new SimpleHorizontalBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_29_VERT_SLAB = registerBlock("andesite_aztec_bricks_29_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_30 = simpleBlockWithVars("andesite_aztec_bricks_30", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_31 = simpleBlockWithVars("andesite_aztec_bricks_31", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_32 = registerBlock("andesite_aztec_bricks_32", AQCreativeTabs.BLOCKS, () -> new SimpleHorizontalBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_32_VERT_SLAB = registerBlock("andesite_aztec_bricks_32_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_33 = registerBlock("andesite_aztec_bricks_33", AQCreativeTabs.BLOCKS, () -> new Block(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_33_VERT_SLAB = registerBlock("andesite_aztec_bricks_33_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_34 = registerBlock("andesite_aztec_bricks_34", AQCreativeTabs.BLOCKS, () -> new Block(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_34_VERT_SLAB = registerBlock("andesite_aztec_bricks_34_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_35 = simpleBlockWithVars("andesite_aztec_bricks_35", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_36 = simpleBlockWithVars("andesite_aztec_bricks_36", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_37 = simpleBlockWithVars("andesite_aztec_bricks_37", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_38 = simpleBlockWithVars("andesite_aztec_bricks_38", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_39 = simpleBlockWithVars("andesite_aztec_bricks_39", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_40 = simpleBlockWithVars("andesite_aztec_bricks_40", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_41 = simpleBlockWithVars("andesite_aztec_bricks_41", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_42 = simpleBlockWithVars("andesite_aztec_bricks_42", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_43 = simpleBlockWithVars("andesite_aztec_bricks_43", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_44 = simpleBlockWithVars("andesite_aztec_bricks_44", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_45 = simpleBlockWithVars("andesite_aztec_bricks_45", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_46 = simpleBlockWithVars("andesite_aztec_bricks_46", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_47 = simpleBlockWithVars("andesite_aztec_bricks_47", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_48 = simpleBlockWithVars("andesite_aztec_bricks_48", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_49 = simpleBlockWithVars("andesite_aztec_bricks_49", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_50 = simpleBlockWithVars("andesite_aztec_bricks_50", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_51 = simpleBlockWithVars("andesite_aztec_bricks_51", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_52 = simpleBlockWithVars("andesite_aztec_bricks_52", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_53 = simpleBlockWithVars("andesite_aztec_bricks_53", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_54 = simpleBlockWithVars("andesite_aztec_bricks_54", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_55 = simpleBlockWithVars("andesite_aztec_bricks_55", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_56 = simpleBlockWithVars("andesite_aztec_bricks_56", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_57 = simpleBlockWithVars("andesite_aztec_bricks_57", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> MOSSY_ANDESITE_AZTEC_BRICKS_57 = simpleBlockWithVars("mossy_andesite_aztec_bricks_57", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_58 = simpleBlockWithVars("andesite_aztec_bricks_58", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_59 = simpleBlockWithVars("andesite_aztec_bricks_59", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_60 = simpleBlockWithVars("andesite_aztec_bricks_60", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_61 = simpleBlockWithVars("andesite_aztec_bricks_61", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_62 = simpleBlockWithVars("andesite_aztec_bricks_62", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_63 = simpleBlockWithVars("andesite_aztec_bricks_63", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_64 = simpleBlockWithVars("andesite_aztec_bricks_64", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_65 = simpleBlockWithVars("andesite_aztec_bricks_65", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_66 = simpleBlockWithVars("andesite_aztec_bricks_66", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_67 = simpleBlockWithVars("andesite_aztec_bricks_67", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_68 = simpleBlockWithVars("andesite_aztec_bricks_68", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_69 = simpleBlockWithVars("andesite_aztec_bricks_69", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_70 = simpleBlockWithVars("andesite_aztec_bricks_70", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_71 = simpleBlockWithVars("andesite_aztec_bricks_71", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_72 = simpleBlockWithVars("andesite_aztec_bricks_72", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_73 = simpleBlockWithVars("andesite_aztec_bricks_73", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_74 = simpleBlockWithVars("andesite_aztec_bricks_74", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_75 = simpleBlockWithVars("andesite_aztec_bricks_75", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_76 = simpleBlockWithVars("andesite_aztec_bricks_76", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_77 = registerBlock("andesite_aztec_bricks_77", AQCreativeTabs.BLOCKS, () -> new SimpleHorizontalBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_77_VERT_SLAB = registerBlock("andesite_aztec_bricks_77_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_78 = simpleBlockWithVars("andesite_aztec_bricks_78", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_79 = registerBlock("andesite_aztec_bricks_79", AQCreativeTabs.BLOCKS, () -> new SimpleHorizontalBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_79_VERT_SLAB = registerBlock("andesite_aztec_bricks_79_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_80 = registerBlock("andesite_aztec_bricks_80", AQCreativeTabs.BLOCKS, () -> new SimpleHorizontalBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_80_VERT_SLAB = registerBlock("andesite_aztec_bricks_80_vertical_slab", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_81 = simpleBlockWithVars("andesite_aztec_bricks_81", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_82 = simpleBlockWithVars("andesite_aztec_bricks_82", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_83 = simpleBlockWithVars("andesite_aztec_bricks_83", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_84 = simpleBlockWithVars("andesite_aztec_bricks_84", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_85 = simpleBlockWithVars("andesite_aztec_bricks_85", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_86 = simpleBlockWithVars("andesite_aztec_bricks_86", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_87 = simpleBlockWithVars("andesite_aztec_bricks_87", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);

    public static final RegistryObject<Block> AZTEC_PILLAR = registerBlock("aztec_pillar", AQCreativeTabs.DECORATION, () -> new ChiselPillarBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<AztecDungeonDoorBlock> AZTEC_DUNGEON_DOOR_0 = registerDungeonDoor("aztec_dungeon_door_0", DungeonDoorType.AZTEC_DOOR_0);
    public static final RegistryObject<AztecDungeonDoorBlock> AZTEC_DUNGEON_DOOR_1 = registerDungeonDoor("aztec_dungeon_door_1", DungeonDoorType.AZTEC_DOOR_1);
    public static final RegistryObject<AztecDungeonDoorBlock> AZTEC_DUNGEON_DOOR_FRAME_0 = registerDungeonDoor("aztec_dungeon_door_frame_0", DungeonDoorType.AZTEC_DOOR_FRAME_0);
    public static final RegistryObject<AztecDungeonDoorBlock> AZTEC_DUNGEON_DOOR_FRAME_1 = registerDungeonDoor("aztec_dungeon_door_frame_1", DungeonDoorType.AZTEC_DOOR_FRAME_1);



    public static final RegistryObject<Block> ANDESITE_AZTEC_TRAP_0 = registerBlock("andesite_aztec_trap_0", AQCreativeTabs.BLOCKS, () -> new AztecTrapBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_TRAP_1 = registerBlock("andesite_aztec_trap_1", AQCreativeTabs.BLOCKS, () -> new AztecTrapBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> STONE_AZTEC_BRICKS_0 = simpleBlockWithVars("stone_aztec_bricks_0", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);

    public static final RegistryObject<Block> AZTEC_SPRUCE_WOOD_PILLAR = registerBlock("aztec_spruce_wood_pillar", AQCreativeTabs.DECORATION, () -> new AztecWoodPillarBlock(AbstractBlock.Properties.copy(Blocks.SPRUCE_WOOD)));
    public static final RegistryObject<Block> AZTEC_SPRUCE_WOOD_PILLAR_ANDESITE_BASE = registerBlock("aztec_spruce_wood_pillar_andesite_base", AQCreativeTabs.DECORATION, () -> new AztecWoodPillarBaseBlock(AbstractBlock.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> AZTEC_CRAFTING_STATION = registerBlock("aztec_crafting_station",
            () -> new AztecCraftingStationBlock(AbstractBlock.Properties.of(Material.STONE).noOcclusion().strength(1.5F, 4.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE)),
            () -> new AztecCraftingStationBlockItem(AQBlocks.AZTEC_CRAFTING_STATION.get(), new Item.Properties().tab(AQCreativeTabs.DECORATION).setISTER(ISTER::aztecCraftingStation))
    );


    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS = simpleBlockWithVars("gold_aztec_bricks", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_0 = simpleBlockWithVars("gold_aztec_bricks_0", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_1 = simpleBlockWithVars("gold_aztec_bricks_1", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_2 = simpleBlockWithVars("gold_aztec_bricks_2", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_3 = simpleBlockWithVars("gold_aztec_bricks_3", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_4 = simpleBlockWithVars("gold_aztec_bricks_4", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_5 = simpleBlockWithVars("gold_aztec_bricks_5", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_6 = simpleBlockWithVars("gold_aztec_bricks_6", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_7 = simpleBlockWithVars("gold_aztec_bricks_7", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_8 = simpleBlockWithVars("gold_aztec_bricks_8", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_9 = simpleBlockWithVars("gold_aztec_bricks_9", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_10 = simpleBlockWithVars("gold_aztec_bricks_10", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_11 = simpleBlockWithVars("gold_aztec_bricks_11", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_12 = simpleBlockWithVars("gold_aztec_bricks_12", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_13 = simpleBlockWithVars("gold_aztec_bricks_13", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_14 = simpleBlockWithVars("gold_aztec_bricks_14", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_15 = simpleBlockWithVars("gold_aztec_bricks_15", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_16 = simpleBlockWithVars("gold_aztec_bricks_16", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_17 = simpleBlockWithVars("gold_aztec_bricks_17", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_18 = simpleBlockWithVars("gold_aztec_bricks_18", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_19 = simpleBlockWithVars("gold_aztec_bricks_19", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_20 = simpleBlockWithVars("gold_aztec_bricks_20", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_21 = simpleBlockWithVars("gold_aztec_bricks_21", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_22 = simpleBlockWithVars("gold_aztec_bricks_22", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_23 = simpleBlockWithVars("gold_aztec_bricks_23", AQCreativeTabs.BLOCKS, AQBlocks.GOLD_BRICKS_PROP);

    public static final RegistryObject<Block> GOLD_AZTEC_TRAP_0 = registerBlock("gold_aztec_trap_0", AQCreativeTabs.BLOCKS, () -> new AztecTrapBlock(AQBlocks.GOLD_BRICKS_PROP));
    public static final RegistryObject<Block> GOLD_AZTEC_TRAP_1 = registerBlock("gold_aztec_trap_1", AQCreativeTabs.BLOCKS, () -> new AztecTrapBlock(AQBlocks.GOLD_BRICKS_PROP));


    // MISC
    /*
    public static final RegistryObject<DoorBlock> DUNGEON_DOOR_BARS = registerDoorBlock("dungeon_door_bars", () -> new DoorBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion().harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_0 = registerDoorBlock("medieval_door_0", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().harvestTool(ToolType.AXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_1 = registerDoorBlock("medieval_door_1", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_2 = registerDoorBlock("medieval_door_2", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));
     */

    public static final RegistryObject<Block> KNAPPING_TABLE = registerBlock("knapping_table", AQCreativeTabs.DECORATION, KnappingTableBlock::new);




    private static RegistryObject<Block> simpleBlock(String name, ItemGroup itemGroup, Supplier<Block> blockSupplier) {
        RegistryObject<Block> registryObject = registerBlock(name, itemGroup, blockSupplier);
        SIMPLE_BLOCKS.add(registryObject);
        return registryObject;
    }

    private static RegistryObject<Block> simpleBlockWithVars(String name, ItemGroup itemGroup, AbstractBlock.Properties properties) {
        RegistryObject<Block> registryObject = registerBlock(name, itemGroup, () -> new Block(properties));
        SIMPLE_BLOCKS.add(registryObject);

        RegistryObject<SlabBlock> slabRegObject = registerBlock(name + "_slab", itemGroup, () -> new SlabBlock(properties));
        SLAB_VARIANTS.put(registryObject, slabRegObject);

        RegistryObject<VerticalSlabBlock> vertSlabRegObject = registerBlock(name + "_vertical_slab", itemGroup, () -> new VerticalSlabBlock(properties));
        VERT_SLAB_VARIANTS.put(registryObject, vertSlabRegObject);

        RegistryObject<StairsBlock> stairsRegObject = registerBlock(name + "_stairs", itemGroup, () -> new StairsBlock(registryObject.get()::defaultBlockState, properties));
        STAIRS_VARIANTS.put(registryObject, stairsRegObject);

        return registryObject;
    }

    private static RegistryObject<Block> simpleBlockWithVertSlab(String name, ItemGroup itemGroup, Supplier<Block> blockSupplier) {
        RegistryObject<Block> registryObject = registerBlock(name, itemGroup, blockSupplier);
        SIMPLE_BLOCKS.add(registryObject);

        RegistryObject<VerticalSlabBlock> vertSlabRegObject = registerBlock(name + "_vertical_slab", itemGroup, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(registryObject.get())));
        VERT_SLAB_VARIANTS.put(registryObject, vertSlabRegObject);

        return registryObject;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, ItemGroup itemGroup, Supplier<T> blockSupplier) {
        RegistryObject<T> registryObject = BLOCKS.register(name, blockSupplier);
        AQItems.ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(itemGroup)));
        return registryObject;
    }

    private static <T extends Block> RegistryObject<T> registerBlockNoBlockItem(String name, Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    private static <T extends DoorBlock> RegistryObject<T> registerDoorBlock(String name, Supplier<T> doorBlockSupplier) {
        RegistryObject<T> registryObject = BLOCKS.register(name, doorBlockSupplier);
        AQItems.ITEMS.register(name, () -> new TallBlockItem(registryObject.get(), new Item.Properties().tab(AQCreativeTabs.BLOCKS).stacksTo(16)));
        return registryObject;
    }

    private static <T extends Block, B extends BlockItem> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier, Supplier<B> blockItemSupplier) {
        RegistryObject<T> registryObject = BLOCKS.register(name, blockSupplier);
        AQItems.ITEMS.register(name, blockItemSupplier);
        return registryObject;
    }

    private static RegistryObject<AztecDungeonDoorBlock> registerDungeonDoor(String name, DungeonDoorType doorType) {
        RegistryObject<AztecDungeonDoorBlock> registryObject = BLOCKS.register(name, () -> new AztecDungeonDoorBlock(doorType));
        AQItems.ITEMS.register(name, () -> new AztecDungeonDoorBlockItem(registryObject.get()));
        return registryObject;
    }

    private static boolean always(BlockState state, IBlockReader world, BlockPos pos) {
        return true;
    }
}
