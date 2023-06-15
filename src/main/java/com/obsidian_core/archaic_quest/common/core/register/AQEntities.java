package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.living.Tlatlaomi;
import com.obsidian_core.archaic_quest.common.entity.projectile.DartEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AQEntities {

    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TPOSITIVE_YES, ArchaicQuest.MODID);


    // LIVING
    public static final RegistryObject<EntityType<Tlatlaomi>> TLATLAOMI = registerWithEgg("tlatlaomi", 0xBEBC95, 0x4B3324, () -> EntityType.Builder.of(Tlatlaomi::new, MobCategory.MONSTER)
                    .sized(0.85F, 2.6F)
                    .canSpawnFarFromPlayer());



    // PROJECTILE
    public static final RegistryObject<EntityType<DartEntity>> DART = register("dart", () -> EntityType.Builder.<DartEntity>of(DartEntity::new, MobCategory.MISC)
            .sized(0.2F, 0.2F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .noSummon());




    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builderSupplier) {
        return REGISTRY.register(name, () -> builderSupplier.get().build(name));
    }

    private static <T extends Mob> RegistryObject<EntityType<T>> registerWithEgg(String name, int primaryColor, int secondaryColor, Supplier<EntityType.Builder<T>> builderSupplier) {
        RegistryObject<EntityType<T>> regObj = REGISTRY.register(name, () -> builderSupplier.get().build(name));
        AQItems.registerSpawnEgg(regObj, primaryColor, secondaryColor);
        return regObj;
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(TLATLAOMI.get(), Tlatlaomi.createTlatlaomiAttributes().build());
    }
}
