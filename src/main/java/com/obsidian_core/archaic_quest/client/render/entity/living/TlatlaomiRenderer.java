package com.obsidian_core.archaic_quest.client.render.entity.living;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.client.render.blockentity.AQModelLayers;
import com.obsidian_core.archaic_quest.client.render.entity.model.TlatlaomiModel;
import com.obsidian_core.archaic_quest.common.entity.living.Tlatlaomi;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class TlatlaomiRenderer extends MobEntityRenderer<Tlatlaomi, TlatlaomiModel> {

    private static final Identifier TEXTURE = ArchaicQuest.id("textures/entity/tlatlaomi/tlatlaomi.png");

    public TlatlaomiRenderer(EntityRendererFactory.Context context) {
        super(context, new TlatlaomiModel(context.getPart(AQModelLayers.TLATLAOMI)), 0.5F);
        addFeature(new TlatlaomiGlowLayer(this));
    }

    @Override
    public Identifier getTexture(Tlatlaomi entity) {
        return TEXTURE;
    }
}
