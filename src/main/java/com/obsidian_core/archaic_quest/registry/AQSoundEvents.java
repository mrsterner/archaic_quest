package com.obsidian_core.archaic_quest.registry;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AQSoundEvents {

    Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap<>();

    SoundEvent DEATH_WHISTLE_SHRIEK = register("death_whistle_shriek");
    SoundEvent AZTEC_DOOR_OPENING = register("aztec_door_opening");
    SoundEvent AZTEC_DOOR_CLOSING = register("aztec_door_closing");
    SoundEvent VASE_BREAK = register("vase_break");
    SoundEvent POISON_TRAP_ACTIVATE = register("poison_trap_activate");


    BlockSoundGroup VASE_BREAK_GROUP = new BlockSoundGroup(
            1.0F,
            1.3F,
            AQSoundEvents.VASE_BREAK,
            SoundEvents.BLOCK_STONE_STEP,
            SoundEvents.BLOCK_STONE_PLACE,
            SoundEvents.BLOCK_STONE_HIT,
            SoundEvents.BLOCK_STONE_FALL
    );


    static SoundEvent register(String name) {
        Identifier id = ArchaicQuest.id(name);
        SoundEvent soundEvent = new SoundEvent(id);
        SOUND_EVENTS.put(soundEvent, id);
        return soundEvent;
    }

    static void init() {
        SOUND_EVENTS.keySet().forEach(soundEvent -> Registry.register(Registry.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent));
    }

}
