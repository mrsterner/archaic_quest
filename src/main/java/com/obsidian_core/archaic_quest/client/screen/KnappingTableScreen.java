package com.obsidian_core.archaic_quest.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class KnappingTableScreen extends AbstractContainerScreen<KnappingTableContainer> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/gui/knapping_table.png");

    public KnappingTableScreen(KnappingTableContainer container, Inventory inventory, Component title) {
        super(container, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        imageWidth = 176;
        imageHeight = 166;
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(poseStack);
        renderBg(poseStack, partialTick, mouseX, mouseY);
        super.render(poseStack, mouseX, mouseY, partialTick);
        renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
}
