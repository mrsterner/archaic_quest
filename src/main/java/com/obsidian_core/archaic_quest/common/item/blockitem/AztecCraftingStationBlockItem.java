package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.client.render.blockentity.bewlr.BEWLRS;
import net.minecraft.client.renderer.BlockEntityWithoutWorldRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class AztecCraftingStationBlockItem extends BlockItem {

    public AztecCraftingStationBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext useContext, BlockState state) {
        PlayerEntity player = useContext.getPlayer();
        CollisionContext collisionContext = player == null ? CollisionContext.empty() : CollisionContext.of(player);
        return (!this.mustSurvive() || state.canSurvive(useContext.getWorld(), useContext.getClickedPos())) && useContext.getWorld().isUnobstructed(state, useContext.getClickedPos(), collisionContext);
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
