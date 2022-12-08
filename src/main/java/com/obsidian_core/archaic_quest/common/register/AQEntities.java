package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.DartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class AQEntities {

    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, ArchaicQuest.MODID);


    public static final RegistryObject<EntityType<DartEntity>> DART = register("dart", () -> EntityType.Builder.of(DartEntity::new, EntityClassification.MISC)
            .sized(0.2F, 0.2F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .noSummon());


    private static <T extends Entity>RegistryObject<EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builderSupplier) {
        return REGISTRY.register(name, () -> builderSupplier.get().build(name));
    }
}
