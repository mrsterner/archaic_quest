package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.block.SpikeTrapBlock;
import com.obsidian_core.archaic_quest.common.blockentity.SpikeTrapBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

public class SpikeTrapRenderer implements BlockEntityRenderer<SpikeTrapBlockEntity> {

    private static final Identifier texture = ArchaicQuest.id("textures/block/spike.png");
    private static final Identifier masterOverlay = ArchaicQuest.id("textures/block/spike_trap_overlay.png");
    private static final Identifier invertMasterOverlay = ArchaicQuest.id("textures/block/spike_trap_overlay_inverted.png");


    private final ModelPart spikes;
    private final ModelPart overlay;

    public SpikeTrapRenderer(BlockEntityRendererFactory.Context context) {
        ModelPart root = context.getLayerModelPart(AQModelLayers.SPIKE_TRAP);
        ModelPart overlayRoot = context.getLayerModelPart(AQModelLayers.SPIKE_TRAP_OVERLAY);
        spikes = root.getChild("spikes");
        overlay = overlayRoot.getChild("overlay");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        partdefinition.addChild("spikes", ModelPartBuilder.create().uv(0, 6).cuboid(-4.0F, -28.0F, -5.0F, 0.0F, 24.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(-5.0F, -28.0F, -4.0F, 2.0F, 24.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(-5.0F, -28.0F, 4.0F, 2.0F, 24.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(-4.0F, -28.0F, 3.0F, 0.0F, 24.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(3.0F, -28.0F, -4.0F, 2.0F, 24.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(4.0F, -28.0F, -5.0F, 0.0F, 24.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(3.0F, -28.0F, 4.0F, 2.0F, 24.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(4.0F, -28.0F, 3.0F, 0.0F, 24.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 32, 32);
    }

    public static TexturedModelData createOverlayBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        partdefinition.addChild("overlay", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -4.0F, -8.0F, 16.0F, 4.0F, 16.0F, new Dilation(0.0F)), ModelTransform.offset(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 64, 64);
    }


    @Override
    public void render(SpikeTrapBlockEntity spikeTrap, float partialTick, MatrixStack matrices, VertexConsumerProvider buffer, int packedLight, int overlayTexture) {
        matrices.translate(0.5D, 0.0D, 0.5D);
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F));

        // Render redstone overlay
        if (MinecraftClient.getInstance().player != null && spikeTrap.getBlockState().get(SpikeTrapBlock.MODE) != SpikeTrapBlock.Mode.NORMAL) {
            SpikeTrapBlock.Mode mode = spikeTrap.getBlockState().get(SpikeTrapBlock.MODE);

            if (MinecraftClient.getInstance().player.getStackInHand(Hand.MAIN_HAND).getItem() == Items.REDSTONE_TORCH) {
                VertexConsumer vertexConsumer1 =
                        buffer.getBuffer(RenderLayer.getEntityCutout(mode == SpikeTrapBlock.Mode.MASTER ? masterOverlay : invertMasterOverlay));

                matrices.scale(1.001F, 1.001F, 1.001F);
                matrices.translate(0.0D, -1.5D, 0.0D);

                overlay.render(matrices, vertexConsumer1, packedLight, overlayTexture);

                matrices.scale(0.999F, 0.999F, 0.999F);
                matrices.translate(0.0D, 1.5D, 0.0D);
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
        matrices.translate(0.0D, -y, 0.0D);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderLayer.getEntityCutout(texture));
        spikes.render(matrices, vertexConsumer, packedLight, overlayTexture);
    }

    @Override
    public int getViewDistance() {
        return 128;
    }
}
