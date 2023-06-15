package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.client.render.blockentity.bewlr.BEWLRS;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

import java.util.function.Consumer;

public class AztecDungeonChestBlockItem extends BlockItem {

    public AztecDungeonChestBlockItem(Block block, Settings properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutWorldRenderer getCustomRenderer() {
                return BEWLRS.AZTEC_DUNGEON_CHEST.getInstance();
            }
        });
    }
}
