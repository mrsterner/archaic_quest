package com.obsidian_core.archaic_quest.common.loot_modifier;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

/** Simple loot modifier to removing an item from a loot table */
public class LootModifierRemove extends LootModifier {

    private final Item itemToRemove;
    private final ResourceLocation targetLootTable;

    public LootModifierRemove(ILootCondition[] conditions, ResourceLocation lootTable, Item itemToRemove) {
        super(conditions);
        this.itemToRemove = itemToRemove;
        this.targetLootTable = lootTable;
    }


    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (context.getQueriedLootTableId().equals(this.targetLootTable)) {
            generatedLoot.removeIf((itemStack) -> itemStack.getItem() == this.itemToRemove);
        }
        return generatedLoot;
    }

    public static class LootModifierRemoveSerializer extends GlobalLootModifierSerializer<LootModifierRemove> {

        @Override
        public LootModifierRemove read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
            Item itemToRemove = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "item")));
            ResourceLocation lootTable = new ResourceLocation(JSONUtils.getAsString(object, "lootTable"));

            return new LootModifierRemove(lootConditions, lootTable, itemToRemove);
        }

        @Override
        public JsonObject write(LootModifierRemove instance) {
            final JsonObject json = this.makeConditions(instance.conditions);
            json.addProperty("item", Objects.requireNonNull(instance.itemToRemove.getRegistryName()).toString());
            json.add("lootTable", ResourceLocation.CODEC.encodeStart(JsonOps.INSTANCE, instance.targetLootTable)
                    .getOrThrow(false, (s) -> ArchaicQuest.LOGGER.log(Level.ERROR, s)));

            return json;
        }
    }
}