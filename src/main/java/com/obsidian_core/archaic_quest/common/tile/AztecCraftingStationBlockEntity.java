package com.obsidian_core.archaic_quest.common.tile;

import com.obsidian_core.archaic_quest.common.misc.TranslationReferences;
import com.obsidian_core.archaic_quest.common.register.AQBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class AztecCraftingStationBlockEntity extends BlockEntity implements MenuProvider {

    public AztecCraftingStationBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.AZTEC_CRAFTING_STATION.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return TranslationReferences.AZTEC_CRAFTING_STATION_CONTAINER_NAME;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return null;
    }
}
