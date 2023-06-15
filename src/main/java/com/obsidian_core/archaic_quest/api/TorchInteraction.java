package com.obsidian_core.archaic_quest.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface TorchInteraction {

    /**
     * @param level The world object.
     * @param state the BlockState of the block being clicked.
     * @param pos The position of the block being clicked.
     * @param soulfire True if the torch is lit with soulfire.
     * <p>
     * @return True if this interaction was successful, false if not.
     */
    boolean interact(World level, BlockState state, BlockPos pos, boolean soulfire);


}
