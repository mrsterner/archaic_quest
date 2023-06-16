package com.obsidian_core.archaic_quest.client.render.blockentity.bewlr;

import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonChestBlockEntity;
import com.obsidian_core.archaic_quest.registry.AQObjects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class AztecDungeonChestBEWLR extends BuiltinModelItemRenderer {

    private final AztecDungeonChestBlockEntity blockEntity = new AztecDungeonChestBlockEntity(BlockPos.ORIGIN, AQObjects.AZTEC_DUNGEON_CHEST.getDefaultState());

    public AztecDungeonChestBEWLR(BlockEntityRenderDispatcher renderDispatcher, EntityModelLoader modelSet) {
        super(renderDispatcher, modelSet);
    }

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Item item = stack.getItem();

        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            BlockState state = block.getDefaultState();

            if (state.isOf(AQObjects.AZTEC_DUNGEON_CHEST)) {
                var blockEntityRenderDispatcher = MinecraftClient.getInstance().getBlockEntityRenderDispatcher();
                blockEntityRenderDispatcher.renderEntity(blockEntity, matrices, vertexConsumers, light, overlay);
            }
        }
    }
}
