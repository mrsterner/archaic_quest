package com.obsidian_core.archaic_quest.common.misc;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.projectile.DartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import org.jetbrains.annotations.Nullable;

public class AQDamageSources {

    public static final DamageSource SPEAR_TRAP = create("spear_trap").setBypassesArmor().setBypassesProtection().setUnblockable().setFromFalling();
    public static final DamageSource SPIKE_TRAP = create("spike_trap").setBypassesArmor().setBypassesProtection();

    public static DamageSource dart(DartEntity dartEntity, @Nullable Entity entity) {
        return new ProjectileDamageSource("archaic_quest.dart", dartEntity, entity).setProjectile();
    }

    private static DamageSource create(String name) {
        return new DamageSource(ArchaicQuest.id(name).toString());
    }

    public static void init() {}

    private AQDamageSources() {}
}
