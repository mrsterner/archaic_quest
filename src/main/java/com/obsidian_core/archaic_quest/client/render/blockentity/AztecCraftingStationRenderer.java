package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.blockentity.AztecCraftingStationBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;

public class AztecCraftingStationRenderer implements BlockEntityRenderer<AztecCraftingStationBlockEntity> {

    private static final Identifier texture = ArchaicQuest.id("textures/block/aztec_crafting_station.png");

    private final ModelPart table;
    private final ModelPart misc;


    public AztecCraftingStationRenderer(BlockEntityRendererFactory.Context context) {
        ModelPart root = context.getLayerModelPart(AQModelLayers.AZTEC_CRAFTING_STATION);
        this.table = root.getChild("table");
        this.misc = root.getChild("misc");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshDef = new ModelData();
        ModelPartData partDef = meshDef.getRoot();

        ModelPartData table = partDef.addChild("table", ModelPartBuilder.create().uv(0, 193).cuboid(-16.0F, -4.0F, -8.0F, 32.0F, 4.0F, 24.0F, new Dilation(0.0F))
                .uv(173, 53).cuboid(-16.0F, -12.0F, 12.99F, 2.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(176, 53).cuboid(-16.0F, -12.0F, -5.99F, 2.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(173, 53).cuboid(13.99F, -12.0F, 12.99F, 2.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(176, 53).cuboid(13.99F, -12.0F, -5.99F, 2.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 165).cuboid(-14.0F, -12.0F, -6.001F, 28.0F, 8.0F, 20.0F, new Dilation(0.0F))
                .uv(0, 228).cuboid(-19.0F, -16.0F, -8.0F, 38.0F, 4.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, -8.0F));

        ModelPartData cube5_r1 = table.addChild("cube5_r1", ModelPartBuilder.create().uv(71, 72).cuboid(-1.0F, -9.0F, -9.0001F, 2.0F, 9.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 71).cuboid(-1.0F, -9.0F, -18.9999F, 2.0F, 9.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(14.0F, -4.0F, 13.0F, 0.0F, 0.0F, 0.2618F));

        ModelPartData cube8_r1 = table.addChild("cube8_r1", ModelPartBuilder.create().uv(0, 46).cuboid(-1.0F, -9.0F, -15.0001F, 2.0F, 9.0F, 10.0F, new Dilation(0.0F))
                .uv(40, 40).cuboid(-1.0F, -9.0F, -25.0F, 2.0F, 9.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(-14.0F, -4.0F, 19.0F, 0.0F, 0.0F, -0.2618F));

        ModelPartData misc = partDef.addChild("misc", ModelPartBuilder.create(), ModelTransform.of(0.0F, 8.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData hammer = misc.addChild("hammer", ModelPartBuilder.create().uv(115, 6).cuboid(-3.0F, -3.0F, -2.0F, 5.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(122, 46).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(11.0F, 0.0F, 5.0F, -0.1745F, -0.8308F, 0.0F));

        ModelPartData chissel = misc.addChild("chissel", ModelPartBuilder.create().uv(145, 67).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(116, 7).cuboid(-1.5F, -1.0F, -4.0F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-9.0F, 0.0F, 5.0F, 0.0F, 0.3491F, 0.0F));

        ModelPartData paper = misc.addChild("paper", ModelPartBuilder.create().uv(155, 29).cuboid(-0.5365F, -0.01F, -7.3187F, 5.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        ModelPartData cube17_r1 = paper.addChild("cube17_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5365F, 0.3051F, -5.0868F, 5.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.7581F, -4.3137F, -0.3491F, 0.0F, 0.0F));

        ModelPartData patterntiles = misc.addChild("patterntiles", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, -4.0F));

        ModelPartData cube13_r1 = patterntiles.addChild("cube13_r1", ModelPartBuilder.create().uv(240, 248).cuboid(-3.0F, -7.0F, 2.0F, 6.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.0F, -16.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData cube12_r1 = patterntiles.addChild("cube12_r1", ModelPartBuilder.create().uv(240, 248).cuboid(-4.0F, -6.1F, -0.25F, 6.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -16.0F, 2.0F, 0.1745F, 0.0F, 0.0F));

        ModelPartData cube14_r1 = patterntiles.addChild("cube14_r1", ModelPartBuilder.create().uv(240, 248).cuboid(-3.0F, -7.0F, 2.0F, 6.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -14.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        return TexturedModelData.of(meshDef, 256, 256);
    }

    @Override
    public void render(AztecCraftingStationBlockEntity craftingStation, float partialTick, MatrixStack matrices, VertexConsumerProvider bufferSource, int packedLight, int textureOverlay) {
        BlockState state = craftingStation.getWorld() == null ? AQBlocks.AZTEC_CRAFTING_STATION.getDefaultState() : craftingStation.getBlockState();
        Direction direction = state.get(Properties.HORIZONTAL_FACING);
        float rotation = direction.toYRot();

        matrices.push();
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-rotation));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F));

        double x, z;

        switch (direction) {
            // NORTH & default
            default -> {
                x = 0.5D;
                z = -0.5D;
            }
            case SOUTH -> {
                x = -0.5D;
                z = 0.5D;
            }
            case WEST -> {
                x = -0.5D;
                z = -0.5D;
            }
            case EAST -> {
                x = 0.5D;
                z = 0.5D;
            }
        }
        matrices.translate(x, -1.5D, z);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderLayer.getEntityCutout(texture));
        table.render(matrices, vertexConsumer, packedLight, textureOverlay);
        misc.render(matrices, vertexConsumer, packedLight, textureOverlay);

        matrices.pop();
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
