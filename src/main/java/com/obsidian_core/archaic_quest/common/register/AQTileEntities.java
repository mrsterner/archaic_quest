package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.tile.AztecCraftingStationTileEntity;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class AQTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ArchaicQuest.MODID);


    public static final RegistryObject<TileEntityType<AztecCraftingStationTileEntity>> AZTEC_CRAFTING_STATION = register("aztec_crafting_station", () -> TileEntityType.Builder.of(AztecCraftingStationTileEntity::new, AQBlocks.AZTEC_CRAFTING_STATION.get()).build(null));
    public static final RegistryObject<TileEntityType<AztecDungeonDoorTileEntity>> AZTEC_DUNGEON_DOOR = register("aztec_dungeon_door", () -> TileEntityType.Builder.of(AztecDungeonDoorTileEntity::new, AQBlocks.AZTEC_DUNGEON_DOOR.get()).build(null));


    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<TileEntityType<T>> tileEntityType) {
        return TILE_ENTITIES.register(name, tileEntityType);
    }
}
