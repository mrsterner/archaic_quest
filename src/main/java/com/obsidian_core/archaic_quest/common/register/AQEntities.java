package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.DartEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AQEntities {

    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ArchaicQuest.MODID);


    public static final RegistryObject<EntityType<DartEntity>> DART = register("dart", () -> EntityType.Builder.<DartEntity>of(DartEntity::new, MobCategory.MISC)
            .sized(0.2F, 0.2F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .noSummon());


    private static <T extends Entity>RegistryObject<EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builderSupplier) {
        return REGISTRY.register(name, () -> builderSupplier.get().build(name));
    }
}
