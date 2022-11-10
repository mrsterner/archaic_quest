package com.obsidian_core.archaic_quest.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.obsidian_core.archaic_quest.common.tile.AztecCraftingStationTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ISTER {

    public static Callable<ItemStackTileEntityRenderer> aztecCraftingStation() {
        return () -> new ItemStackTileEntityRenderer() {
            final Supplier<AztecCraftingStationTileEntity> supplier = AztecCraftingStationTileEntity::new;

            @Override
            public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
                TileEntityRendererDispatcher.instance.renderItem(supplier.get(), matrixStack, buffer, combinedLight, combinedOverlay);
            }
        };
    }
}
