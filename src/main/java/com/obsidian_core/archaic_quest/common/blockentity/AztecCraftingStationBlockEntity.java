package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.misc.TranslationReferences;
import com.obsidian_core.archaic_quest.registry.AQBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class AztecCraftingStationBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {

    public AztecCraftingStationBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntityTypes.AZTEC_CRAFTING_STATION, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return TranslationReferences.AZTEC_CRAFTING_STATION_CONTAINER_NAME;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return null;
    }
}
