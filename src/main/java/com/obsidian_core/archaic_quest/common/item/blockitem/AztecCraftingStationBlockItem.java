package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.client.render.blockentity.bewlr.BEWLRS;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;

public class AztecCraftingStationBlockItem extends BlockItem {

    public AztecCraftingStationBlockItem(Block block, Settings properties) {
        super(block, properties);
    }

    @Override
    protected boolean canPlace(ItemPlacementContext useContext, BlockState state) {
        PlayerEntity player = useContext.getPlayer();
        ShapeContext collisionContext = player == null ? ShapeContext.absent() : ShapeContext.of(player);
        return (!this.checkStatePlacement() || state.canPlaceAt(useContext.getWorld(), useContext.getBlockPos())) && useContext.getWorld().canPlace(state, useContext.getBlockPos(), collisionContext);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutWorldRenderer getCustomRenderer() {
                return BEWLRS.AZTEC_CRAFTING_STATION.getInstance();
            }
        });
    }
}
