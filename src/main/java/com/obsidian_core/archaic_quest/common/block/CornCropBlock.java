package com.obsidian_core.archaic_quest.common.block;

import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class CornCropBlock extends DoubleCropBlock {

    protected static final Pair<VoxelShape[], VoxelShape[]> SHAPES = Pair.of(
            new VoxelShape[] {
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            },
            new VoxelShape[] {
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            }
    );

    public CornCropBlock() {
        super(DEFAULT_PROPS, AQItems.CORN::get);
    }

    @Nonnull
    @Override
    public Pair<VoxelShape[], VoxelShape[]> getShapes() {
        return SHAPES;
    }

    @Override
    public int getDoublingAge() {
        return 2;
    }

    @Override
    public int maxAge() {
        return 4;
    }

    @Override
    public IntegerProperty ageProperty() {
        return AGE_4;
    }

    @Override
    public int getOnHarvestAge() {
        return 3;
    }
}
