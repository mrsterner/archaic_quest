package com.obsidian_core.archaic_quest.common.loot_modifier;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import java.util.Random;

public class LootModifierAdd extends LootModifier {

    private final Item itemToAdd;
    private final int maxStackCount;
    private final int minStackCount;
    private final ResourceLocation targetLootTable;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public LootModifierAdd(ILootCondition[] conditionsIn, ResourceLocation lootTable, Item itemToAdd, int maxStackCount, int minStackCount) {
        super(conditionsIn);
        this.itemToAdd = itemToAdd;
        this.maxStackCount = maxStackCount;
        this.minStackCount = minStackCount;
        this.targetLootTable = lootTable;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (context.getQueriedLootTableId().equals(targetLootTable)) {
            Random random = new Random();
            ItemStack stack = new ItemStack(this.itemToAdd, random.nextInt(this.maxStackCount + 1));

            generatedLoot.add(stack);
        }
        return generatedLoot;
    }

    public static class LootModifierAddSerializer extends GlobalLootModifierSerializer<LootModifierAdd> {

        @Override
        public LootModifierAdd read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
            Item itemToAdd = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "item")));
            int maxStackCount = JSONUtils.getAsInt(object, "maxCount");
            int minStackCount = JSONUtils.getAsInt(object, "minCount");
            ResourceLocation lootTable = new ResourceLocation(JSONUtils.getAsString(object, "lootTable"));

            return new LootModifierAdd(lootConditions, lootTable, itemToAdd, maxStackCount, minStackCount);
        }

        @Override
        public JsonObject write(LootModifierAdd instance) {
            final JsonObject json = this.makeConditions(instance.conditions);
            json.addProperty("item", Objects.requireNonNull(instance.itemToAdd.getRegistryName()).toString());
            json.addProperty("maxCount", instance.maxStackCount);
            json.addProperty("minCount", instance.minStackCount);
            json.add("lootTable", ResourceLocation.CODEC.encodeStart(JsonOps.INSTANCE, instance.targetLootTable)
                    .getOrThrow(false, (s) -> ArchaicQuest.LOGGER.log(Level.ERROR, s)));

            return json;
        }
    }
}
