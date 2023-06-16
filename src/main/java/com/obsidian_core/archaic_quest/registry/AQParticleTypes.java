package com.obsidian_core.archaic_quest.registry;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AQParticleTypes {

    Map<ParticleType<?>, Identifier> PARTICLE_TYPES = new LinkedHashMap<>();

    ParticleType<DefaultParticleType> POISON_CLOUD = register("poison_cloud", FabricParticleTypes.simple());

    static <T extends ParticleEffect> ParticleType<T> register(String name, ParticleType<T> type) {
        PARTICLE_TYPES.put(type, ArchaicQuest.id(name));
        return type;
    }

    static void init() {
        PARTICLE_TYPES.keySet().forEach(particleType -> Registry.register(Registry.PARTICLE_TYPE, PARTICLE_TYPES.get(particleType), particleType));
    }
}
