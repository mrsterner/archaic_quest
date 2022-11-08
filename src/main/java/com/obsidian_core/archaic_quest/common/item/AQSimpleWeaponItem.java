package com.obsidian_core.archaic_quest.common.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class AQSimpleWeaponItem extends SwordItem {

    public AQSimpleWeaponItem(IItemTier itemTier, int durability, int damage, float attackSpeed) {
        super(itemTier, damage, attackSpeed, new Item.Properties().tab(AQCreativeTabs.WEAPONS).stacksTo(1).durability(durability));
    }
}
