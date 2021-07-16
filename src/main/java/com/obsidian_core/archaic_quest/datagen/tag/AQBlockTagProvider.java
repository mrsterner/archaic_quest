package com.obsidian_core.archaic_quest.datagen.tag;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.tag.AQBlockTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class AQBlockTagProvider extends BlockTagsProvider {

    public AQBlockTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, ArchaicQuest.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(AQBlockTags.ORE_TIN).add(AQBlocks.TIN_ORE.get());
        this.tag(AQBlockTags.ORE_SILVER).add(AQBlocks.SILVER_ORE.get());
        this.tag(AQBlockTags.ORE_QUARTZ).add(AQBlocks.GRANITE_QUARTZ_ORE.get());
    }
}
