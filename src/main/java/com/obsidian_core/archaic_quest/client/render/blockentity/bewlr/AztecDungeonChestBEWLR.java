package com.obsidian_core.archaic_quest.client.render.blockentity.bewlr;

import com.mojang.blaze3d.vertex.MatrixStack;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonChestBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecThroneBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.renderer.BlockEntityWithoutWorldRenderer;
import net.minecraft.client.renderer.VertexConsumerProvider;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.core.BlockPos;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.state.BlockState;

public class AztecDungeonChestBEWLR extends BuiltinModelItemRenderer {

    private final AztecDungeonChestBlockEntity blockEntity = new AztecDungeonChestBlockEntity(BlockPos.ZERO, AQBlocks.AZTEC_DUNGEON_CHEST.getDefaultState());

    public AztecDungeonChestBEWLR(BlockEntityRenderDispatcher renderDispatcher, EntityModelLoader modelSet) {
        super(renderDispatcher, modelSet);
    }

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Item item = stack.getItem();

        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            BlockState state = block.getDefaultState();

            if (state.isOf(AQBlocks.AZTEC_DUNGEON_CHEST)) {
                var blockEntityRenderDispatcher = MinecraftClient.getInstance().getBlockEntityRenderDispatcher();
                blockEntityRenderDispatcher.renderEntity(blockEntity, matrices, vertexConsumers, light, overlay);
            }
        }
    }
}
