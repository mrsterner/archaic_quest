package com.obsidian_core.archaic_quest.common.core;

import com.obsidian_core.archaic_quest.common.event.BiomeEvents;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import com.obsidian_core.archaic_quest.common.network.PacketHandler;
import com.obsidian_core.archaic_quest.common.register.*;
import com.obsidian_core.archaic_quest.common.worldgen.feature.AQConfiguredFeatures;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.inventory.CraftingInventory;
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

    private final PacketHandler packetHandler = new PacketHandler();

    public ArchaicQuest() {
        packetHandler.registerMessages();

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::onCommonSetup);

        MinecraftForge.EVENT_BUS.register(new BiomeEvents());

        AQBlocks.REGISTRY.register(eventBus);
        AQItems.REGISTRY.register(eventBus);
        AQEntities.REGISTRY.register(eventBus);
        AQGlobalLootModifiers.REGISTRY.register(eventBus);
        AQSoundEvents.REGISTRY.register(eventBus);
        AQContainers.REGISTRY.register(eventBus);
        AQTileEntities.REGISTRY.register(eventBus);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(AQConfiguredFeatures::register);
    }

    public static ResourceLocation resourceLoc(String path) {
        return new ResourceLocation(MODID, path);
    }
}
