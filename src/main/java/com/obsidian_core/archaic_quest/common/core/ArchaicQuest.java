package com.obsidian_core.archaic_quest.common.core;

import com.obsidian_core.archaic_quest.common.compat.terrablender.AQTerraBlender;
import com.obsidian_core.archaic_quest.common.event.BiomeEvents;
import com.obsidian_core.archaic_quest.common.item.AdventurersTorchItem;
import com.obsidian_core.archaic_quest.common.misc.AQDamageSources;
import com.obsidian_core.archaic_quest.common.network.PacketHandler;
import com.obsidian_core.archaic_quest.common.core.register.*;
import com.obsidian_core.archaic_quest.common.worldgen.feature.AQConfiguredFeatures;
import com.obsidian_core.archaic_quest.common.worldgen.feature.decorators.AQTreeDecoratorType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.world.worldgen.GenerationStep;
import net.minecraft.world.world.worldgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ArchaicQuest.MODID)
public class ArchaicQuest {

    public static final String MODID = "archaic_quest";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @SuppressWarnings("FieldCanBeLocal")
    private final PacketHandler packetHandler = new PacketHandler();

    public ArchaicQuest() {
        packetHandler.registerMessages();

        AQDamageSources.init();

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::onCommonSetup);
        eventBus.addListener(AQEntities::registerAttributes);

        MinecraftForge.EVENT_BUS.register(new BiomeEvents());

        AQBlocks.REGISTRY.register(eventBus);
        AQItems.REGISTRY.register(eventBus);
        AQEntities.REGISTRY.register(eventBus);
        AQBiomes.REGISTRY.register(eventBus);
        AQParticles.REGISTRY.register(eventBus);
        AQBiomeModifiers.REGISTRY.register(eventBus);
        AQGlobalLootModifiers.REGISTRY.register(eventBus);
        AQSoundEvents.REGISTRY.register(eventBus);
        AQContainers.REGISTRY.register(eventBus);
        AQBlockEntities.REGISTRY.register(eventBus);
        AQConfiguredFeatures.CF_REGISTRY.register(eventBus);
        AQConfiguredFeatures.P_REGISTRY.register(eventBus);
        AQTreeDecoratorType.REGISTRY.register(eventBus);
        AQStructures.REGISTRY.register(eventBus);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AdventurersTorchItem.registerDefaults();

            if (ModList.get().isLoaded("terrablender")) {
                AQTerraBlender.setup();
            }
        });
    }

    public static ResourceLocation resourceLoc(String path) {
        return new ResourceLocation(MODID, path);
    }
}
