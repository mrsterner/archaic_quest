package com.obsidian_core.archaic_quest;

import com.obsidian_core.archaic_quest.client.screen.KnappingTableScreen;
import com.obsidian_core.archaic_quest.registry.AQScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ArchaicQuestClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(AQScreenHandlers.KNAPPING_SCREEN_HANDLER, KnappingTableScreen::new);
    }
}
