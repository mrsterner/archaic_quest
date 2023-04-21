package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class VaseBlockEntity extends BlockEntity {

    private ResourceLocation lootTableId = null;
    private long lootTableSeed = 0L;

    public VaseBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.VASE.get(), pos, state);
    }

    public void setLootTable(ResourceLocation lootTableId, long lootTableSeed) {
        this.lootTableId = lootTableId;
        this.lootTableSeed = lootTableSeed;
    }

    public void dropLootTable(@Nullable Player player) {
        if (lootTableId != null && level.getServer() != null) {
            LootTable lootTable = level.getServer().getLootTables().get(lootTableId);
            lootTableId = null;
            LootContext.Builder builder = (new LootContext.Builder((ServerLevel) level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(worldPosition)).withOptionalRandomSeed(lootTableSeed);
            if (player != null) {
                builder.withLuck(player.getLuck()).withParameter(LootContextParams.THIS_ENTITY, player);
            }
            NonNullList<ItemStack> loot = NonNullList.create();
            loot.addAll(lootTable.getRandomItems(builder.create(LootContextParamSets.CHEST)));

            if (!loot.isEmpty()) {
                loot.forEach((itemStack) -> Containers.dropItemStack(level, worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D, itemStack));
            }
        }
    }


    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);

        if (compoundTag.contains("LootTable", Tag.TAG_STRING)) {
            lootTableId = new ResourceLocation(compoundTag.getString("LootTable"));
        }
        if (compoundTag.contains("LootTableSeed", Tag.TAG_LONG)) {
            lootTableSeed = compoundTag.getLong("LootTableSeed");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        if (lootTableId != null) {
            compoundTag.putString("LootTable", lootTableId.toString());
        }
        if (lootTableSeed != 0L) {
            compoundTag.putLong("LootTableSeed", lootTableSeed);
        }
    }
}
