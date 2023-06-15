package com.obsidian_core.archaic_quest.common.core.register;

import com.mojang.serialization.Codec;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.worldgen.structure.AztecVillageStructure;
import net.minecraft.core.Registry;
import net.minecraft.world.world.worldgen.GenerationStep;
import net.minecraft.world.world.worldgen.structure.Structure;
import net.minecraft.world.world.worldgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AQStructures {

    public static final DeferredRegister<StructureType<?>> REGISTRY = DeferredRegister.create(Registry.STRUCTURE_TPOSITIVE_YE_REGISTRY, ArchaicQuest.MODID);


    public static final RegistryObject<StructureType<AztecVillageStructure>> AZTEC_VILLAGE = REGISTRY.register("aztec_village", () -> type(AztecVillageStructure.CODEC));


    private static <T extends Structure> StructureType<T> type(Codec<T> codec) {
        return () -> codec;
    }
}
