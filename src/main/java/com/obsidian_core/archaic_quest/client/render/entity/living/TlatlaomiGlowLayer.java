package com.obsidian_core.archaic_quest.client.render.entity.living;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.obsidian_core.archaic_quest.client.render.entity.model.TlatlaomiModel;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.living.Tlatlaomi;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class TlatlaomiGlowLayer extends RenderLayer<Tlatlaomi, TlatlaomiModel> {

    private static final ResourceLocation TEXTURE = ArchaicQuest.resourceLoc("textures/entity/tlatlaomi/glow_overlay.png");

    public TlatlaomiGlowLayer(RenderLayerParent<Tlatlaomi, TlatlaomiModel> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Tlatlaomi tlatlaomi, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(TEXTURE));
        getParentModel().renderToBuffer(poseStack, vertexConsumer, LightTexture.pack(15, 15), OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.0F);
    }
}
