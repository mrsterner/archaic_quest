package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.block.CoolVinesBlock;
import com.obsidian_core.archaic_quest.common.block.DoubleCropBlock;
import com.obsidian_core.archaic_quest.common.block.SpearTrapBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static com.obsidian_core.archaic_quest.common.register.AQBlocks.*;


public class AQBlockStateProvider extends AbstractBlockStateProvider {

    public AQBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {
        SIMPLE_BLOCKS.forEach((block) -> simpleBlockAndItem(block.get()));
        VERT_SLAB_VARIANTS.forEach((block, vertSlab) -> simpleVerticalSlab(vertSlab.get(), block.get()));
        SLAB_VARIANTS.forEach((block, slab) -> slab(slab.get(), block.get()));

        STAIRS_VARIANTS.forEach((block, stairs) -> {
            stairsBlock(stairs.get(), blockTexture(block.get()));
            ModelFile model = models().withExistingParent(name(stairs.get()), mcLoc("block/stairs"));

            simpleBlockItem(stairs.get(), model);
        });

        for (RegistryObject<Block> regObject : REGISTRY.getEntries()) {
            Block block = regObject.get();

            if (block instanceof DoubleCropBlock) {
                doubleCrop((DoubleCropBlock) block);
            }
            else if (block instanceof CoolVinesBlock) {
                vine((CoolVinesBlock) block);
            }
            else if (block instanceof AztecDungeonDoorBlock) {
                simpleBlock(block, models().withExistingParent(name(block), ArchaicQuest.resourceLoc("block/aztec_dungeon_door")));
            }
            else if (block instanceof SpearTrapBlock) {
                spearTrap((SpearTrapBlock) block);
            }
        }
    }
}
