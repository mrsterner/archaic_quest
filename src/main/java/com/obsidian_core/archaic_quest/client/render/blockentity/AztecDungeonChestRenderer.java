package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.block.AztecDungeonChestBlock;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonChestBlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

public class AztecDungeonChestRenderer implements BlockEntityRenderer<AztecDungeonChestBlockEntity> {

    private static final Identifier texture = ArchaicQuest.id("textures/block/aztec_dungeon_chest.png");

    private final ModelPart lid;
    private final ModelPart chest;

    public AztecDungeonChestRenderer(BlockEntityRendererFactory.Context context) {
        ModelPart root = context.getLayerModelPart(AQModelLayers.AZTEC_DUNGEON_CHEST);
        lid = root.getChild("lid");
        chest = root.getChild("chest");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        // Lid
        partdefinition.addChild("lid", ModelPartBuilder.create().uv(0, 24).cuboid(-14.0F, -2.0F, -11.0F, 28.0F, 2.0F, 20.0F, new Dilation(0.0F)), ModelTransform.offset(0.0F, 8.0F, 1.0F));

        // Chest
        partdefinition.addChild("chest", ModelPartBuilder.create().uv(0, 0).cuboid(-15.0F, -2.0F, -11.0F, 30.0F, 2.0F, 22.0F, new Dilation(0.0F))
                .uv(0, 66).cuboid(-13.0F, -14.0F, -9.0F, 26.0F, 2.0F, 18.0F, new Dilation(0.0F))
                .uv(82, 6).cuboid(-11.0F, -16.0F, 7.0F, 22.0F, 2.0F, 4.0F, new Dilation(0.0F))
                .uv(72, 50).cuboid(-12.0F, -12.0F, -8.0F, 24.0F, 8.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 66).cuboid(-14.0F, -14.0F, -10.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 46).cuboid(-14.0F, -14.0F, 6.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 24).cuboid(10.0F, -14.0F, 6.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(10.0F, -14.0F, -10.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 46).cuboid(-13.0F, -4.0F, -9.0F, 26.0F, 2.0F, 18.0F, new Dilation(0.0F))
                .uv(66, 74).cuboid(-15.0F, -16.0F, -11.0F, 4.0F, 2.0F, 22.0F, new Dilation(0.0F))
                .uv(74, 24).cuboid(11.0F, -16.0F, -11.0F, 4.0F, 2.0F, 22.0F, new Dilation(0.0F))
                .uv(82, 0).cuboid(-11.0F, -16.0F, -11.0F, 22.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 256, 256);
    }

    @Override
    public void render(AztecDungeonChestBlockEntity dungeonChest, float partialTick, MatrixStack matrices, VertexConsumerProvider bufferSource, int packedLight, int textureOverlay) {
        float rot = 22.5F * (float) dungeonChest.getBlockState().get(AztecDungeonChestBlock.ROTATION);
        matrices.translate(0.5D, 1.5F, 0.5D);

        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-rot));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F));

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderLayer.getEntityCutout(texture));
        chest.render(matrices, vertexConsumer, packedLight, textureOverlay);

        lid.yRot = dungeonChest.getOpenNess(partialTick) * ((float) Math.PI / 4F);

        lid.render(matrices, vertexConsumer, packedLight, textureOverlay);
    }
}
