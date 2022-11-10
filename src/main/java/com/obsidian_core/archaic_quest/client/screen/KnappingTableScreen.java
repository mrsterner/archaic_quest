package com.obsidian_core.archaic_quest.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
import net.minecraft.client.gui.screen.HopperScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class KnappingTableScreen extends ContainerScreen<KnappingTableContainer> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/gui/knapping_table.png");

    public KnappingTableScreen(KnappingTableContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
    }

    @Override
    public void init() {
        super.init();
        this.titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(matrixStack);
        renderBg(matrixStack, partialTick, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, partialTick);
        renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    @SuppressWarnings({"deprecation", "ConstantConditions"})
    protected void renderBg(MatrixStack matrixStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bind(texture);
        int leftPos = this.leftPos;
        int topPos = this.topPos;
        blit(matrixStack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
    }
}
