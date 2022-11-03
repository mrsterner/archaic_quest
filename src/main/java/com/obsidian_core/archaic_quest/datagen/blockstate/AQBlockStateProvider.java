package com.obsidian_core.archaic_quest.datagen.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import static com.obsidian_core.archaic_quest.common.register.AQBlocks.VERT_SLAB_VARIANTS;
import static com.obsidian_core.archaic_quest.common.register.AQBlocks.SLAB_VARIANTS;


public class AQBlockStateProvider extends AbstractBlockStateProvider {

    public AQBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {

        for (RegistryObject<Block> simpleBlock : SIMPLE_BLOCKS) {
            this.simpleBlockAndItem(simpleBlock.get());
        }

        VERT_SLAB_VARIANTS.forEach((block, vertSlab) -> {
            this.verticalSlab(vertSlab.get(), block.get());
        });

        SLAB_VARIANTS.forEach((block, slab) -> {
            this.slabBlock(slab.get(), models().modLoc("models/block/" + block.getId().getPath()), blockTexture(block.get()));
        });
    }
}
