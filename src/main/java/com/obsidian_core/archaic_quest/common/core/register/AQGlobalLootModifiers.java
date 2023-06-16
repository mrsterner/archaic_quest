package com.obsidian_core.archaic_quest.common.core.register;

import com.mojang.serialization.Codec;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.loot_modifier.LootModifierAdd;
import com.obsidian_core.archaic_quest.common.loot_modifier.LootModifierRemove;
import io.github.fabricators_of_create.porting_lib.loot.IGlobalLootModifier;

import java.util.function.Supplier;

public class AQGlobalLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ArchaicQuest.MODID);

    public static final RegistryObject<Codec<LootModifierRemove>> REMOVE_ITEM_MODIFIER = register("remove_item_modifier", LootModifierRemove.CODEC);
    public static final RegistryObject<Codec<LootModifierAdd>> ADD_ITEM_MODIFIER = REGISTRY.register("example_codec", LootModifierAdd.CODEC);


    private static <T extends Codec<? extends IGlobalLootModifier>> RegistryObject<T> register(String name, Supplier<T> codecSupplier) {
        return REGISTRY.register(name, codecSupplier);
    }
}
