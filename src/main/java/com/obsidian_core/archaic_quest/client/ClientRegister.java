package com.obsidian_core.archaic_quest.client;

import com.obsidian_core.archaic_quest.client.render.tile.*;
import com.obsidian_core.archaic_quest.client.render.tile.bewlr.BEWLRS;
import com.obsidian_core.archaic_quest.client.screen.KnappingTableScreen;
import com.obsidian_core.archaic_quest.common.block.DoubleCropBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.AQContainers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArchaicQuest.MODID)
public class ClientRegister {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        AQModelLayers.init();
        MinecraftForge.EVENT_BUS.register(new ClientEvents());

        setBlockRenderTypes();
        registerScreenMenus();
        addSkippedHighlightBlocks();
    }

    private static void registerScreenMenus() {
        MenuScreens.register(AQContainers.KNAPPING.get(), KnappingTableScreen::new);
    }

    @SubscribeEvent
    public static void registerLayerDefs(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AQModelLayers.AZTEC_DUNGEON_DOOR, AztecDungeonDoorRenderer::createBodyLayer);
        event.registerLayerDefinition(AQModelLayers.AZTEC_CRAFTING_STATION, AztecCraftingStationRenderer::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBERs(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_DUNGEON_DOOR.get(), AztecDungeonDoorRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_CRAFTING_STATION.get(), AztecCraftingStationRenderer::new);


        for (BEWLRS.Holder holder : BEWLRS.BEWLR_LIST) {
            holder.populate(Minecraft.getInstance().getBlockEntityRenderDispatcher());
        }
    }

    @SubscribeEvent
    public static void registerReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(BEWLRS.MODEL_SET);
    }

    @Deprecated // Render type should be specified in block model
    private static void setBlockRenderTypes() {

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
            BlockState blockState = ((BlockItem) itemStack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockState, null, null, color);
        }, AQBlocks.VINES_1.get());
    }
}
