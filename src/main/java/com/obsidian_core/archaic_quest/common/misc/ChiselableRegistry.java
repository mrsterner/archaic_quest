package com.obsidian_core.archaic_quest.common.misc;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Objects;
import java.util.TreeMap;

/**
 * A simple registry keeping track of which blocks
 * belong to which chisel family (which blocks can be
 * chiseled into other blocks in the same family)
 */
@Mod.EventBusSubscriber(modid = ArchaicQuest.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChiselableRegistry {

    private static final TreeMap<Family, Block[]> CHISEL_MAP = new TreeMap<>();

    private static boolean populated = false;


    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {


        populated = true;
    }

    private static void addChiselable(Family family, Block block) {
        if (populated) {
            ArchaicQuest.LOGGER.error("Attempted to register chiselable block after the registry was populated.");
            return;
        }
        Objects.requireNonNull(block);
        Objects.requireNonNull(family);


    }

    public enum Family {
        AZTEC_STONE,
        AZTEC_GOLD
    }
}
