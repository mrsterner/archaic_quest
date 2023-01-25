package com.obsidian_core.archaic_quest.datagen.tag;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBiomes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class AQBiomeTagProvider extends BiomeTagsProvider {

    public AQBiomeTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper fileHelper) {
        super(dataGenerator, ArchaicQuest.MODID, fileHelper);
    }

    @Override
    protected void addTags() {
        tag(BiomeTags.IS_JUNGLE).add(
                AQBiomes.AZTEC_JUNGLE.get()
        );

        this.tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS).add(
                AQBiomes.AZTEC_JUNGLE.get()
        );
    }
}
