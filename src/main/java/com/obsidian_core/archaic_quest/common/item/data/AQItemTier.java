package com.obsidian_core.archaic_quest.common.item.data;

import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public record AQItemTier(int durability, float speed, float damage, int level, int enchantmentValue,
                         Supplier<Ingredient> repairMaterial) implements Tier {


    public static final AQItemTier JADE = new AQItemTier(
            1600,
            8.0F,
            3.0F,
            3,
            10,
            () -> Ingredient.of(AQItems.JADE.get())
    );
    public static final AQItemTier OBSIDIAN = new AQItemTier(
            400,
            6.0F,
            5.0F,
            1,
            8,
            () -> Ingredient.of(Items.OBSIDIAN)
    );


    @Override
    public int getUses() {
        return durability;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getWorld() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
