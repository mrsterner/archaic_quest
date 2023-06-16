package com.obsidian_core.archaic_quest;

import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import com.obsidian_core.archaic_quest.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ArchaicQuest implements ModInitializer {
    public static final String MODID = "archaic_quest";

    public static final ItemGroup ITEMS = FabricItemGroupBuilder.build(ArchaicQuest.id("items"), () -> new ItemStack(AQObjects.CRYSTAL_SKULL));
    public static final ItemGroup DECORATION = FabricItemGroupBuilder.build(ArchaicQuest.id("decoration"), () -> new ItemStack(AQObjects.VINES_1));
    public static final ItemGroup TOOLS = FabricItemGroupBuilder.build(ArchaicQuest.id("tools"), () -> new ItemStack(AQObjects.HAMMER_AND_CHISEL));
    public static final ItemGroup WEAPONS = FabricItemGroupBuilder.build(ArchaicQuest.id("weapons"), () -> new ItemStack(AQObjects.BONE_CLUB));
    public static final ItemGroup ARMOR = FabricItemGroupBuilder.build(ArchaicQuest.id("armor"), () -> new ItemStack(Items.IRON_CHESTPLATE));
    public static final ItemGroup FOOD = FabricItemGroupBuilder.build(ArchaicQuest.id("food"), () -> new ItemStack(AQObjects.CORN));

    public static Identifier id(String name){
        return new Identifier(MODID, name);
    }

    @Override
    public void onInitialize() {
        AQObjects.init();
        AQBlockEntityTypes.init();
        AQEntityTypes.init();
        AQParticleTypes.init();
        AQScreenHandlers.init();


        AQBiomes.init();
        AQBiomeModifiers.init();
        AQGlobalLootModifiers.init();
        AQConfiguredFeatures.init();
    }
}
