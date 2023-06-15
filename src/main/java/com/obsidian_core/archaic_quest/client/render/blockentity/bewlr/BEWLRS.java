package com.obsidian_core.archaic_quest.client.render.blockentity.bewlr;


import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class BEWLRS {

    public static final List<Holder> BEWLR_LIST = new ArrayList<>();
    public static final EntityModelLoader MODEL_SET = new EntityModelLoader();

    public static final Holder AZTEC_CRAFTING_STATION = new Holder(AztecCraftingStationBEWLR::new);
    public static final Holder AZTEC_THRONE = new Holder(AztecThroneBEWLR::new);
    public static final Holder AZTEC_DUNGEON_CHEST = new Holder(AztecDungeonChestBEWLR::new);


    public static class Holder {

        private final BiFunction<BlockEntityRenderDispatcher, EntityModelLoader, BuiltinModelItemRenderer> factory;
        private BuiltinModelItemRenderer instance;

        public Holder(BiFunction<BlockEntityRenderDispatcher, EntityModelLoader, BuiltinModelItemRenderer> factory) {
            this.factory = factory;
            BEWLR_LIST.add(this);
        }

        public void populate(BlockEntityRenderDispatcher renderDispatcher) {
            instance = factory.apply(renderDispatcher, MODEL_SET);
        }

        @Nullable
        public BuiltinModelItemRenderer getInstance() {
            if (instance == null) {
                throw new IllegalStateException("Attempted to access a BlockEntityWithoutWorldRenderer instance that had not been created.");
            }
            return instance;
        }
    }
}
