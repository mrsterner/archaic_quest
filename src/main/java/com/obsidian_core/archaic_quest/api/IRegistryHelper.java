package com.obsidian_core.archaic_quest.api;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import java.util.function.Predicate;

public interface IRegistryHelper {

    /**
     * Registers a block that can light the Adventurer's Torch,
     * BlockState sensitive.<br>
     * <br>
     * @param block The block that is able to light the torch.
     * @param predicate The predicate to test if the block can light the torch.
     * @param soulfire If true the torch will be lit with soulfire rather than normal fire.
     */
    void registerTorchLightable(Block block, Predicate<BlockState> predicate, boolean soulfire);

    /**
     * Registers a block that can be interacted with using a lit Adventurer's Torch.
     * <br>
     * @param block The block that is able to light the torch.
     * @param torchInteraction The logic to use when the block is clicked with a lit adventurer's torch.
     */
    void registerTorchInteraction(Block block, TorchInteraction torchInteraction);
}
