package com.obsidian_core.archaic_quest.client.render.blockentity.bewlr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.obsidian_core.archaic_quest.common.blockentity.AztecThroneBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecThroneBlockItem;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutWorldRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AztecThroneBEWLR extends BlockEntityWithoutWorldRenderer {

    private final AztecThroneBlockEntity blockEntity = new AztecThroneBlockEntity(BlockPos.ZERO, AQBlocks.AZTEC_THRONE.get().defaultBlockState());

    public AztecThroneBEWLR(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet) {
        super(renderDispatcher, modelSet);
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int overlayTexture) {
        Item item = itemStack.getItem();

        if (item instanceof AztecThroneBlockItem throneItem) {
            blockEntity.setThroneType(throneItem.getThroneType());
            blockEntityRenderDispatcher.renderItem(blockEntity, poseStack, bufferSource, packedLight, overlayTexture);
        }
    }
}
