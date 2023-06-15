package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.obsidian_core.archaic_quest.common.block.*;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.Identifier;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.SlabBlock;
import net.minecraft.world.world.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public abstract class AbstractBlockStateProvider extends BlockStateProvider {

    public AbstractBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ArchaicQuest.MODID, exFileHelper);
    }

    protected String name(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
    }

    public void simpleBlockAndItem(Block block) {
        this.simpleBlock(block);
        this.simpleBlockItem(block, cubeAll(block));
    }

    public void topBottomCube(Block block, Identifier sides, Identifier topBottom) {
        ModelFile model = models().cubeBottomTop(resLoc(":block/" + name(block)).toString(), sides, topBottom, topBottom);

        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(model)
                .build());

        simpleBlockItem(block, model);
    }

    public void slab(SlabBlock block, Block doubleBlock) {
        slab(block, doubleBlock, blockTexture(doubleBlock));
    }

    public void slab(SlabBlock block, Block doubleBlock, Identifier texture) {
        slab(block, doubleBlock, texture, texture, texture);
    }

    public void slab(SlabBlock block, Block doubleBlock, Identifier side, Identifier bottom, Identifier top) {
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
                .partialState().with(SlabBlock.TPOSITIVE_YE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottomModel))
                .partialState().with(SlabBlock.TPOSITIVE_YE, SlabType.TOP).addModels(new ConfiguredModel(topModel))
                .partialState().with(SlabBlock.TPOSITIVE_YE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubleModel));
    }

    public void simpleVerticalSlab(VerticalSlabBlock block, Block doubleBlock) {
        verticalSlab(block, doubleBlock, blockTexture(doubleBlock), blockTexture(doubleBlock), blockTexture(doubleBlock));
    }

    public void verticalSlab(VerticalSlabBlock block, Block doubleBlock, Identifier side, Identifier bottom, Identifier top) {
        ModelFile model = models().withExistingParent(name(block), resLoc("block/vertical_slab"))
                .texture("side", side)
                .texture("bottom", bottom)
                .texture("top", top);

        verticalSlab(block, model, cubeAll(doubleBlock));
        simpleBlockItem(block, model);
    }


    public void verticalSlab(VerticalSlabBlock block, ModelFile model, ModelFile doubleSlab) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            VerticalSlabBlock.SlabState slabState = state.get(VerticalSlabBlock.SLAB_STATE);

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
        Identifier crossModel = mcLoc("block/cross");

        getVariantBuilder(block).forAllStates((state) -> {
            int age = state.get(block.getAgeProperty());
            boolean top = state.get(DoubleCropBlock.IS_TOP);
            String modelFileName = name(block) + "_stage_" + age + (top ? "_top" : "");

            return ConfiguredModel.builder()
                    .modelFile(models().withExistingParent(modelFileName, crossModel)
                            .renderType("cutout")
                            .texture("cross", texture(modelFileName)))
                    .build();
        });
    }

    public void vine(CoolVinesBlock vineBlock) {
        getVariantBuilder(vineBlock).forAllStatesExcept((state) -> {
            Direction face = state.get(CoolVinesBlock.FACING);
            boolean cut = state.get(CoolVinesBlock.CUT);
            int yRot = (int) face.getOpposite().toYRot();

            String textureName = name(vineBlock) + (cut ? "_cut" : "");
            Identifier modelName = resLoc("block/vine_var_1" + (cut ? "_cut" : ""));

            return ConfiguredModel.builder()
                    .modelFile(models().withExistingParent(name(vineBlock) + (cut ? "_cut" : ""), modelName)
                            .texture("vine", texture(textureName)))
                    .rotationY(yRot)
                    .build();
        }, CoolVinesBlock.CAN_GROW);
        generatedItem(vineBlock);
    }

    public void spearTrap(SpearTrapBlock trapBlock) {
        getVariantBuilder(trapBlock).forAllStatesExcept((state) -> {
            boolean extended = state.get(SpearTrapBlock.EXTENDED);
            Identifier modelName = resLoc("block/spear_trap");

            return ConfiguredModel.builder()
                    .modelFile(models().withExistingParent(extended ? name(trapBlock) + "_extended" : name(trapBlock), modelName)
                            .texture("texture", extended ? texture("spear_trap_base") : texture(name(trapBlock))))
                    .build();
        }, SpearTrapBlock.WATERLOGGED);
        generateItemBlockTexture(trapBlock);
    }

    public void woodPillar(AztecWoodPillarBlock woodPillarBlock) {
        final String[] extendedModels = new String[] {
                "_extended",
                "_connect_x_extended",
                "_connect_z_extended",
                "_connect_xz_extended"
        };
        final String normal = "";
        final String x = "_x";
        final String z = "_z";
        final String connectX = "_connect_x";
        final String connectZ = "_connect_z";
        final String connectXZ = "_connect_xz";

        getVariantBuilder(woodPillarBlock).forAllStatesExcept((state) -> {
            boolean extended = state.get(AztecWoodPillarBlock.EXTENDED);
            Direction.Axis axis = state.get(AztecWoodPillarBlock.AXIS);
            boolean connectedX = state.get(AztecWoodPillarBlock.CONNECTED_X);
            boolean connectedZ = state.get(AztecWoodPillarBlock.CONNECTED_Z);

            String modelName = normal;

            if (extended) {
                if (connectedX && connectedZ)
                    modelName = extendedModels[3];
                else if (connectedZ)
                    modelName = extendedModels[2];
                else if (connectedX)
                    modelName = extendedModels[1];
                else {
                    switch (axis) {
                        case X -> modelName = x;
                        case Z -> modelName = z;
                        case Y -> modelName = extendedModels[0];
                    }
                }
            }
            else {
                if (connectedX && connectedZ)
                    modelName = connectXZ;
                else if (connectedZ)
                    modelName = connectZ;
                else if (connectedX)
                    modelName = connectX;
                else {
                    switch (axis) {
                        case X -> modelName = x;
                        case Z -> modelName = z;
                        case Y -> {
                            // Do nothing; model already initialized as the normal variant
                        }
                    }
                }
            }

            return ConfiguredModel.builder()
                    .modelFile(models().withExistingParent(name(woodPillarBlock) + modelName, resLoc("block/wood_pillar" + modelName))
                            .texture("texture", texture(name(woodPillarBlock))))
                    .build();
        }, AztecWoodPillarBlock.WATERLOGGED);
        simpleBlockItem(woodPillarBlock, models().withExistingParent(name(woodPillarBlock), resLoc("block/wood_pillar")));
    }

    private void generatedItem(Block block) {
        itemModels().withExistingParent(name(block), mcLoc("item/generated"))
                .texture("layer0", itemTexture(name(block)));
    }

    private void generateItemBlockTexture(Block block) {
        itemModels().withExistingParent(name(block), mcLoc("item/generated"))
                .texture("layer0", texture(name(block)));
    }

    public static Identifier resLoc(String path) {
        return ArchaicQuest.resourceLoc(path);
    }

    public static Identifier texture(String textureName) {
        return resLoc("block/" + textureName);
    }

    public static Identifier itemTexture(String textureName) {
        return resLoc("item/" + textureName);
    }
}
