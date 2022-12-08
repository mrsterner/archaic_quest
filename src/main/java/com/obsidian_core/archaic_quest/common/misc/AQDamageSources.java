package com.obsidian_core.archaic_quest.common.misc;

import com.obsidian_core.archaic_quest.common.entity.DartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

import javax.annotation.Nullable;

public class AQDamageSources {

    public static DamageSource dart(DartEntity dartEntity, @Nullable Entity entity) {
        return new IndirectEntityDamageSource("arrow", dartEntity, entity).setProjectile();
    }
}
