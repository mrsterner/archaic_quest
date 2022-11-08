package com.obsidian_core.archaic_quest.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
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
    @SuppressWarnings({"deprecation", "ConstantConditions"})
    protected void renderBg(MatrixStack matrixStack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(texture);
        int leftPos = this.leftPos;
        int topPos = this.topPos;
        blit(matrixStack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);

        /*
        if (menu.isLit()) {
            int k = this.menu.getLitProgress();
            blit(matrixStack, leftPos + 56, topPos + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.menu.getBurnProgress();
        blit(matrixStack, leftPos + 79, topPos + 34, 176, 14, l + 1, 16);

         */
    }
}
