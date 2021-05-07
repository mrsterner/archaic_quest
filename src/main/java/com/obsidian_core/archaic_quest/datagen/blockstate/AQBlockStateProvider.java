package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.obsidian_core.archaic_quest.common.block.SimpleBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AQBlockStateProvider extends BlockStateProvider {

    public AQBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ArchaicQuest.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // Normal 6 side full cube blocks.
        for (Block block : SimpleBlock.SIMPLE_BLOCKS) {
            this.simpleBlock(block);
        }
    }
}
