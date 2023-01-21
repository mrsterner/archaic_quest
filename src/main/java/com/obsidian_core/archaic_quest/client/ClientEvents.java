package com.obsidian_core.archaic_quest.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.event.RenderHighlightEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ClientEvents {

    public static final List<Block> HIGHLIGHT_SKIPPED_BLOCKS = new ArrayList<>();

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public void onDrawBlockHighlight(RenderHighlightEvent.Block event) {
        BlockHitResult result = event.getTarget();
        ClientLevel world = Minecraft.getInstance().level;

        if (HIGHLIGHT_SKIPPED_BLOCKS.contains(world.getBlockState(result.getBlockPos()).getBlock())) {
            event.setCanceled(true);
        }
    }
}
