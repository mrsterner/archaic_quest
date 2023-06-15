package com.obsidian_core.archaic_quest.client.render.blockentity.bewlr;

import com.obsidian_core.archaic_quest.common.blockentity.AztecThroneBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecThroneBlockItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class AztecThroneBEWLR extends BuiltinModelItemRenderer {

    private final AztecThroneBlockEntity blockEntity = new AztecThroneBlockEntity(BlockPos.ZERO, AQBlocks.AZTEC_THRONE.getDefaultState());

    public AztecThroneBEWLR(BlockEntityRenderDispatcher renderDispatcher, EntityModelLoader modelSet) {
        super(renderDispatcher, modelSet);
    }

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Item item = stack.getItem();

        if (item instanceof AztecThroneBlockItem throneItem) {
            blockEntity.setThroneType(throneItem.getThroneType());
            var blockEntityRenderDispatcher = MinecraftClient.getInstance().getBlockEntityRenderDispatcher();
            blockEntityRenderDispatcher.renderEntity(blockEntity, matrices, vertexConsumers, light, overlay);
        }
    }
}
