package com.obsidian_core.archaic_quest.client.render.blockentity.bewlr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonChestBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecThroneBlockItem;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutWorldRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.state.BlockState;

public class AztecDungeonChestBEWLR extends BlockEntityWithoutWorldRenderer {

    private final AztecDungeonChestBlockEntity blockEntity = new AztecDungeonChestBlockEntity(BlockPos.ZERO, AQBlocks.AZTEC_DUNGEON_CHEST.get().defaultBlockState());

    public AztecDungeonChestBEWLR(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet) {
        super(renderDispatcher, modelSet);
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int overlayTexture) {
        Item item = itemStack.getItem();

        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            BlockState state = block.defaultBlockState();

            if (state.is(AQBlocks.AZTEC_DUNGEON_CHEST.get())) {
                blockEntityRenderDispatcher.renderItem(blockEntity, poseStack, bufferSource, packedLight, overlayTexture);
            }
        }
    }
}
