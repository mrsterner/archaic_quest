package com.obsidian_core.archaic_quest.datagen.tag;

import com.obsidian_core.archaic_quest.common.block.CoolVinesBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.tag.AQBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.world.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class AQBlockTagProvider extends BlockTagsProvider {

    public AQBlockTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, ArchaicQuest.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(AQBlockTags.ORE_TIN).add(AQBlocks.TIN_ORE.get());
        tag(AQBlockTags.ORE_SILVER).add(AQBlocks.SILVER_ORE.get());
        tag(AQBlockTags.ORE_QUARTZ).add(AQBlocks.GRANITE_QUARTZ_ORE.get());

        tag(BlockTags.SAPLINGS).add(
                AQBlocks.AZTEC_JUNGLE_SAPLING.get()
        );

        TagsProvider.TagAppender<Block> CLIMBABLE = tag(BlockTags.CLIMBABLE);

        for (RegistryObject<Block> regObject : AQBlocks.REGISTRY.getEntries()) {
            Block block = regObject.get();

            if (block instanceof CoolVinesBlock) {
                CLIMBABLE.add(block);
            }
        }

        AQBlocks.BLOCK_TAGS.forEach((registryObject, tagKeys) -> {
            for (TagKey<Block> tagKey : tagKeys) {
                tag(tagKey).add(registryObject.get());
            }
        });
    }
}
