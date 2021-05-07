package com.obsidian_core.archaic_quest.client.render.tile.aztec_crafting_station;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.tile.AztecCraftingStationTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.geometry.IModelGeometryPart;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class AztecCraftingStationTileEntityRenderer<T extends AztecCraftingStationTileEntity> extends TileEntityRenderer<T> {

    private static final ResourceLocation MODEL_LOCATION = ArchaicQuest.resourceLoc("models/tile/aztec_crafting_station.obj");
    private static final ResourceLocation TEXTURE = ArchaicQuest.resourceLoc("textures/tile/aztec_crafting_station/aztec_crafting_station.png");
    private final OBJModel model;

    public AztecCraftingStationTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
        this.model = OBJLoader.INSTANCE.loadModel(new OBJModel.ModelSettings(MODEL_LOCATION, true, true, false, true, null));
    }

    @Override
    public void render(T craftingStation, float p_225616_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int p_225616_5_, int p_225616_6_) {
        matrixStack.pushPose();

        Minecraft.getInstance().textureManager.bind(TEXTURE);
    }
}
