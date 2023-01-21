package com.obsidian_core.archaic_quest.common.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

public class AztecCraftingStationBlockItem extends BlockItem {

    public AztecCraftingStationBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext useContext, BlockState state) {
        Player player = useContext.getPlayer();
        CollisionContext collisionContext = player == null ? CollisionContext.empty() : CollisionContext.of(player);
        return (!this.mustSurvive() || state.canSurvive(useContext.getLevel(), useContext.getClickedPos())) && useContext.getLevel().isUnobstructed(state, useContext.getClickedPos(), collisionContext);
    }
}
