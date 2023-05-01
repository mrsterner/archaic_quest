package com.obsidian_core.archaic_quest.api.impl;

import com.obsidian_core.archaic_quest.api.IRegistryHelper;
import com.obsidian_core.archaic_quest.api.TorchInteraction;
import com.obsidian_core.archaic_quest.common.item.AdventurersTorchItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Predicate;

public class RegistryHelper implements IRegistryHelper {

    @Override
    public void registerTorchLightable(Block block, Predicate<BlockState> predicate, boolean soulfire) {
        AdventurersTorchItem.registerTorchLightable(block, predicate, soulfire);
    }

    @Override
    public void registerTorchInteraction(Block block, TorchInteraction torchInteraction) {
        AdventurersTorchItem.registerTorchInteraction(block, torchInteraction);
    }
}
