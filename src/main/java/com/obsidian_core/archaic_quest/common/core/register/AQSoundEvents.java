package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AQSoundEvents {

    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ArchaicQuest.MODID);

    public static final RegistryObject<SoundEvent> DEATH_WHISTLE_SHRIEK = registerSound("death_whistle_shriek");
    public static final RegistryObject<SoundEvent> AZTEC_DOOR_OPENING = registerSound("aztec_door_opening");
    public static final RegistryObject<SoundEvent> AZTEC_DOOR_CLOSING = registerSound("aztec_door_closing");
    public static final RegistryObject<SoundEvent> VASE_BREAK = registerSound("vase_break");



    private static RegistryObject<SoundEvent> registerSound(String name) {
        return REGISTRY.register(name, () -> new SoundEvent(ArchaicQuest.resourceLoc(name)));
    }
}
