package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public class AQCreativeTabs extends CreativeModeTab {

    public static final CreativeModeTab BLOCKS = new AQCreativeTabs("blocks", () -> new ItemStack(AQBlocks.ANDESITE_AZTEC_BRICKS_0.get())) {
        @Override
        public void fillItemList(NonNullList<ItemStack> list) {
            super.fillItemList(list);
        }
    };
    public static final CreativeModeTab ITEMS = new AQCreativeTabs("items", () -> new ItemStack(AQItems.CRYSTAL_SKULL.get()));
    public static final CreativeModeTab DECORATION = new AQCreativeTabs("decoration", () -> new ItemStack(AQBlocks.VINES_1.get()));
    public static final CreativeModeTab TOOLS = new AQCreativeTabs("tools", () -> new ItemStack(AQItems.HAMMER_AND_CHISEL.get()));
    public static final CreativeModeTab WEAPONS = new AQCreativeTabs("weapons", () -> new ItemStack(AQItems.BONE_CLUB.get()));
    public static final CreativeModeTab ARMOR = new AQCreativeTabs("armor", () -> new ItemStack(Items.IRON_CHESTPLATE));
    public static final CreativeModeTab FOOD = new AQCreativeTabs("food", () -> new ItemStack(AQItems.CORN.get()));

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
