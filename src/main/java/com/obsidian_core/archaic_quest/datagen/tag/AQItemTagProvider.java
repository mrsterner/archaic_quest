package com.obsidian_core.archaic_quest.datagen.tag;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.register.AQItems;
import com.obsidian_core.archaic_quest.common.tag.AQBlockTags;
import com.obsidian_core.archaic_quest.common.tag.AQItemTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;


public class AQItemTagProvider extends ItemTagsProvider {

    public AQItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, ArchaicQuest.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.copy(AQBlockTags.ORE_TIN, AQItemTags.ORE_TIN);
        this.copy(AQBlockTags.ORE_SILVER, AQItemTags.ORE_SILVER);
        this.copy(AQBlockTags.ORE_QUARTZ, AQItemTags.ORE_QUARTZ);

        this.tag(AQItemTags.INGOT_TIN).add(AQItems.TIN_INGOT.get());
        this.tag(AQItemTags.INGOT_SILVER).add(AQItems.SILVER_INGOT.get());
        this.tag(Tags.Items.GEMS).add(AQItems.JADE.get());
    }
}
