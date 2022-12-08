package com.obsidian_core.archaic_quest.common.item.data;

import com.obsidian_core.archaic_quest.common.register.AQItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public class AQItemTier implements IItemTier {


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




    private AQItemTier(int durability, float speed, float damage, int level, int enchantmentValue, Supplier<Ingredient> repairMaterial) {
        this.durability = durability;
        this.speed = speed;
        this.damage = damage;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
        this.repairMaterial = repairMaterial;
    }

    private final int durability;
    private final float speed;
    private final float damage;
    private final int level;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairMaterial;

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
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
