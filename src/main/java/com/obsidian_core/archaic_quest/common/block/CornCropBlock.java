package com.obsidian_core.archaic_quest.common.block;

import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.registry.AQObjects;
import net.minecraft.block.Block;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CornCropBlock extends DoubleCropBlock {

    protected static final Pair<VoxelShape[], VoxelShape[]> SHAPES = Pair.of(
            new VoxelShape[] {
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            },
            new VoxelShape[] {
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D),
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            }
    );

    public CornCropBlock() {
        super(DEFAULT_PROPS, AQObjects.CORN);
    }

    @Override
    public @NotNull Pair<VoxelShape[], VoxelShape[]> getShapes() {
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
    public IntProperty ageProperty() {
        return AGE_4;
    }

    @Override
    public int getOnHarvestAge() {
        return 3;
    }
}
