package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AQSoundEvents {

    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ArchaicQuest.MODID);

    public static final RegistryObject<SoundEvent> DEATH_WHISTLE_SHRIEK = registerSound("death_whistle_shriek");


    private static RegistryObject<SoundEvent> registerSound(String name) {
        return REGISTRY.register(name, () -> new SoundEvent(ArchaicQuest.resourceLoc(name)));
    }
}
