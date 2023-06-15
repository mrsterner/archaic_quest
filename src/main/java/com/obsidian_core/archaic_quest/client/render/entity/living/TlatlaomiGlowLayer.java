package com.obsidian_core.archaic_quest.client.render.entity.living;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.client.render.entity.model.TlatlaomiModel;
import com.obsidian_core.archaic_quest.common.entity.living.Tlatlaomi;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TlatlaomiGlowLayer extends FeatureRenderer<Tlatlaomi, TlatlaomiModel> {

    private static final Identifier TEXTURE = ArchaicQuest.id("textures/entity/tlatlaomi/glow_overlay.png");

    public TlatlaomiGlowLayer(FeatureRendererContext<Tlatlaomi, TlatlaomiModel> parent) {
        super(parent);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider buffer, int packedLight, Tlatlaomi tlatlaomi, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderLayer.getEntityCutout(TEXTURE));
        getContextModel().render(matrices, vertexConsumer, LightmapTextureManager.pack(15, 15), OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.0F);
    }
}
