package com.obsidian_core.archaic_quest.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ClientEvents {

    public static final List<Block> HIGHLIGHT_SKIPPED_BLOCKS = new ArrayList<>();

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public void onDrawBlockHighlight(DrawHighlightEvent.HighlightBlock event) {
        BlockRayTraceResult result = event.getTarget();
        ClientWorld world = Minecraft.getInstance().level;

        if (HIGHLIGHT_SKIPPED_BLOCKS.contains(world.getBlockState(result.getBlockPos()).getBlock())) {
            event.setCanceled(true);
        }
    }
}
