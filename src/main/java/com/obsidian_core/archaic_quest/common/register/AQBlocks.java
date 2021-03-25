package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.block.AztecTrapBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.TallBlockItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class AQBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArchaicQuest.MODID);

    public static final RegistryObject<Block> ANDESITE_BRICKS = registerBlock("andesite_bricks", AQCreativeTabs.BLOCKS, () ->  new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_0 = registerBlock("andesite_aztec_bricks_0", AQCreativeTabs.BLOCKS , () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_1 = registerBlock("andesite_aztec_bricks_1", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_2 = registerBlock("andesite_aztec_bricks_2", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_3 = registerBlock("andesite_aztec_bricks_3", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_4 = registerBlock("andesite_aztec_bricks_4", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_5 = registerBlock("andesite_aztec_bricks_5", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_6 = registerBlock("andesite_aztec_bricks_6", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_7 = registerBlock("andesite_aztec_bricks_7", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_8 = registerBlock("andesite_aztec_bricks_8", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_9 = registerBlock("andesite_aztec_bricks_9", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_10 = registerBlock("andesite_aztec_bricks_10", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_11 = registerBlock("andesite_aztec_bricks_11", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_12 = registerBlock("andesite_aztec_bricks_12", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_13 = registerBlock("andesite_aztec_bricks_13", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_14 = registerBlock("andesite_aztec_bricks_14", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_15 = registerBlock("andesite_aztec_bricks_15", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_16 = registerBlock("andesite_aztec_bricks_16", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_17 = registerBlock("andesite_aztec_bricks_17", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_18 = registerBlock("andesite_aztec_bricks_18", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_19 = registerBlock("andesite_aztec_bricks_19", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_20 = registerBlock("andesite_aztec_bricks_20", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_21 = registerBlock("andesite_aztec_bricks_21", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_22 = registerBlock("andesite_aztec_bricks_22", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_23 = registerBlock("andesite_aztec_bricks_23", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_24 = registerBlock("andesite_aztec_bricks_24", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_25 = registerBlock("andesite_aztec_bricks_25", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_TRAP = registerBlock("andesite_aztec_trap", AQCreativeTabs.BLOCKS, () -> new AztecTrapBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));

    public static final RegistryObject<DoorBlock> DUNGEON_DOOR_BARS = registerDoorBlock("dungeon_door_bars", () -> new DoorBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion().harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_0 = registerDoorBlock("medieval_door_0", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().harvestTool(ToolType.AXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_1 = registerDoorBlock("medieval_door_1", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_2 = registerDoorBlock("medieval_door_2", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));

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
}
