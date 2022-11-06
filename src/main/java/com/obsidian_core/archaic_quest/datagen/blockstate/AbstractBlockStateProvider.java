package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.obsidian_core.archaic_quest.common.block.DoubleCropBlock;
import com.obsidian_core.archaic_quest.common.block.VerticalSlabBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public abstract class AbstractBlockStateProvider extends BlockStateProvider {

    public AbstractBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ArchaicQuest.MODID, exFileHelper);
    }

    protected String name(Block block) {
        return Objects.requireNonNull(block.getRegistryName()).getPath();
    }

    public void simpleBlockAndItem(Block block) {
        this.simpleBlock(block);
        this.simpleBlockItem(block, cubeAll(block));
    }

    public void topBottomCube(Block block, ResourceLocation sides, ResourceLocation topBottom) {
        ModelFile model = models().cubeBottomTop(resLoc(":block/" + name(block)).toString(), sides, topBottom, topBottom);

        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(model)
                .build());

        simpleBlockItem(block, model);
    }

    public void slab(SlabBlock block, Block doubleBlock) {
        slab(block, doubleBlock, blockTexture(doubleBlock));
    }

    public void slab(SlabBlock block, Block doubleBlock, ResourceLocation texture) {
        slab(block, doubleBlock, texture, texture, texture);
    }

    public void slab(SlabBlock block, Block doubleBlock, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        ModelFile topModel = models().withExistingParent(name(block) + "_top", mcLoc("block/slab_top"))
                .texture("side", side)
                .texture("bottom", bottom)
                .texture("top", top);

        ModelFile bottomModel = models().withExistingParent(name(block), mcLoc("block/slab"))
                .texture("side", side)
                .texture("bottom", bottom)
                .texture("top", top);

        slab(block, topModel, bottomModel, cubeAll(doubleBlock));
        simpleBlockItem(block, bottomModel);
    }

    public void slab(SlabBlock block, ModelFile topModel, ModelFile bottomModel, ModelFile doubleModel) {
        getVariantBuilder(block)
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottomModel))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(topModel))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubleModel));
    }

    public void verticalSlab(VerticalSlabBlock block, Block doubleBlock) {
        verticalSlab(block, doubleBlock, blockTexture(doubleBlock));
    }

    public void verticalSlab(VerticalSlabBlock block, Block doubleBlock, ResourceLocation texture) {
        verticalSlab(block, doubleBlock, texture, texture, texture);
    }

    public void verticalSlab(VerticalSlabBlock block, Block doubleBlock, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        ModelFile model = models().withExistingParent(name(block), resLoc("block/vertical_slab"))
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

    public void doubleCrop(DoubleCropBlock block) {
        ResourceLocation crossModel = mcLoc("block/cross");

        getVariantBuilder(block).forAllStates((state) -> {
            int age = state.getValue(block.getAgeProperty());
            boolean top = state.getValue(DoubleCropBlock.IS_TOP);
            String modelFileName = name(block) + "_stage_" + age + (top ? "_top" : "");

            return ConfiguredModel.builder()
                    .modelFile(models().withExistingParent(modelFileName, crossModel)
                            .texture("cross", texture(modelFileName)))
                    .build();
        });
    }

    public static ResourceLocation resLoc(String path) {
        return ArchaicQuest.resourceLoc(path);
    }

    public static ResourceLocation texture(String textureName) {
        return resLoc("block/" + textureName);
    }
}
