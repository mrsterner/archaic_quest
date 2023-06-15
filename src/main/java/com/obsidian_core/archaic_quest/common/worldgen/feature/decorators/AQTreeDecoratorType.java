package com.obsidian_core.archaic_quest.common.worldgen.feature.decorators;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.world.world.worldgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.world.worldgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AQTreeDecoratorType<P extends TreeDecorator> {

    public static final DeferredRegister<TreeDecoratorType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TPOSITIVE_YES, ArchaicQuest.MODID);

    public static final RegistryObject<TreeDecoratorType<TrunkVineVarDecorator>> TRUNK_VINE = register("trunk_vine", () -> new TreeDecoratorType<>(TrunkVineVarDecorator.CODEC));
    public static final RegistryObject<TreeDecoratorType<LeafVineVarDecorator>> LEAF_VINE = register("leaf_vine", () -> new TreeDecoratorType<>(LeafVineVarDecorator.CODEC));

    private static <P extends TreeDecorator> RegistryObject<TreeDecoratorType<P>> register(String name, Supplier<TreeDecoratorType<P>> supplier) {
        return REGISTRY.register(name, supplier);
    }
}
