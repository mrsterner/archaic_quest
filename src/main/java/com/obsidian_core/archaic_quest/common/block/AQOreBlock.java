package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class AQOreBlock extends OreBlock {

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
    protected int xpOnDrop(Random random) {
        if (minXp < 0) return 0;
        return MathHelper.nextInt(random, minXp, maxXp);
    }
}
