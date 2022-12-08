package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.loot_modifier.LootModifierAdd;
import com.obsidian_core.archaic_quest.common.loot_modifier.LootModifierRemove;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class AQGlobalLootModifiers {

    public static final DeferredRegister<GlobalLootModifierSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, ArchaicQuest.MODID);

    public static final RegistryObject<LootModifierAdd.LootModifierAddSerializer> ADD_ITEM_MODIFIER = register("add_item_modifier", LootModifierAdd.LootModifierAddSerializer::new);
    public static final RegistryObject<LootModifierRemove.LootModifierRemoveSerializer> REMOVE_ITEM_MODIFIER = register("remove_item_modifier", LootModifierRemove.LootModifierRemoveSerializer::new);


    private static <T extends GlobalLootModifierSerializer<?>> RegistryObject<T> register(String name, Supplier<T> serializerSupplier) {
        return REGISTRY.register(name, serializerSupplier);
    }
}
