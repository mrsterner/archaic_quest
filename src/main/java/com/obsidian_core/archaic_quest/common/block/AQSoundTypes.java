package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.core.register.AQSoundEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;

public class AQSoundTypes {

    public static final ForgeSoundType CERAMIC_VASE = new ForgeSoundType(1.0F, 1.3F, AQSoundEvents.VASE_BREAK, () -> SoundEvents.STONE_STEP, () -> SoundEvents.STONE_PLACE, () -> SoundEvents.STONE_HIT, () -> SoundEvents.STONE_FALL);
}
