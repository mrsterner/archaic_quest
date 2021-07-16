package com.obsidian_core.archaic_quest.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.shapes.ISelectionContext;

public class AztecCraftingStationBlockItem extends BlockItem {

    public AztecCraftingStationBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean canPlace(BlockItemUseContext useContext, BlockState state) {
        PlayerEntity player = useContext.getPlayer();
        ISelectionContext selectionContext = player == null ? ISelectionContext.empty() : ISelectionContext.of(player);
        return (!this.mustSurvive() || state.canSurvive(useContext.getLevel(), useContext.getClickedPos())) && useContext.getLevel().isUnobstructed(state, useContext.getClickedPos(), selectionContext);
    }
}
