package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

public class AQCreativeTabs extends ItemGroup {

    public static final ItemGroup BLOCKS = new AQCreativeTabs("blocks", () -> Items.ANDESITE);
    public static final ItemGroup ITEMS = new AQCreativeTabs("items", () -> Items.STICK);
    public static final ItemGroup DECORATION = new AQCreativeTabs("decoration", () -> Items.POPPY);
    public static final ItemGroup TOOLS = new AQCreativeTabs("tools", () -> Items.IRON_PICKAXE);
    public static final ItemGroup WEAPONS = new AQCreativeTabs("weapons", () -> Items.IRON_SWORD);
    public static final ItemGroup ARMOR = new AQCreativeTabs("armor", () -> Items.IRON_CHESTPLATE);

    private final Supplier<Item> itemIcon;

    public AQCreativeTabs(String label, Supplier<Item> itemIcon) {
        super(ArchaicQuest.MODID + "_" + label);
        this.itemIcon = itemIcon;
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(this.itemIcon.get());
    }
}
