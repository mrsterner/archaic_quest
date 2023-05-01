package com.obsidian_core.archaic_quest.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface TorchInteraction {

    /**
     * @param level The world object.
     * @param state the BlockState of the block being clicked.
     * @param pos The position of the block being clicked.
     * <p>
     * @return True if this interaction was successful, false if not.
     */
    boolean interact(Level level, BlockState state, BlockPos pos);


}
