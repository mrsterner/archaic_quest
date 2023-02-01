package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VaseBlockEntity extends BlockEntity {

    public VaseBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.VASE.get(), pos, state);
    }
}
