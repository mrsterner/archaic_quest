package com.obsidian_core.archaic_quest.common.item.data;

import com.obsidian_core.archaic_quest.registry.AQObjects;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import org.jetbrains.annotations.NotNull;

public record AQToolMaterials(int durability, float speed, float damage, int world, int enchantmentValue, Ingredient repairMaterial) implements ToolMaterial {


    public static final AQToolMaterials JADE = new AQToolMaterials(
            1600,
            8.0F,
            3.0F,
            3,
            10,
            Ingredient.ofItems(AQObjects.JADE)
    );
    public static final AQToolMaterials OBSIDIAN = new AQToolMaterials(
            400,
            6.0F,
            5.0F,
            1,
            8,
            Ingredient.ofItems(Items.OBSIDIAN)
    );

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return speed;
    }

    @Override
    public float getAttackDamage() {
        return damage;
    }

    @Override
    public int getMiningLevel() {
        return world;
    }

    @Override
    public int getEnchantability() {
        return enchantmentValue;
    }

    @NotNull
    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial;
    }
}
