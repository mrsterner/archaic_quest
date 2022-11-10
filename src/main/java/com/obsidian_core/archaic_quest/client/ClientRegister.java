package com.obsidian_core.archaic_quest.client;

import com.obsidian_core.archaic_quest.client.render.tile.AztecCraftingStationRenderer;
import com.obsidian_core.archaic_quest.client.render.tile.AztecDungeonDoorRenderer;
import com.obsidian_core.archaic_quest.client.screen.KnappingTableScreen;
import com.obsidian_core.archaic_quest.common.block.CoolVinesBlock;
import com.obsidian_core.archaic_quest.common.block.DoubleCropBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.register.AQContainers;
import com.obsidian_core.archaic_quest.common.register.AQTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArchaicQuest.MODID)
public class ClientRegister {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        setBlockRenderTypes();
        registerScreenMenus();
        registerTileEntityRenderers();
    }

    private static void registerScreenMenus() {
        ScreenManager.register(AQContainers.KNAPPING.get(), KnappingTableScreen::new);
    }

    private static void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(AQTileEntities.AZTEC_DUNGEON_DOOR.get(), AztecDungeonDoorRenderer::new);
        ClientRegistry.bindTileEntityRenderer(AQTileEntities.AZTEC_CRAFTING_STATION.get(), AztecCraftingStationRenderer::new);
    }

    private static void setBlockRenderTypes() {
        RenderTypeLookup.setRenderLayer(AQBlocks.AZTEC_CRAFTING_STATION.get(), RenderType.cutout());
        /*
        RenderTypeLookup.setRenderLayer(AQBlocks.DUNGEON_DOOR_BARS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(AQBlocks.MEDIEVAL_DOOR_0.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(AQBlocks.MEDIEVAL_DOOR_1.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(AQBlocks.MEDIEVAL_DOOR_2.get(), RenderType.cutout());
         */

        // Loop through the entire registry for certain block types
        // that should always use the same specific render type.
        for (RegistryObject<Block> regObject : AQBlocks.BLOCKS.getEntries()) {
            Block block = regObject.get();

            if (block instanceof DoubleCropBlock || block instanceof CoolVinesBlock)
                RenderTypeLookup.setRenderLayer(block, RenderType.cutout());
        }
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();

        colors.register((state, blockDisplayReader, pos, color) -> blockDisplayReader != null && pos != null ? BiomeColors.getAverageFoliageColor(blockDisplayReader, pos) : FoliageColors.getDefaultColor(),
                AQBlocks.VINES_1.get());
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();
        BlockColors blockColors = event.getBlockColors();

        itemColors.register((itemStack, color) -> {
            BlockState blockState = ((BlockItem) itemStack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockState, null, null, color);
        }, AQBlocks.VINES_1.get());
    }
}
