package com.obsidian_core.archaic_quest.common.compat.jei;

import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.network.chat.Text;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemDescs {

    protected static final Map<Supplier<ItemStack>, Function<Supplier<ItemStack>, Text>> ITEM_INGREDIENT_INFO = new HashMap<>();

    static {
        ITEM_INGREDIENT_INFO.put(() ->  new ItemStack(AQItems.MACHETE.get()), ItemDescs::jeiDesc);
    }

    private static Text jeiDesc(Supplier<ItemStack> supplier) {
        ItemStack itemStack = supplier.get();
        return Text.translatable(itemStack.getItem().getDescriptionId() + ".jei_desc");
    }
}
