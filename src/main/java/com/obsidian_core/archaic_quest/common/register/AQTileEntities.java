package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.tile.AztecCraftingStationTileEntity;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class AQTileEntities {

    public static final DeferredRegister<TileEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ArchaicQuest.MODID);


    private static final Supplier<AztecDungeonDoorBlock[]> DUNGEON_DOORS = () ->
            new AztecDungeonDoorBlock[] {
                    AQBlocks.AZTEC_DUNGEON_DOOR_0.get(),
                    AQBlocks.AZTEC_DUNGEON_DOOR_1.get(),
                    AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_0.get(),
                    AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_1.get()
            };


    public static final RegistryObject<TileEntityType<AztecCraftingStationTileEntity>> AZTEC_CRAFTING_STATION = register("aztec_crafting_station", () -> TileEntityType.Builder.of(AztecCraftingStationTileEntity::new, AQBlocks.AZTEC_CRAFTING_STATION.get()).build(null));
    public static final RegistryObject<TileEntityType<AztecDungeonDoorTileEntity>> AZTEC_DUNGEON_DOOR = register("aztec_dungeon_door", () -> TileEntityType.Builder.of(AztecDungeonDoorTileEntity::new, DUNGEON_DOORS.get()).build(null));


    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<TileEntityType<T>> tileEntityType) {
        return REGISTRY.register(name, tileEntityType);
    }
}
