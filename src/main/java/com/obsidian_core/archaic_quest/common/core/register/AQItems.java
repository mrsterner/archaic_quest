package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.*;
import com.obsidian_core.archaic_quest.common.item.data.AQItemTier;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodSettings;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class AQItems {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ArchaicQuest.MODID);


    public static final List<RegistryObject<? extends Item>> SIMPLE_ITEMS = new ArrayList<>();
    public static final List<RegistryObject<ForgeSpawnEggItem>> SPAWN_EGGS = new ArrayList<>();


    // FOOD
    public static final RegistryObject<Item> CORN = registerSimpleItem("corn_cob", () -> new ItemNameBlockItem(AQBlocks.CORN_CROP.get(), new Item.Settings()
            .tab(AQCreativeTabs.FOOD)
            .food(new FoodSettings.Builder()
                    .nutrition(2)
                    .saturationMod(1.0F)
                    .build())));

    // MISC
    public static final RegistryObject<Item> TIN_INGOT = registerSimpleItem("tin_ingot", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> SILVER_INGOT = registerSimpleItem("silver_ingot", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> TURQUOISE = registerSimpleItem("turquoise", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> JADE = registerSimpleItem("jade", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> PEBBLE = registerSimpleItem("pebble", PebbleItem::new);

    public static final RegistryObject<Item> ADVENTURERS_BAG = registerSimpleItem("adventurers_bag", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> ADVENTURERS_GLOBE = registerSimpleItem("adventurers_globe", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> ADVENTURERS_HAT = registerSimpleItem("adventurers_hat", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> ADVENTURERS_MAGNIFYING_GLASS = registerSimpleItem("adventurers_magnifying_glass", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> ADVENTURERS_SPYGLASS = registerSimpleItem("adventurers_spyglass", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> ADVENTURERS_TORCH = registerItem("adventurers_torch", AdventurersTorchItem::new);
    public static final RegistryObject<Item> AMBER = registerSimpleItem("amber", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AMBER_FOSSIL_0 = registerSimpleItem("amber_fossil_0", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AMBER_FOSSIL_1 = registerSimpleItem("amber_fossil_1", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_GOLD_TALISMAN = registerSimpleItem("aztec_gold_talisman", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_GUIDE_BOOK = registerSimpleItem("aztec_guide_book", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_JADE_TALISMAN = registerSimpleItem("aztec_jade_talisman", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_PAN_FLUTE = registerSimpleItem("aztec_pan_flute", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_PIPE = registerSimpleItem("aztec_pipe", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_RITUAL_CHALICE = registerSimpleItem("aztec_ritual_chalice", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_RITUAL_STAFF = registerSimpleItem("aztec_ritual_staff", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_DEATH_WHISTLE = registerSimpleItem("aztec_death_whistle", AztecDeathWhistleItem::new);
    public static final RegistryObject<Item> HEART = registerSimpleItem("heart", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> JAGUAR_HIDE = registerSimpleItem("jaguar_hide", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> LEATHER_QUIVER = registerSimpleItem("leather_quiver", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> AZTEC_JAGUAR_QUIVER = registerSimpleItem("aztec_jaguar_quiver", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> OLD_BONE = registerSimpleItem("old_bone", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> POISONOUS_FROG_0 = registerSimpleItem("poisonous_frog_0", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> POISONOUS_FROG_1 = registerSimpleItem("poisonous_frog_1", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> SKULL_0 = registerSimpleItem("skull_0", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> SKULL_1 = registerSimpleItem("skull_1", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> CRYSTAL_SKULL = registerSimpleItem("crystal_skull", AQCreativeTabs.ITEMS);
    public static final RegistryObject<Item> STONE_SKULL = registerSimpleItem("stone_skull", AQCreativeTabs.ITEMS);


    // TOOLS & WEAPONS
    public static final RegistryObject<Item> MACHETE = registerSimpleItem("machete", () -> new MacheteItem(Tiers.IRON, 200, 2, -1.0F));
    public static final RegistryObject<Item> HAMMER_AND_CHISEL = registerSimpleItem("hammer_and_chisel", HammerAndChiselItem::new);
    public static final RegistryObject<Item> WHIP = registerSimpleItem("whip", AQCreativeTabs.ITEMS);

    public static final RegistryObject<Item> WOOD_BONE_DAGGER = registerSimpleItem("wood_bone_dagger", () -> new AQSimpleWeaponItem(Tiers.WOOD, 60, 3, 0.0F));
    public static final RegistryObject<Item> WOOD_JADE_DAGGER = registerSimpleItem("wood_jade_dagger", () -> new AQSimpleWeaponItem(AQItemTier.JADE, 60, 3, 0.0F));
    public static final RegistryObject<Item> WOOD_OBSIDIAN_DAGGER = registerSimpleItem("wood_obsidian_dagger", () -> new AQSimpleWeaponItem(AQItemTier.OBSIDIAN, 60, 3, 0.0F));
    public static final RegistryObject<Item> BONE_CLUB = registerSimpleItem("bone_club", () -> new AQSimpleWeaponItem(Tiers.WOOD, 60, 5, -3.0F));

    public static final RegistryObject<Item> BONE_BLOWPIPE = registerSimpleItem("bone_blowpipe", AQCreativeTabs.WEAPONS);
    public static final RegistryObject<Item> WOODEN_BLOWPIPE = registerSimpleItem("wooden_blowpipe", AQCreativeTabs.WEAPONS);
    public static final RegistryObject<Item> WOODEN_DART = registerSimpleItem("wooden_dart", AQCreativeTabs.WEAPONS);
    public static final RegistryObject<Item> BONE_DART = registerSimpleItem("bone_dart", AQCreativeTabs.WEAPONS);


    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> itemSupplier) {
        return REGISTRY.register(name, itemSupplier);
    }

    private static RegistryObject<Item> registerSimpleItem(String name, Supplier<Item> itemSupplier) {
        RegistryObject<Item> regObject = REGISTRY.register(name, itemSupplier);
        SIMPLE_ITEMS.add(regObject);
        return regObject;
    }

    private static RegistryObject<Item> registerSimpleItem(String name, CreativeModeTab itemGroup, FoodSettings food) {
        RegistryObject<Item> regObject = REGISTRY.register(name, () -> new Item(new Item.Settings().tab(itemGroup).food(food)));
        SIMPLE_ITEMS.add(regObject);
        return regObject;
    }

    private static RegistryObject<Item> registerSimpleItem(String name, CreativeModeTab itemGroup) {
        RegistryObject<Item> regObject = REGISTRY.register(name, () -> new Item(new Item.Settings().tab(itemGroup)));
        SIMPLE_ITEMS.add(regObject);
        return regObject;
    }

    protected static <T extends Mob> RegistryObject<ForgeSpawnEggItem> registerSpawnEgg(RegistryObject<EntityType<T>> entityType, int primaryColor, int secondaryColor) {
        RegistryObject<ForgeSpawnEggItem> regObj = REGISTRY.register(entityType.getId().getPath() + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Settings().tab(CreativeModeTab.TAB_MISC)));
        SPAWN_EGGS.add(regObj);
        return regObj;
    }
}
