package com.obsidian_core.archaic_quest.datagen.model;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class AQItemModelProvider extends ItemModelProvider {

    public AQItemModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, ArchaicQuest.MODID, fileHelper);
    }

    @Override
    protected void registerModels() {
        AQItems.SIMPLE_ITEMS.forEach((item) -> simpleItem(item.get()));
        AQItems.SPAWN_EGGS.forEach((egg) -> spawnEgg(egg.get()));
    }

    private <T extends Item> void simpleItem(T item) {
        Identifier regName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item));
        singleTexture(regName.getPath(), mcLoc("item/generated"), "layer0", ArchaicQuest.resourceLoc("item/" + regName.getPath()));
    }

    private void spawnEgg(ForgeSpawnEggItem spawnEgg) {
        Identifier regName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(spawnEgg));
        withExistingParent(regName.getPath(), mcLoc("item/template_spawn_egg"));
    }
}
