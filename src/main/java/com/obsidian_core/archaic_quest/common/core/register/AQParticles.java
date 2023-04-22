package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AQParticles {

    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ArchaicQuest.MODID);


    public static final RegistryObject<SimpleParticleType> POISON_CLOUD = registerSimple("poison_cloud", true);


    private static RegistryObject<SimpleParticleType> registerSimple(String name, boolean showAtFar) {
        return REGISTRY.register(name, () -> new SimpleParticleType(showAtFar));
    }
}
