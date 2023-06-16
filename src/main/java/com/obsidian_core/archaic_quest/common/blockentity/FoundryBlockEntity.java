package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class FoundryBlockEntity extends BlockEntity {

    public FoundryBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.FOUNDRY, pos, state);
    }

}
