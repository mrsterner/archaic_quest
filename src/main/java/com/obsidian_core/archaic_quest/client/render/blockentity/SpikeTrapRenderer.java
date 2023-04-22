package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.common.blockentity.AztecThroneBlockEntity;
import com.obsidian_core.archaic_quest.common.blockentity.SpikeTrapBlockEntity;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SpikeTrapRenderer implements BlockEntityRenderer<SpikeTrapBlockEntity> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/block/spike.png");

    private final ModelPart spikes;

    public SpikeTrapRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(AQModelLayers.SPIKE_TRAP);
        spikes = root.getChild("spikes");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition spikes = partdefinition.addOrReplaceChild("spikes", CubeListBuilder.create().texOffs(0, 6).addBox(-4.0F, -28.0F, -5.0F, 0.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-5.0F, -28.0F, -4.0F, 2.0F, 24.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-5.0F, -28.0F, 4.0F, 2.0F, 24.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(-4.0F, -28.0F, 3.0F, 0.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(3.0F, -28.0F, -4.0F, 2.0F, 24.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(4.0F, -28.0F, -5.0F, 0.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(3.0F, -28.0F, 4.0F, 2.0F, 24.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(4.0F, -28.0F, 3.0F, 0.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void render(SpikeTrapBlockEntity spikeTrap, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int overlay) {
        poseStack.translate(0.5D, 0.0D, 0.5D);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));

        int spikeRise = spikeTrap.getSpikeRise();
        float precision = spikeRise >= 3 || spikeRise <= 0 ? 0.0F : partialTick;

        if (spikeTrap.isActive()) {
            precision = spikeRise >= 10 ? 0.0F : precision;
        }
        else {
            precision = spikeRise <= 0 ? 0.0F : -precision;
        }

        double y = (double) (spikeRise + precision) / 2.0D;
        poseStack.translate(0.0D, -y, 0.0D);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(texture));
        spikes.render(poseStack, vertexConsumer, packedLight, overlay);
    }

    @Override
    public int getViewDistance() {
        return 128;
    }
}
