package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import com.obsidian_core.archaic_quest.registry.AQBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class VaseBlockEntity extends BlockEntity {

    private Identifier lootTableId = null;
    private long lootTableSeed = 0L;

    public VaseBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntityTypes.VASE, pos, state);
    }

    public void setLootTable(Identifier lootTableId, long lootTableSeed) {
        this.lootTableId = lootTableId;
        this.lootTableSeed = lootTableSeed;
    }

    public void dropLootTable(@Nullable PlayerEntity player) {
        if (lootTableId != null && world.getServer() != null) {
            LootTable lootTable = world.getServer().getLootManager().getTable(lootTableId);
            lootTableId = null;
            LootContext.Builder builder = (new LootContext.Builder((ServerWorld) world)).parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos)).random(lootTableSeed);
            if (player != null) {
                builder.luck(player.getLuck()).parameter(LootContextParameters.THIS_ENTITY, player);
            }
            DefaultedList<ItemStack> loot = DefaultedList.of();
            loot.addAll(lootTable.generateLoot(builder.build(LootContextTypes.CHEST)));

            if (!loot.isEmpty()) {
                loot.forEach((itemStack) -> ItemScatterer.spawn(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, itemStack));
            }
        }
    }


    @Override
    public void readNbt(NbtCompound compoundTag) {
        super.readNbt(compoundTag);

        if (compoundTag.contains("LootTable", NbtElement.STRING_TYPE)) {
            lootTableId = new Identifier(compoundTag.getString("LootTable"));
        }
        if (compoundTag.contains("LootTableSeed", NbtElement.LONG_TYPE)) {
            lootTableSeed = compoundTag.getLong("LootTableSeed");
        }
    }

    @Override
    protected void writeNbt(NbtCompound compoundTag) {
        super.writeNbt(compoundTag);

        if (lootTableId != null) {
            compoundTag.putString("LootTable", lootTableId.toString());
        }
        if (lootTableSeed != 0L) {
            compoundTag.putLong("LootTableSeed", lootTableSeed);
        }
    }
}
