package com.obsidian_core.archaic_quest.common.misc;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.DartEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class AQDamageSources {

    public static final DamageSource SPEAR_TRAP = create("spear_trap").bypassArmor().setIsFall();


    public static DamageSource dart(DartEntity dartEntity, @Nullable Entity entity) {
        return new IndirectEntityDamageSource("archaic_quest.dart", dartEntity, entity).setProjectile();
    }

    private static DamageSource create(String name) {
        return new DamageSource(ArchaicQuest.resourceLoc(name).toString());
    }

    public static void init() {}

    private AQDamageSources() {}
}
