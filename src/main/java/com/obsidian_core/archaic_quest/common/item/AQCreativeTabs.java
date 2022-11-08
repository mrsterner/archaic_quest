package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.register.AQItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;

import java.util.function.Supplier;

public class AQCreativeTabs extends ItemGroup {

    public static final ItemGroup BLOCKS = new AQCreativeTabs("blocks", () -> new ItemStack(AQBlocks.ANDESITE_AZTEC_BRICKS_0.get()));
    public static final ItemGroup ITEMS = new AQCreativeTabs("items", () -> new ItemStack(AQItems.CRYSTAL_SKULL.get()));
    public static final ItemGroup DECORATION = new AQCreativeTabs("decoration", () -> new ItemStack(AQBlocks.VINES_1.get()));
    public static final ItemGroup TOOLS = new AQCreativeTabs("tools", () -> new ItemStack(AQItems.HAMMER_AND_CHISEL.get()));
    public static final ItemGroup WEAPONS = new AQCreativeTabs("weapons", () -> new ItemStack(AQItems.BONE_CLUB.get()));
    public static final ItemGroup ARMOR = new AQCreativeTabs("armor", () -> new ItemStack(Items.IRON_CHESTPLATE));
    public static final ItemGroup FOOD = new AQCreativeTabs("food", () -> new ItemStack(AQItems.CORN.get()));

    private final Supplier<ItemStack> itemIcon;


    private AQCreativeTabs(String label, Supplier<ItemStack> itemIcon) {
        super(ArchaicQuest.MODID + "_" + label);
        this.itemIcon = itemIcon;
    }

    @Override
    public ItemStack makeIcon() {
        return itemIcon.get();
    }
}
