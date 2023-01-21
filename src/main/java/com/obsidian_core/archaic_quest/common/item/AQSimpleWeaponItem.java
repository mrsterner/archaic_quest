package com.obsidian_core.archaic_quest.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class AQSimpleWeaponItem extends SwordItem {

    public AQSimpleWeaponItem(Tier itemTier, int durability, int damage, float attackSpeed) {
        super(itemTier, damage, attackSpeed, new Item.Properties().tab(AQCreativeTabs.WEAPONS).stacksTo(1).durability(durability));
    }
}
