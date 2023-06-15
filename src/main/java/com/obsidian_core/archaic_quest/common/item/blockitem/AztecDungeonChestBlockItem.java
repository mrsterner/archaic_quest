package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.client.render.blockentity.bewlr.BEWLRS;
import net.minecraft.client.renderer.BlockEntityWithoutWorldRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class AztecDungeonChestBlockItem extends BlockItem {

    public AztecDungeonChestBlockItem(Block block, Properties properties) {
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
