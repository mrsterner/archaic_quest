package com.obsidian_core.archaic_quest.common.compat.jei;

import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemDescs {

    protected static final Map<Supplier<ItemStack>, Function<Supplier<ItemStack>, Component>> ITEM_INGREDIENT_INFO = new HashMap<>();

    static {
        ITEM_INGREDIENT_INFO.put(() ->  new ItemStack(AQItems.MACHETE.get()), ItemDescs::jeiDesc);
    }

    private static Component jeiDesc(Supplier<ItemStack> supplier) {
        ItemStack itemStack = supplier.get();
        return Component.translatable(itemStack.getItem().getDescriptionId() + ".jei_desc");
    }
}
