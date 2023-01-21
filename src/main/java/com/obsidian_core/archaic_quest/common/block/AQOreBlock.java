package com.obsidian_core.archaic_quest.common.block;


import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AQOreBlock extends DropExperienceBlock {

    private final int minXp, maxXp;

    public AQOreBlock(Properties properties) {
        this(-1, 0, properties);
    }

    public AQOreBlock(int minXp, int maxXp, Properties properties) {
        super(properties);
        this.minXp = minXp;
        this.maxXp = maxXp;
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
        if (minXp < 0) return 0;
        return Mth.nextInt(randomSource, minXp, maxXp);
    }
}
