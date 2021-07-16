package com.obsidian_core.archaic_quest.common.item;

import net.minecraft.item.Item;

public class HammerAndChiselItem extends Item {

    public HammerAndChiselItem() {
        super(new Item.Properties().tab(AQCreativeTabs.TOOLS).stacksTo(1).defaultDurability(150));
    }
}
