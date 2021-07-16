package com.obsidian_core.archaic_quest.datagen.recipe;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractRecipeProvider extends RecipeProvider {

    public AbstractRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    protected String itemName(IItemProvider iItemProvider) {
        return Objects.requireNonNull(iItemProvider.asItem().getRegistryName()).getPath();
    }

    protected String criterionName(IItemProvider criterionIngredient) {
        return "has_" + Objects.requireNonNull(criterionIngredient.asItem().getRegistryName()).getPath();
    }

    protected void stonecutting(IItemProvider result, IItemProvider ingredient, int count, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), result, count)
                .unlocks("has_" + ingredientName, has(ingredient))
                .save(consumer, resultName + "_from_" + ingredientName + "_stonecutting");
    }

    protected void stonecutting(IItemProvider result, ITag.INamedTag<Item> tagIngredient, int count, Consumer<IFinishedRecipe> consumer) {
        String resultName = itemName(result);
        String tagName = tagIngredient.getName().getPath();

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(tagIngredient), result, count)
                .unlocks("has_" + tagName, has(tagIngredient))
                .save(consumer, resultName + "_from_" + tagName + "_stonecutting");
    }

    protected void smelting(IItemProvider result, IItemProvider ingredient, float exp, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(result);
        CookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, exp, 200)
                .unlockedBy("has_" + ingredientName, has(ingredient))
                .save(consumer, ArchaicQuest.resourceLoc(ingredientName + "_from_smelting"));
    }
}
