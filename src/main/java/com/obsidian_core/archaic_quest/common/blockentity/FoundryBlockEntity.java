package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.world.block.entity.BlockEntity;
import net.minecraft.world.world.block.state.BlockState;

public class FoundryBlockEntity extends BlockEntity {

    public FoundryBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.FOUNDRY.get(), pos, state);
    }

}
