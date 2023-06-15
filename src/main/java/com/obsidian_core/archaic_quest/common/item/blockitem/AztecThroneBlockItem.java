package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.client.render.blockentity.bewlr.BEWLRS;
import com.obsidian_core.archaic_quest.common.block.data.ThroneType;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import java.util.function.Consumer;

public class AztecThroneBlockItem extends BlockItem {

    private final ThroneType throneType;

    public AztecThroneBlockItem(Block block, ThroneType type) {
        super(block, new Item.Settings().group(AQCreativeTabs.DECORATION));
        this.throneType = type;
    }

    public ThroneType getThroneType() {
        return throneType;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutWorldRenderer getCustomRenderer() {
                return BEWLRS.AZTEC_THRONE.getInstance();
            }
        });
    }
}
