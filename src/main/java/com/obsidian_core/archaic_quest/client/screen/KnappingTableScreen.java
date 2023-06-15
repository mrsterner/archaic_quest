package com.obsidian_core.archaic_quest.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;
import org.w3c.dom.Text;

public class KnappingTableScreen extends HandledScreen<KnappingTableContainer> {

    private static final Identifier texture = ArchaicQuest.id("textures/gui/knapping_table.png");

    public KnappingTableScreen(KnappingTableContainer container, PlayerInventory inventory, Text title) {
        super(container, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        backgroundWidth = 176;
        backgroundHeight = 166;
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTick) {
        renderBackground(matrices);
        drawBackground(matrices, partialTick, mouseX, mouseY);
        super.render(matrices, mouseX, mouseY, partialTick);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }
}
