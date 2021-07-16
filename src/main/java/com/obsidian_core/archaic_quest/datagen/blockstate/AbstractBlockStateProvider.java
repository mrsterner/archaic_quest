package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.obsidian_core.archaic_quest.common.block.VerticalSlabBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBlockStateProvider extends BlockStateProvider {

    public static final List<RegistryObject<Block>> SIMPLE_BLOCKS = new ArrayList<>();

    public AbstractBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ArchaicQuest.MODID, exFileHelper);
    }


    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

    public void verticalSlab(VerticalSlabBlock block, Block doubleBlock) {
        verticalSlab(block, doubleBlock, blockTexture(doubleBlock));
    }

    public void verticalSlab(VerticalSlabBlock block, Block doubleBlock, ResourceLocation texture) {
        verticalSlab(block, doubleBlock, texture, texture, texture);
    }

    public void verticalSlab(VerticalSlabBlock block, Block doubleBlock, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        ModelFile model = models().withExistingParent(name(block), ArchaicQuest.MODID + ":block/vertical_slab")
                .texture("side", side)
                .texture("bottom", bottom)
                .texture("top", top);

        verticalSlab(block, model, cubeAll(doubleBlock));
        simpleBlockItem(block, model);
    }

    public void verticalSlab(VerticalSlabBlock block, ModelFile model, ModelFile doubleSlab) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            VerticalSlabBlock.SlabState slabState = state.getValue(VerticalSlabBlock.SLAB_STATE);

            if (slabState == VerticalSlabBlock.SlabState.DOUBLE) {
                return ConfiguredModel.builder()
                        .modelFile(doubleSlab)
                        .build();
            }
            Direction facing = slabState.getDirection();
            int yRot = (int) facing.getOpposite().toYRot();

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .uvLock(true)
                    .build();
            }, VerticalSlabBlock.WATERLOGGED);
    }
}
