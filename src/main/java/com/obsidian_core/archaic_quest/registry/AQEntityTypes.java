package com.obsidian_core.archaic_quest.registry;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.living.Tlatlaomi;
import com.obsidian_core.archaic_quest.common.entity.projectile.DartEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AQEntityTypes {
    Map<EntityType<?>, Identifier> ENTITY_TYPES = new LinkedHashMap<>();

    // LIVING
    EntityType<Tlatlaomi> TLATLAOMI = register("tlatlaomi",
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, Tlatlaomi::new)
            .dimensions(EntityDimensions.changing(0.85F, 2.6F))
            .spawnableFarFromPlayer().build(), Tlatlaomi.createTlatlaomiAttributes());

    // PROJECTILE
    EntityType<DartEntity> DART = register("dart", FabricEntityTypeBuilder.<DartEntity>create(SpawnGroup.MISC, DartEntity::new)
            .dimensions(EntityDimensions.changing(0.2F, 0.2F))
            .trackRangeChunks(4)
            .trackedUpdateRate(20)
            .disableSummon().build());

    static <T extends LivingEntity> EntityType<T> register(String name, EntityType<T> type, DefaultAttributeContainer.Builder attributes) {
        ENTITY_TYPES.put(type, ArchaicQuest.id(name));
        FabricDefaultAttributeRegistry.register(type, attributes);
        return type;
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> type) {
        ENTITY_TYPES.put(type, ArchaicQuest.id(name));
        return type;
    }

    static void init() {
        ENTITY_TYPES.keySet().forEach(entityType -> Registry.register(Registry.ENTITY_TYPE, ENTITY_TYPES.get(entityType), entityType));
    }
}
