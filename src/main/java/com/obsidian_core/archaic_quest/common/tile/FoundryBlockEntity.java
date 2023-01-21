package com.obsidian_core.archaic_quest.common.tile;

import com.obsidian_core.archaic_quest.common.register.AQBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FoundryBlockEntity extends BlockEntity {

    public FoundryBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.FOUNDRY.get(), pos, state);
    }

}
