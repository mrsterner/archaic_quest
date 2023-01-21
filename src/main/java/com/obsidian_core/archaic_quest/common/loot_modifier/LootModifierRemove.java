package com.obsidian_core.archaic_quest.common.loot_modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.obsidian_core.archaic_quest.common.register.AQGlobalLootModifiers;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/** Simple loot modifier to removing an item from a loot table */
public class LootModifierRemove extends LootModifier {

    private final Item itemToRemove;
    private final ResourceLocation targetLootTable;

    public static final Supplier<Codec<LootModifierRemove>> CODEC = () -> RecordCodecBuilder.create(inst -> LootModifier.codecStart(inst)
            .and(inst.group(
                            ForgeRegistries.ITEMS.getCodec()
                                    .fieldOf("item")
                                    .forGetter(m -> m.itemToRemove),
                            ResourceLocation.CODEC
                                    .fieldOf("lootTable")
                                    .forGetter(m -> m.targetLootTable)
                    )
            )
            .apply(inst, LootModifierRemove::new)
    );


    public LootModifierRemove(LootItemCondition[] conditions, Item itemToRemove, ResourceLocation lootTable) {
        super(conditions);
        this.itemToRemove = itemToRemove;
        this.targetLootTable = lootTable;
    }


    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getQueriedLootTableId().equals(this.targetLootTable)) {
            generatedLoot.removeIf((itemStack) -> itemStack.getItem() == this.itemToRemove);
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return AQGlobalLootModifiers.REMOVE_ITEM_MODIFIER.get();
    }
}