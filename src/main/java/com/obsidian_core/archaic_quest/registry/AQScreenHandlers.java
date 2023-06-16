package com.obsidian_core.archaic_quest.registry;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AQScreenHandlers {
    Map<Identifier, ScreenHandlerType<? extends ScreenHandler>> SCREEN_HANDLERS = new LinkedHashMap<>();

    ScreenHandlerType<KnappingTableContainer> KNAPPING_SCREEN_HANDLER = register("knapping", KnappingTableContainer::new);

    static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        ScreenHandlerType<T> screenHandlerType = new ScreenHandlerType<>(factory);
        SCREEN_HANDLERS.put(ArchaicQuest.id(id), screenHandlerType);
        return screenHandlerType;
    }

    static void init() {
        SCREEN_HANDLERS.forEach((id, handler) -> Registry.register(Registry.SCREEN_HANDLER, id, handler));
    }
}
