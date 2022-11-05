package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.block.*;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import com.obsidian_core.archaic_quest.datagen.blockstate.AQBlockStateProvider;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.TallBlockItem;
import net.minecraft.state.properties.BlockStateProperties;
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

    private static final AbstractBlock.Properties ANDESITE_BRICKS_PROP;

    static {
        ANDESITE_BRICKS_PROP = AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).harvestTool(ToolType.PICKAXE);
    }


    // ORES
    public static final RegistryObject<Block> TIN_ORE = simpleBlock("tin_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
    public static final RegistryObject<Block> SILVER_ORE = simpleBlock("silver_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> BASALT_ORE = simpleBlock("basalt_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> GRANITE_QUARTZ_ORE = simpleBlock("granite_quartz_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
    public static final RegistryObject<Block> ANDESITE_TURQUOISE_ORE = simpleBlock("andesite_turquoise_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> DIORITE_JADE_ORE = simpleBlock("diorite_jade_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> ONYX = simpleBlock("onyx", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3)));

    // AZTEC STUFF
    public static final RegistryObject<Block> ANDESITE_BRICKS = simpleBlockWithVars("andesite_bricks", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
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
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_15 = simpleBlockWithVars("andesite_aztec_bricks_15", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_16 = registerBlock("andesite_aztec_bricks_16", AQCreativeTabs.BLOCKS, () -> new Block(ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_17 = simpleBlockWithVars("andesite_aztec_bricks_17", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_18 = simpleBlockWithVars("andesite_aztec_bricks_18", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_19 = simpleBlockWithVars("andesite_aztec_bricks_19", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_20 = simpleBlockWithVars("andesite_aztec_bricks_20", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_21 = simpleBlockWithVars("andesite_aztec_bricks_21", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_22 = simpleBlockWithVars("andesite_aztec_bricks_22", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_23 = simpleBlockWithVars("andesite_aztec_bricks_23", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_24 = registerBlock("andesite_aztec_bricks_24", AQCreativeTabs.BLOCKS, () -> new Block(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_25 = simpleBlockWithVars("andesite_aztec_bricks_25", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_26 = registerBlock("andesite_aztec_bricks_26", AQCreativeTabs.BLOCKS, () -> new Block(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_27 = registerBlock("andesite_aztec_bricks_27", AQCreativeTabs.BLOCKS, () -> new SimpleHorizontalBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_28 = simpleBlockWithVars("andesite_aztec_bricks_28", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_29 = registerBlock("andesite_aztec_bricks_29", AQCreativeTabs.BLOCKS, () -> new SimpleHorizontalBlock(AQBlocks.ANDESITE_BRICKS_PROP));

    public static final RegistryObject<Block> AZTEC_PILLAR = registerBlock("aztec_pillar", AQCreativeTabs.DECORATION, () -> new ChiselPillarBlock(AQBlocks.ANDESITE_BRICKS_PROP));

    public static final RegistryObject<Block> ANDESITE_AZTEC_TRAP_0 = registerBlock("andesite_aztec_trap_0", AQCreativeTabs.BLOCKS, () -> new AztecTrapBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> ANDESITE_AZTEC_TRAP_1 = registerBlock("andesite_aztec_trap_1", AQCreativeTabs.BLOCKS, () -> new AztecTrapBlock(AQBlocks.ANDESITE_BRICKS_PROP));
    public static final RegistryObject<Block> STONE_AZTEC_BRICKS_0 = simpleBlockWithVars("stone_aztec_bricks_0", AQCreativeTabs.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP);

    public static final RegistryObject<Block> AZTEC_SPRUCE_WOOD_PILLAR = registerBlock("aztec_spruce_wood_pillar", AQCreativeTabs.BLOCKS, () -> new AztecWoodPillarBlock(AbstractBlock.Properties.copy(Blocks.SPRUCE_WOOD)));
    public static final RegistryObject<Block> AZTEC_SPRUCE_WOOD_PILLAR_ANDESITE_BASE = registerBlock("aztec_spruce_wood_pillar_andesite_base", AQCreativeTabs.BLOCKS, () -> new AztecWoodPillarBaseBlock(AbstractBlock.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> AZTEC_CRAFTING_STATION = registerBlock("aztec_crafting_station", AQCreativeTabs.BLOCKS, () -> new AztecCraftingStationBlock(AbstractBlock.Properties.of(Material.STONE).noOcclusion().strength(1.5F, 4.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE)));



    // MISC
    public static final RegistryObject<DoorBlock> DUNGEON_DOOR_BARS = registerDoorBlock("dungeon_door_bars", () -> new DoorBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion().harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_0 = registerDoorBlock("medieval_door_0", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().harvestTool(ToolType.AXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_1 = registerDoorBlock("medieval_door_1", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_2 = registerDoorBlock("medieval_door_2", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));





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
        AQItems.ITEMS.register(name, () -> new TallBlockItem(registryObject.get(), new Item.Properties().tab(AQCreativeTabs.BLOCKS).stacksTo(16)));
        return registryObject;
    }

    private static boolean always(BlockState state, IBlockReader world, BlockPos pos) {
        return true;
    }
}
