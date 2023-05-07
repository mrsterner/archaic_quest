package com.obsidian_core.archaic_quest.client.render.entity.living;

import com.obsidian_core.archaic_quest.client.render.blockentity.AQModelLayers;
import com.obsidian_core.archaic_quest.client.render.entity.model.TlatlaomiModel;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.living.Tlatlaomi;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TlatlaomiRenderer extends MobRenderer<Tlatlaomi, TlatlaomiModel> {

    private static final ResourceLocation TEXTURE = ArchaicQuest.resourceLoc("textures/entity/tlatlaomi/tlatlaomi.png");

    public TlatlaomiRenderer(EntityRendererProvider.Context context) {
        super(context, new TlatlaomiModel(context.bakeLayer(AQModelLayers.TLATLAOMI)), 0.5F);
        addLayer(new TlatlaomiGlowLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Tlatlaomi p_114482_) {
        return TEXTURE;
    }
}
