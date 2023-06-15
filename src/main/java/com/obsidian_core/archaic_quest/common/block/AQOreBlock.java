package com.obsidian_core.archaic_quest.common.block;


import io.github.fabricators_of_create.porting_lib.block.CustomExpBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class AQOreBlock extends OreBlock implements CustomExpBlock {

    private final int minXp, maxXp;

    public AQOreBlock(Settings properties) {
        this(-1, 0, properties);
    }

    public AQOreBlock(int minXp, int maxXp, Settings properties) {
        super(properties);
        this.minXp = minXp;
        this.maxXp = maxXp;
    }

    @Override
    public int getExpDrop(BlockState state, WorldView level, Random randomSource, BlockPos pos, int fortuneWorld, int silkTouchWorld) {
        if (minXp < 0) {
            return 0;
        }
        return MathHelper.nextInt(randomSource, minXp, maxXp);
    }
}
