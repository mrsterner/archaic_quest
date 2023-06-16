package com.obsidian_core.archaic_quest.registry;

import com.obsidian_core.archaic_quest.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.blockentity.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AQBlockEntityTypes {
    Map<BlockEntityType<?>, Identifier> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();

    BlockEntityType<VaseBlockEntity> VASE = register("vase",
            FabricBlockEntityTypeBuilder.create(VaseBlockEntity::new,
                    AQObjects.AZTEC_VASE
            ).build());

    BlockEntityType<AztecPoisonTrapBlockEntity> POISON_TRAP = register("aztec_poison_trap",
            FabricBlockEntityTypeBuilder.create(AztecPoisonTrapBlockEntity::new,
                    AQObjects.AZTEC_POISON_TRAP
            ).build());

    BlockEntityType<SpikeTrapBlockEntity> SPIKE_TRAP = register("spike_trap",
            FabricBlockEntityTypeBuilder.create(SpikeTrapBlockEntity::new,
                    AQObjects.AZTEC_ANDESITE_SPIKE_TRAP
            ).build());

    BlockEntityType<AztecCraftingStationBlockEntity> AZTEC_CRAFTING_STATION = register("aztec_crafting_station",
            FabricBlockEntityTypeBuilder.create(AztecCraftingStationBlockEntity::new,
                    AQObjects.AZTEC_CRAFTING_STATION
            ).build());

    BlockEntityType<AztecDungeonDoorBlockEntity> AZTEC_DUNGEON_DOOR = register("aztec_dungeon_door",
            FabricBlockEntityTypeBuilder.create(AztecDungeonDoorBlockEntity::new,
                    AQObjects.AZTEC_DUNGEON_DOOR_0,
                    AQObjects.AZTEC_DUNGEON_DOOR_1,
                    AQObjects.AZTEC_DUNGEON_DOOR_FRAME_0,
                    AQObjects.AZTEC_DUNGEON_DOOR_FRAME_1
            ).build());

    BlockEntityType<AztecThroneBlockEntity> AZTEC_THRONE = register("aztec_throne",
            FabricBlockEntityTypeBuilder.create(AztecThroneBlockEntity::new,
                    AQObjects.AZTEC_THRONE,
                    AQObjects.MOSSY_AZTEC_THRONE
            ).build());

    BlockEntityType<AztecDungeonChestBlockEntity> AZTEC_DUNGEON_CHEST = register("aztec_dungeon_chest",
            FabricBlockEntityTypeBuilder.create(AztecDungeonChestBlockEntity::new,
                    AQObjects.AZTEC_DUNGEON_CHEST
            ).build());

    BlockEntityType<FoundryBlockEntity> FOUNDRY = register("foundry",
            FabricBlockEntityTypeBuilder.create(FoundryBlockEntity::new,
                    AQObjects.FOUNDRY
            ).build());

    static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        BLOCK_ENTITY_TYPES.put(type, ArchaicQuest.id(name));
        return type;
    }

    static void init() {
        BLOCK_ENTITY_TYPES.keySet().forEach(blockEntityType -> Registry.register(Registry.BLOCK_ENTITY_TYPE, BLOCK_ENTITY_TYPES.get(blockEntityType), blockEntityType));
    }
}
