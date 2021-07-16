package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraftforge.client.model.generators.IGeneratedBlockstate;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

import static com.obsidian_core.archaic_quest.common.register.AQBlocks.*;

public class AQBlockStateProvider extends AbstractBlockStateProvider {

    public AQBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {

        // Normal 6 side full cube blocks.
        for (Supplier<Block> blockSupplier : SIMPLE_BLOCKS) {
            this.simpleBlock(blockSupplier.get());
            this.simpleBlockItem(blockSupplier.get(), cubeAll(blockSupplier.get()));
        }

        this.verticalSlab(ANDESITE_BRICK_VERTICAL_SLAB.get(), ANDESITE_BRICKS.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_0.get(), ANDESITE_AZTEC_BRICKS_0.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_1.get(), ANDESITE_AZTEC_BRICKS_1.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_2.get(), ANDESITE_AZTEC_BRICKS_2.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_3.get(), ANDESITE_AZTEC_BRICKS_3.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_4.get(), ANDESITE_AZTEC_BRICKS_4.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_5.get(), ANDESITE_AZTEC_BRICKS_5.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_6.get(), ANDESITE_AZTEC_BRICKS_6.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_7.get(), ANDESITE_AZTEC_BRICKS_7.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_8.get(), ANDESITE_AZTEC_BRICKS_8.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_9.get(), ANDESITE_AZTEC_BRICKS_9.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_10.get(), ANDESITE_AZTEC_BRICKS_10.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_11.get(), ANDESITE_AZTEC_BRICKS_11.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_12.get(), ANDESITE_AZTEC_BRICKS_12.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_13.get(), ANDESITE_AZTEC_BRICKS_13.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_14.get(), ANDESITE_AZTEC_BRICKS_14.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_15.get(), ANDESITE_AZTEC_BRICKS_15.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_17.get(), ANDESITE_AZTEC_BRICKS_17.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_18.get(), ANDESITE_AZTEC_BRICKS_18.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_19.get(), ANDESITE_AZTEC_BRICKS_19.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_20.get(), ANDESITE_AZTEC_BRICKS_20.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_21.get(), ANDESITE_AZTEC_BRICKS_21.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_22.get(), ANDESITE_AZTEC_BRICKS_22.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_23.get(), ANDESITE_AZTEC_BRICKS_23.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_24.get(), ANDESITE_AZTEC_BRICKS_24.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_25.get(), ANDESITE_AZTEC_BRICKS_25.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_26.get(), ANDESITE_AZTEC_BRICKS_26.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_27.get(), ANDESITE_AZTEC_BRICKS_27.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_28.get(), ANDESITE_AZTEC_BRICKS_28.get());
        this.verticalSlab(ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_29.get(), ANDESITE_AZTEC_BRICKS_29.get());
    }
}
