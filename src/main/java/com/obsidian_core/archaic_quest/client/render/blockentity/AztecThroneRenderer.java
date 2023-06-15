package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.obsidian_core.archaic_quest.common.block.data.ThroneType;
import com.obsidian_core.archaic_quest.common.blockentity.AztecThroneBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;

public class AztecThroneRenderer implements BlockEntityRenderer<AztecThroneBlockEntity> {

    private final ModelPart throneModel;


    public AztecThroneRenderer(BlockEntityRendererFactory.Context context) {
        ModelPart root = context.getLayerModelPart(AQModelLayers.AZTEC_THRONE);
        this.throneModel = root.getChild("throne");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData throne = partdefinition.addChild("throne", ModelPartBuilder.create().uv(74, 0).cuboid(-8.0F, -8.0F, -8.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
                .uv(82, 34).cuboid(-8.0F, -11.0F, -9.0F, 16.0F, 3.0F, 17.0F, new Dilation(0.0F))
                .uv(37, 13).cuboid(8.0F, -13.0F, -11.0F, 6.0F, 13.0F, 25.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-14.0F, -13.0F, -11.0F, 6.0F, 13.0F, 25.0F, new Dilation(0.0F))
                .uv(0, 85).cuboid(-11.0F, -38.0F, 8.0F, 22.0F, 38.0F, 4.0F, new Dilation(0.0F))
                .uv(42, 55).cuboid(7.0F, -17.0F, -11.99F, 8.0F, 4.0F, 26.0F, new Dilation(0.0F))
                .uv(0, 51).cuboid(-15.0F, -17.0F, -11.99F, 8.0F, 4.0F, 26.0F, new Dilation(0.0F))
                .uv(84, 54).cuboid(-11.0F, -44.0F, 5.0F, 22.0F, 6.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = throne.addChild("cube_r1", ModelPartBuilder.create().uv(52, 85).cuboid(-6.0F, -22.0F, -0.001F, 6.0F, 22.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, -17.0F, 6.0F, 0.0F, 0.0F, -0.1309F));
        ModelPartData cube_r2 = throne.addChild("cube_r2", ModelPartBuilder.create().uv(80, 85).cuboid(0.0F, -22.0F, -0.001F, 6.0F, 22.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-13.0F, -17.0F, 6.0F, 0.0F, 0.0F, 0.1309F));

        return TexturedModelData.of(meshdefinition, 256, 256);
    }


    @Override
    public void render(AztecThroneBlockEntity throne, float partialTick, MatrixStack matrices, VertexConsumerProvider bufferSource, int packedLight, int textureOverlay) {
        BlockState state = throne.getWorld() == null ? AQBlocks.AZTEC_CRAFTING_STATION.get().getDefaultState() : throne.getBlockState();
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

        ThroneType type = throne.getThroneType() == null ? ThroneType.THRONE : throne.getThroneType();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderLayer.getEntityCutout(type.getTextureLocation()));
        throneModel.render(matrices, vertexConsumer, packedLight, textureOverlay);

        matrices.pop();
    }

    @Override
    public int getViewDistance() {
        return 80;
    }
}
