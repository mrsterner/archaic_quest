package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.common.block.AztecDungeonChestBlock;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonChestBlockEntity;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SkullBlock;

public class AztecDungeonChestRenderer implements BlockEntityRenderer<AztecDungeonChestBlockEntity> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/block/aztec_dungeon_chest.png");

    private final ModelPart lid;
    private final ModelPart chest;

    public AztecDungeonChestRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(AQModelLayers.AZTEC_DUNGEON_CHEST);
        lid = root.getChild("lid");
        chest = root.getChild("chest");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Lid
        partdefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 24).addBox(-14.0F, -2.0F, -11.0F, 28.0F, 2.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 1.0F));

        // Chest
        partdefinition.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -2.0F, -11.0F, 30.0F, 2.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).addBox(-13.0F, -14.0F, -9.0F, 26.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(82, 6).addBox(-11.0F, -16.0F, 7.0F, 22.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(72, 50).addBox(-12.0F, -12.0F, -8.0F, 24.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).addBox(-14.0F, -14.0F, -10.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-14.0F, -14.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(10.0F, -14.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(10.0F, -14.0F, -10.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-13.0F, -4.0F, -9.0F, 26.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(66, 74).addBox(-15.0F, -16.0F, -11.0F, 4.0F, 2.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(74, 24).addBox(11.0F, -16.0F, -11.0F, 4.0F, 2.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(82, 0).addBox(-11.0F, -16.0F, -11.0F, 22.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void render(AztecDungeonChestBlockEntity dungeonChest, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay) {
        float rot = 22.5F * (float) dungeonChest.getBlockState().getValue(AztecDungeonChestBlock.ROTATION);
        poseStack.translate(0.5D, 1.5F, 0.5D);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(-rot));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(texture));
        chest.render(poseStack, vertexConsumer, packedLight, textureOverlay);

        lid.yRot = dungeonChest.getOpenNess(partialTick) * ((float) Math.PI / 4F);

        lid.render(poseStack, vertexConsumer, packedLight, textureOverlay);
    }
}
