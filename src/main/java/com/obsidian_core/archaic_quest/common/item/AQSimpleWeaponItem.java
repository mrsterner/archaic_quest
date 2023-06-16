package com.obsidian_core.archaic_quest.common.item;


import com.obsidian_core.archaic_quest.ArchaicQuest;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class AQSimpleWeaponItem extends SwordItem {

    public AQSimpleWeaponItem(ToolMaterial itemTier, int durability, int damage, float attackSpeed) {
        super(itemTier, damage, attackSpeed, new Item.Settings().group(ArchaicQuest.WEAPONS).maxCount(1).maxDamage(durability));
    }
}
