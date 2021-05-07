package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlock extends Block {

    public static final List<Block> SIMPLE_BLOCKS = new ArrayList<>();

    public SimpleBlock(Properties properties) {
        super(properties);
        SIMPLE_BLOCKS.add(this);
    }
}
