package com.obsidian_core.archaic_quest.datagen.loot_table;

import com.obsidian_core.archaic_quest.common.block.VerticalSlabBlock;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.LootTable;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class AQBlockLootTableProvider extends BlockLootTables {

    private final Set<Block> knownBlocks = new HashSet<>();

    @Override
    protected void add(Block block, LootTable.Builder table) {
        super.add(block, table);
        this.knownBlocks.add(block);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.knownBlocks;
    }

    @Override
    public void addTables() {
        this.dropSelf(AQBlocks.ANDESITE_BRICKS.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_0.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_1.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_2.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_2_CRACKED.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_3.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_4.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_5.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_6.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_7.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_8.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_9.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_10.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_11.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_12.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_13.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_14.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_15.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_16.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_17.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_18.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_19.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_20.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_21.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_22.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_23.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_24.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_25.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_26.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_27.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_28.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_29.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_30.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_31.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_32.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_33.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_34.get());

        this.dropSelf(AQBlocks.ANDESITE_AZTEC_TRAP_0.get());
        this.dropSelf(AQBlocks.ANDESITE_AZTEC_TRAP_1.get());

        this.dropSelf(AQBlocks.STONE_AZTEC_BRICKS_0.get());

        this.add(AQBlocks.MEDIEVAL_DOOR_0.get(), BlockLootTables::createDoorTable);
        this.add(AQBlocks.MEDIEVAL_DOOR_1.get(), BlockLootTables::createDoorTable);
        this.add(AQBlocks.MEDIEVAL_DOOR_2.get(), BlockLootTables::createDoorTable);
        this.add(AQBlocks.DUNGEON_DOOR_BARS.get(), BlockLootTables::createDoorTable);

        for (RegistryObject<Block> regObj : AQBlocks.BLOCKS.getEntries()) {
            Block block = regObj.get();

            if (block instanceof SlabBlock || block instanceof StairsBlock || block instanceof VerticalSlabBlock) {
                dropSelf(block);
            }
        }
    }
}
