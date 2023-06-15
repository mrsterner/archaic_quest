package com.obsidian_core.archaic_quest.client.render.blockentity.bewlr;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutWorldRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class BEWLRS {

    public static final List<Holder> BEWLR_LIST = new ArrayList<>();
    public static final EntityModelSet MODEL_SET = new EntityModelSet();

    public static final Holder AZTEC_CRAFTING_STATION = new Holder(AztecCraftingStationBEWLR::new);
    public static final Holder AZTEC_THRONE = new Holder(AztecThroneBEWLR::new);
    public static final Holder AZTEC_DUNGEON_CHEST = new Holder(AztecDungeonChestBEWLR::new);


    public static class Holder {

        private final BiFunction<BlockEntityRenderDispatcher, EntityModelSet, BlockEntityWithoutWorldRenderer> factory;
        private BlockEntityWithoutWorldRenderer instance;

        public Holder(BiFunction<BlockEntityRenderDispatcher, EntityModelSet, BlockEntityWithoutWorldRenderer> factory) {
            this.factory = factory;
            BEWLR_LIST.add(this);
        }

        public void populate(BlockEntityRenderDispatcher renderDispatcher) {
            instance = factory.apply(renderDispatcher, MODEL_SET);
        }

        @Nullable
        public BlockEntityWithoutWorldRenderer getInstance() {
            if (instance == null) {
                throw new IllegalStateException("Attempted to access a BlockEntityWithoutWorldRenderer instance that had not been created.");
            }
            return instance;
        }
    }
}
