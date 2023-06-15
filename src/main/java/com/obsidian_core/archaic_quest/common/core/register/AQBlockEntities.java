package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.block.*;
import com.obsidian_core.archaic_quest.common.blockentity.*;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.world.world.block.entity.BlockEntity;
import net.minecraft.world.world.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class AQBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TPOSITIVE_YES, ArchaicQuest.MODID);


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

    private static final Supplier<CeramicVaseBlock[]> VASES = () ->
            new CeramicVaseBlock[] {
                    AQBlocks.AZTEC_VASE.get()
    };

    private static final Supplier<AztecPoisonTrapBlock[]> POISON_TRAPS = () ->
            new AztecPoisonTrapBlock[] {
                AQBlocks.AZTEC_POISON_TRAP.get()
    };

    private static final Supplier<SpikeTrapBlock[]> SPIKE_TRAPS = () ->
            new SpikeTrapBlock[] {
                    AQBlocks.AZTEC_ANDESITE_SPIKE_TRAP.get()
    };

    private static final Supplier<AztecDungeonChestBlock[]> DUNGEON_CHESTS = () ->
            new AztecDungeonChestBlock[] {
                    AQBlocks.AZTEC_DUNGEON_CHEST.get()
    };


    public static final RegistryObject<BlockEntityType<VaseBlockEntity>> VASE = register("vase", () -> BlockEntityType.Builder.of(VaseBlockEntity::new, VASES.get()).build(null));
    public static final RegistryObject<BlockEntityType<AztecPoisonTrapBlockEntity>> POISON_TRAP = register("aztec_poison_trap", () -> BlockEntityType.Builder.of(AztecPoisonTrapBlockEntity::new, POISON_TRAPS.get()).build(null));
    public static final RegistryObject<BlockEntityType<SpikeTrapBlockEntity>> SPIKE_TRAP = register("spike_trap", () -> BlockEntityType.Builder.of(SpikeTrapBlockEntity::new, SPIKE_TRAPS.get()).build(null));
    public static final RegistryObject<BlockEntityType<AztecCraftingStationBlockEntity>> AZTEC_CRAFTING_STATION = register("aztec_crafting_station", () -> BlockEntityType.Builder.of(AztecCraftingStationBlockEntity::new, AQBlocks.AZTEC_CRAFTING_STATION.get()).build(null));
    public static final RegistryObject<BlockEntityType<AztecDungeonDoorBlockEntity>> AZTEC_DUNGEON_DOOR = register("aztec_dungeon_door", () -> BlockEntityType.Builder.of(AztecDungeonDoorBlockEntity::new, DUNGEON_DOORS.get()).build(null));
    public static final RegistryObject<BlockEntityType<AztecThroneBlockEntity>> AZTEC_THRONE = register("aztec_throne", () -> BlockEntityType.Builder.of(AztecThroneBlockEntity::new, AZTEC_THRONES.get()).build(null));
    public static final RegistryObject<BlockEntityType<AztecDungeonChestBlockEntity>> AZTEC_DUNGEON_CHEST = register("aztec_dungeon_chest", () -> BlockEntityType.Builder.of(AztecDungeonChestBlockEntity::new, DUNGEON_CHESTS.get()).build(null));
    public static final RegistryObject<BlockEntityType<FoundryBlockEntity>> FOUNDRY = register("foundry", () -> BlockEntityType.Builder.of(FoundryBlockEntity::new, AQBlocks.FOUNDRY.get()).build(null));


    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, Supplier<BlockEntityType<T>> tileEntityType) {
        return REGISTRY.register(name, tileEntityType);
    }
}
