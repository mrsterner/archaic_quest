package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.obsidian_core.archaic_quest.common.block.DoubleCropBlock;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import static com.obsidian_core.archaic_quest.common.register.AQBlocks.*;


public class AQBlockStateProvider extends AbstractBlockStateProvider {

    public AQBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {

        SIMPLE_BLOCKS.forEach((block) -> simpleBlockAndItem(block.get()));

        VERT_SLAB_VARIANTS.forEach((block, vertSlab) -> {
            this.verticalSlab(vertSlab.get(), block.get());
        });

        SLAB_VARIANTS.forEach((block, slab) -> {
            this.slab(slab.get(), block.get());
        });

        STAIRS_VARIANTS.forEach((block, stairs) -> {
            this.stairsBlock(stairs.get(), blockTexture(block.get()));
            ModelFile model = models().withExistingParent(name(stairs.get()), mcLoc("block/stairs"));

            simpleBlockItem(stairs.get(), model);
        });

        for (RegistryObject<Block> regObject : BLOCKS.getEntries()) {
            Block block = regObject.get();

            if (block instanceof DoubleCropBlock) {
                doubleCrop((DoubleCropBlock) block);
            }
        }
    }
}
