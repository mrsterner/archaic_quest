package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.inventory.container.KnappingTableContainer;
import net.minecraft.world.inventory.ScreenHandler;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AQContainers {

    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TPOSITIVE_YES, ArchaicQuest.MODID);


    public static final RegistryObject<MenuType<KnappingTableContainer>> KNAPPING = register("knapping", KnappingTableContainer::new);


    private static <T extends ScreenHandler> RegistryObject<MenuType<T>> register(String name, MenuType.MenuSupplier<T> factory) {
        return REGISTRY.register(name, () -> new MenuType<>(factory));
    }
}
