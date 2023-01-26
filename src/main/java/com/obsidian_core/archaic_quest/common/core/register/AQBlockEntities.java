package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.block.AztecThroneBlock;
import com.obsidian_core.archaic_quest.common.blockentity.AztecThroneBlockEntity;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.blockentity.AztecCraftingStationBlockEntity;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import com.obsidian_core.archaic_quest.common.blockentity.FoundryBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class AQBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ArchaicQuest.MODID);


    private static final Supplier<AztecDungeonDoorBlock[]> DUNGEON_DOORS = () ->
            new AztecDungeonDoorBlock[] {
                    AQBlocks.AZTEC_DUNGEON_DOOR_0.get(),
                    AQBlocks.AZTEC_DUNGEON_DOOR_1.get(),
                    AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_0.get(),
                    AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_1.get()
            };

    private static final Supplier<AztecThroneBlock[]> AZTEC_THRONES = () ->
            new AztecThroneBlock[] {
                    AQBlocks.AZTEC_THRONE.get(),
                    AQBlocks.MOSSY_AZTEC_THRONE.get()
            };


    public static final RegistryObject<BlockEntityType<AztecCraftingStationBlockEntity>> AZTEC_CRAFTING_STATION = register("aztec_crafting_station", () -> BlockEntityType.Builder.of(AztecCraftingStationBlockEntity::new, AQBlocks.AZTEC_CRAFTING_STATION.get()).build(null));
    public static final RegistryObject<BlockEntityType<AztecDungeonDoorBlockEntity>> AZTEC_DUNGEON_DOOR = register("aztec_dungeon_door", () -> BlockEntityType.Builder.of(AztecDungeonDoorBlockEntity::new, DUNGEON_DOORS.get()).build(null));
    public static final RegistryObject<BlockEntityType<AztecThroneBlockEntity>> AZTEC_THRONE = register("aztec_throne", () -> BlockEntityType.Builder.of(AztecThroneBlockEntity::new, AZTEC_THRONES.get()).build(null));
    public static final RegistryObject<BlockEntityType<FoundryBlockEntity>> FOUNDRY = register("foundry", () -> BlockEntityType.Builder.of(FoundryBlockEntity::new, AQBlocks.FOUNDRY.get()).build(null));


    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, Supplier<BlockEntityType<T>> tileEntityType) {
        return REGISTRY.register(name, tileEntityType);
    }
}
