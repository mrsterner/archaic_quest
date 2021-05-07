package com.obsidian_core.archaic_quest.common.core;

import com.obsidian_core.archaic_quest.common.event.BiomeEvents;
import com.obsidian_core.archaic_quest.common.register.*;
import com.obsidian_core.archaic_quest.common.worldgen.feature.AQConfiguredFeatures;
import net.minecraft.loot.conditions.LootConditionManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ArchaicQuest.MODID)
public class ArchaicQuest {

    public static final String MODID = "archaic_quest";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public ArchaicQuest() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::onCommonSetup);

        MinecraftForge.EVENT_BUS.register(new BiomeEvents());

        AQBlocks.BLOCKS.register(eventBus);
        AQItems.ITEMS.register(eventBus);
        AQGlobalLootModifiers.GLOBAL_LOOT_MODIFIERS.register(eventBus);
        AQSoundEvents.SOUNDS.register(eventBus);
        AQTileEntities.TILE_ENTITIES.register(eventBus);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AQConfiguredFeatures.register();
        });
    }

    public static ResourceLocation resourceLoc(String path) {
        return new ResourceLocation(MODID, path);
    }
}
