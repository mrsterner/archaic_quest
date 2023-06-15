package com.obsidian_core.archaic_quest.datagen.recipe;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.world.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractRecipeProvider extends RecipeProvider {

    public AbstractRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    protected String itemName(ItemLike itemLike) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(itemLike.asItem())).getPath();
    }

    protected String criterionName(ItemLike criterionIngredient) {
        return "has_" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(criterionIngredient.asItem())).getPath();
    }

    protected void stonecutting(ItemLike result, ItemLike ingredient, int count, Consumer<FinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), result, count)
                .unlockedBy("has_" + ingredientName, has(ingredient))
                .save(consumer, resultName + "_from_" + ingredientName + "_stonecutting");
    }

    protected void stonecutting(ItemLike result, TagKey<Item> tagIngredient, int count, Consumer<FinishedRecipe> consumer) {
        String resultName = itemName(result);
        String tagName = tagIngredient.location().getPath();

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(tagIngredient), result, count)
                .unlockedBy("has_" + tagName, has(tagIngredient))
                .save(consumer, resultName + "_from_" + tagName + "_stonecutting");
    }

    protected void smelting(ItemLike result, ItemLike ingredient, float exp, Consumer<FinishedRecipe> consumer) {
        String ingredientName = itemName(result);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, exp, 200)
                .unlockedBy("has_" + ingredientName, has(ingredient))
                .save(consumer, ArchaicQuest.resourceLoc(ingredientName + "_from_smelting"));
    }
}
