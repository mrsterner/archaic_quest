package com.obsidian_core.archaic_quest.client;

import com.obsidian_core.archaic_quest.client.particle.PoisonCloudParticle;
import com.obsidian_core.archaic_quest.client.render.blockentity.*;
import com.obsidian_core.archaic_quest.client.render.blockentity.bewlr.BEWLRS;
import com.obsidian_core.archaic_quest.client.render.entity.living.TlatlaomiRenderer;
import com.obsidian_core.archaic_quest.client.render.entity.model.TlatlaomiModel;
import com.obsidian_core.archaic_quest.client.screen.KnappingTableScreen;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonChestBlockEntity;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.world.FoliageColor;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArchaicQuest.MODID)
public class ClientRegister {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        AQModelLayers.init();
        MinecraftForge.EVENT_BUS.register(new ClientEvents());

        setBlockStateRenderTypes();
        registerScreenMenus();
        addSkippedHighlightBlocks();

        event.enqueueWork(() -> {
            AQItemModelProps.register();
        });
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.register(AQParticles.POISON_CLOUD.get(), PoisonCloudParticle.Factory::new);
    }

    private static void registerScreenMenus() {
        MenuScreens.register(AQContainers.KNAPPING.get(), KnappingTableScreen::new);
    }

    @SubscribeEvent
    public static void registerLayerDefs(EntityRenderersEvent.RegisterTexturedModelDatas event) {
        event.registerTexturedModelData(AQModelLayers.AZTEC_DUNGEON_DOOR, AztecDungeonDoorRenderer::createBodyLayer);
        event.registerTexturedModelData(AQModelLayers.AZTEC_CRAFTING_STATION, AztecCraftingStationRenderer::createBodyLayer);
        event.registerTexturedModelData(AQModelLayers.AZTEC_THRONE, AztecThroneRenderer::createBodyLayer);
        event.registerTexturedModelData(AQModelLayers.SPIKE_TRAP, SpikeTrapRenderer::createBodyLayer);
        event.registerTexturedModelData(AQModelLayers.SPIKE_TRAP_OVERLAY, SpikeTrapRenderer::createOverlayBodyLayer);
        event.registerTexturedModelData(AQModelLayers.AZTEC_DUNGEON_CHEST, AztecDungeonChestRenderer::createBodyLayer);

        event.registerTexturedModelData(AQModelLayers.TLATLAOMI, TlatlaomiModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_DUNGEON_DOOR.get(), AztecDungeonDoorRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_CRAFTING_STATION.get(), AztecCraftingStationRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_THRONE.get(), AztecThroneRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.SPIKE_TRAP.get(), SpikeTrapRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_DUNGEON_CHEST.get(), AztecDungeonChestRenderer::new);

        for (BEWLRS.Holder holder : BEWLRS.BEWLR_LIST) {
            holder.populate(Minecraft.getInstance().getBlockEntityRenderDispatcher());
        }

        event.registerEntityRenderer(AQEntities.TLATLAOMI.get(), TlatlaomiRenderer::new);
    }

    @SubscribeEvent
    public static void registerReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(BEWLRS.MODEL_SET);
    }

    @Deprecated // Render type should be specified in block model
    private static void setBlockStateRenderTypes() {

    }

    private static void addSkippedHighlightBlocks() {
        ClientEvents.skipHighlight(AQBlocks.AZTEC_DUNGEON_DOOR_0);
        ClientEvents.skipHighlight(AQBlocks.AZTEC_DUNGEON_DOOR_1);
        ClientEvents.skipHighlight(AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_0);
        ClientEvents.skipHighlight(AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_1);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, blockDisplayReader, pos, color) -> blockDisplayReader != null && pos != null ? BiomeColors.getAverageFoliageColor(blockDisplayReader, pos) : FoliageColor.getDefaultColor(),
                AQBlocks.VINES_1.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        BlockColors blockColors = event.getBlockColors();

        event.register((itemStack, color) -> {
            BlockState blockState = ((BlockItem) itemStack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(blockState, null, null, color);
        }, AQBlocks.VINES_1.get());
    }
}
