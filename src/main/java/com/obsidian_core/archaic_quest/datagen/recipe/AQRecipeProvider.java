package com.obsidian_core.archaic_quest.datagen.recipe;

import com.mojang.math.MethodsReturnNonnullByDefault;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.world.block.Blocks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AQRecipeProvider extends AbstractRecipeProvider {

    public AQRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        this.stonecutting(AQItems.PEBBLE.get(), Blocks.COBBLESTONE, 9, consumer);
        this.stonecutting(AQBlocks.ANDESITE_BRICKS.get(), Items.ANDESITE, 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_0.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_1.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_2.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_3.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_4.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_5.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_6.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_7.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_8.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_9.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_10.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_11.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_12.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_13.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_14.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_15.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_16.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_17.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_18.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_19.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_20.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_21.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_22.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_23.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_24.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_25.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_26.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_27.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_28.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_29.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_30.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_31.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_32.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_33.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.ANDESITE_AZTEC_BRICKS_34.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);
        this.stonecutting(AQBlocks.STONE_AZTEC_BRICKS_0.get(), AQBlocks.ANDESITE_BRICKS.get(), 1, consumer);

        this.smelting(AQItems.TIN_INGOT.get(), AQBlocks.TIN_ORE.get(), 0.7F, consumer);
        this.smelting(AQItems.SILVER_INGOT.get(), AQBlocks.SILVER_ORE.get(), 0.7F, consumer);
        this.smelting(Items.QUARTZ, AQBlocks.GRANITE_QUARTZ_ORE.get(), 0.2F, consumer);
        this.smelting(AQItems.JADE.get(), AQBlocks.DIORITE_JADE_ORE.get(), 1.0F, consumer);
        this.smelting(AQItems.TURQUOISE.get(), AQBlocks.ANDESITE_TURQUOISE_ORE.get(), 1.0F, consumer);

        ShapedRecipeBuilder.shaped(Blocks.COBBLESTONE, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AQItems.PEBBLE.get())
                .unlockedBy(criterionName(AQItems.PEBBLE.get()), has(AQItems.PEBBLE.get()));

        ShapedRecipeBuilder.shaped(AQBlocks.ANDESITE_BRICKS.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', Blocks.POLISHED_ANDESITE)
                .unlockedBy(criterionName(Blocks.POLISHED_ANDESITE), has(Blocks.POLISHED_ANDESITE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(AQBlocks.ANDESITE_AZTEC_TRAP_0.get(), 1)
                .requires(Blocks.DISPENSER)
                .requires(AQBlocks.ANDESITE_AZTEC_BRICKS_25.get())
                .unlockedBy(criterionName(Blocks.DISPENSER), has(Blocks.DISPENSER))
                .unlockedBy(criterionName(AQBlocks.ANDESITE_AZTEC_BRICKS_25.get()), has(AQBlocks.ANDESITE_AZTEC_BRICKS_25.get()))
                .save(consumer);
    }
}
