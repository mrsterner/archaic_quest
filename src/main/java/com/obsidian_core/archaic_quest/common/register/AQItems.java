package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class AQItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArchaicQuest.MODID);


    public static final List<RegistryObject<? extends Item>> SIMPLE_ITEMS = new ArrayList<>();


    // FOOD
    public static final RegistryObject<Item> CORN = registerSimpleItem("corn_cob", () -> new BlockNamedItem(AQBlocks.CORN_CROP.get(), new Item.Properties()
            .tab(AQCreativeTabs.FOOD)
            .food(new Food.Builder()
                    .nutrition(2)
                    .saturationMod(1.0F)
                    .build())));

    public static final RegistryObject<Item> TIN_INGOT = registerSimpleItem("tin_ingot", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> SILVER_INGOT = registerSimpleItem("silver_ingot", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> TURQUOISE = registerSimpleItem("turquoise", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> JADE = registerSimpleItem("jade", AQCreativeTabs.ITEMS);

    // TOOLS & WEAPONS
    public static final RegistryObject<Item> BONE_CLUB = registerSimpleItem("bone_club", () -> new AQSimpleWeaponItem(ItemTier.WOOD, 60, 5, -3.0F));
    public static final RegistryObject<Item> MACHETE = registerSimpleItem("machete", () -> new MacheteItem(ItemTier.IRON, 200, 2, -1.2F));

    public static final RegistryObject<Item> PEBBLE = registerSimpleItem("pebble", PebbleItem::new);
    public static final RegistryObject<Item> CRYSTAL_SKULL = registerSimpleItem("crystal_skull", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_DEATH_WHISTLE = registerSimpleItem("aztec_death_whistle", AztecDeathWhistleItem::new);

    public static final RegistryObject<Item> HAMMER_AND_CHISEL = registerSimpleItem("hammer_and_chisel", HammerAndChiselItem::new);

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    private static RegistryObject<Item> registerSimpleItem(String name, Supplier<Item> itemSupplier) {
        RegistryObject<Item> regObject = ITEMS.register(name, itemSupplier);
        SIMPLE_ITEMS.add(regObject);
        return regObject;
    }


    private static RegistryObject<Item> registerSimpleItem(String name, ItemGroup itemGroup, Food food) {
        RegistryObject<Item> regObject = ITEMS.register(name, () -> new Item(new Item.Properties().tab(itemGroup).food(food)));
        SIMPLE_ITEMS.add(regObject);
        return regObject;
    }

    private static RegistryObject<Item> registerSimpleItem(String name, ItemGroup itemGroup) {
        RegistryObject<Item> regObject = ITEMS.register(name, () -> new Item(new Item.Properties().tab(itemGroup)));
        SIMPLE_ITEMS.add(regObject);
        return regObject;
    }
}
