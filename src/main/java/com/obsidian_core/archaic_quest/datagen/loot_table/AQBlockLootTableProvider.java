package com.obsidian_core.archaic_quest.datagen.loot_table;

import com.obsidian_core.archaic_quest.common.block.DoubleCropBlock;
import com.obsidian_core.archaic_quest.common.block.VerticalSlabBlock;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.register.AQItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Set;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
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
        dropSelf(AQBlocks.TIN_ORE.get());
        dropSelf(AQBlocks.SILVER_ORE.get());
        createOreDrop(AQBlocks.DIORITE_JADE_ORE.get(), AQItems.JADE.get());
        createOreDrop(AQBlocks.GRANITE_QUARTZ_ORE.get(), Items.QUARTZ);

        dropSelf(AQBlocks.ANDESITE_BRICKS.get());
        dropSelf(AQBlocks.MOSSY_ANDESITE_BRICKS.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_0.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_1.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_2.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_2_CRACKED.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_3.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_4.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_5.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_6.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_7.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_8.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_9.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_10.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_11.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_12.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_13.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_14.get());
        dropSelf(AQBlocks.MOSSY_ANDESITE_AZTEC_BRICKS_14.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_15.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_16.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_16_VERT_SLAB.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_17.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_18.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_19.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_20.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_21.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_22.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_23.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_24.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_24_VERT_SLAB.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_25.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_26.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_26_VERT_SLAB.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_27.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_27_VERT_SLAB.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_28.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_29.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_29_VERT_SLAB.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_30.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_31.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_32.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_32_VERT_SLAB.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_33.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_33_VERT_SLAB.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_34.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_34_VERT_SLAB.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_35.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_36.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_37.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_38.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_39.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_40.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_41.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_42.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_43.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_44.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_45.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_46.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_47.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_48.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_49.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_50.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_51.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_52.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_53.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_54.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_55.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_56.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_57.get());
        dropSelf(AQBlocks.MOSSY_ANDESITE_AZTEC_BRICKS_57.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_58.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_59.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_60.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_61.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_62.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_63.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_64.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_65.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_66.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_67.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_68.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_69.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_70.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_71.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_72.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_73.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_74.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_75.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_76.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_77.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_78.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_79.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_80.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_81.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_82.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_83.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_84.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_85.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_86.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_BRICKS_87.get());

        dropSelf(AQBlocks.ANDESITE_AZTEC_TRAP_0.get());
        dropSelf(AQBlocks.ANDESITE_AZTEC_TRAP_1.get());

        dropSelf(AQBlocks.STONE_AZTEC_BRICKS_0.get());

        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_0.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_1.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_2.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_3.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_4.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_5.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_6.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_7.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_8.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_9.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_10.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_11.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_12.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_13.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_14.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_15.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_16.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_17.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_18.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_19.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_20.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_21.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_22.get());
        dropSelf(AQBlocks.GOLD_AZTEC_BRICKS_23.get());

        dropSelf(AQBlocks.GOLD_AZTEC_TRAP_0.get());
        dropSelf(AQBlocks.GOLD_AZTEC_TRAP_1.get());

        /*
        add(AQBlocks.MEDIEVAL_DOOR_0.get(), BlockLootTables::createDoorTable);
        add(AQBlocks.MEDIEVAL_DOOR_1.get(), BlockLootTables::createDoorTable);
        add(AQBlocks.MEDIEVAL_DOOR_2.get(), BlockLootTables::createDoorTable);
        add(AQBlocks.DUNGEON_DOOR_BARS.get(), BlockLootTables::createDoorTable);
        */

        dropSelf(AQBlocks.KNAPPING_TABLE.get());


        for (RegistryObject<Block> regObj : AQBlocks.BLOCKS.getEntries()) {
            Block block = regObj.get();

            if (block instanceof SlabBlock || block instanceof StairsBlock || block instanceof VerticalSlabBlock) {
                dropSelf(block);
            }
            else if (block instanceof DoubleCropBlock) {
                DoubleCropBlock crop = (DoubleCropBlock) block;
                doubleCrop(crop);
            }
        }
    }

    private void doubleCrop(DoubleCropBlock crop) {
        ILootCondition.IBuilder harvestCondition = BlockStateProperty.hasBlockStateProperties(crop)
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(crop.getAgeProperty(), crop.getMaxAge())
                        .hasProperty(DoubleCropBlock.IS_TOP, false));

        ILootCondition.IBuilder retrieveSeedCondition = BlockStateProperty.hasBlockStateProperties(crop)
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(DoubleCropBlock.IS_TOP, false));

        LootTable.Builder builder = applyExplosionDecay(crop, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(ItemLootEntry.lootTableItem(crop.getBaseSeedId())
                                .when(retrieveSeedCondition)))
                .withPool(LootPool.lootPool()
                        .when(harvestCondition)
                        .add(ItemLootEntry.lootTableItem(crop.getBaseSeedId())
                                .apply(ApplyBonus.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));

        add(crop, builder);
    }

    private void rangedOreDrop(Block block, Item item, float min, float max) {
        this.add(block, (b) -> {
            return createSilkTouchDispatchTable(b, applyExplosionDecay(b, ItemLootEntry.lootTableItem(item)
                    .apply(SetCount.setCount(RandomValueRange.between(min, max)))
                    .apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
        });
    }
}
