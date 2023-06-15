package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.common.block.SpikeTrapBlock;
import com.obsidian_core.archaic_quest.common.blockentity.SpikeTrapBlockEntity;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.world.block.SlabBlock;

public class SpikeTrapRenderer implements BlockEntityRenderer<SpikeTrapBlockEntity> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/block/spike.png");
    private static final ResourceLocation masterOverlay = ArchaicQuest.resourceLoc("textures/block/spike_trap_overlay.png");
    private static final ResourceLocation invertMasterOverlay = ArchaicQuest.resourceLoc("textures/block/spike_trap_overlay_inverted.png");


    private final ModelPart spikes;
    private final ModelPart overlay;

    public SpikeTrapRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(AQModelLayers.SPIKE_TRAP);
        ModelPart overlayRoot = context.bakeLayer(AQModelLayers.SPIKE_TRAP_OVERLAY);
        spikes = root.getChild("spikes");
        overlay = overlayRoot.getChild("overlay");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("spikes", CubeListBuilder.create().texOffs(0, 6).addBox(-4.0F, -28.0F, -5.0F, 0.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-5.0F, -28.0F, -4.0F, 2.0F, 24.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-5.0F, -28.0F, 4.0F, 2.0F, 24.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(-4.0F, -28.0F, 3.0F, 0.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(3.0F, -28.0F, -4.0F, 2.0F, 24.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(4.0F, -28.0F, -5.0F, 0.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(3.0F, -28.0F, 4.0F, 2.0F, 24.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(4.0F, -28.0F, 3.0F, 0.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public static LayerDefinition createOverlayBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("overlay", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -4.0F, -8.0F, 16.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void render(SpikeTrapBlockEntity spikeTrap, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
        poseStack.translate(0.5D, 0.0D, 0.5D);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));

        // Render redstone overlay
        if (Minecraft.getInstance().player != null && spikeTrap.getBlockState().getValue(SpikeTrapBlock.MODE) != SpikeTrapBlock.Mode.NORMAL) {
            SpikeTrapBlock.Mode mode = spikeTrap.getBlockState().getValue(SpikeTrapBlock.MODE);

            if (Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.REDSTONE_TORCH) {
                VertexConsumer vertexConsumer1 =
                        buffer.getBuffer(RenderType.entityCutout(mode == SpikeTrapBlock.Mode.MASTER ? masterOverlay : invertMasterOverlay));

                poseStack.scale(1.001F, 1.001F, 1.001F);
                poseStack.translate(0.0D, -1.5D, 0.0D);

                overlay.render(poseStack, vertexConsumer1, packedLight, overlayTexture);

                poseStack.scale(0.999F, 0.999F, 0.999F);
                poseStack.translate(0.0D, 1.5D, 0.0D);
            }
        }

        // Render spikes
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
        spikes.render(poseStack, vertexConsumer, packedLight, overlayTexture);
    }

    @Override
    public int getViewDistance() {
        return 128;
    }
}
