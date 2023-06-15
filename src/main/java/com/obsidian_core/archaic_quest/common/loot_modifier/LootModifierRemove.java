package com.obsidian_core.archaic_quest.common.loot_modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.obsidian_core.archaic_quest.common.core.register.AQGlobalLootModifiers;
import io.github.fabricators_of_create.porting_lib.loot.IGlobalLootModifier;
import io.github.fabricators_of_create.porting_lib.loot.LootModifier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/** Simple loot modifier to removing an item from a loot table */
public class LootModifierRemove extends LootModifier {

    private final Item itemToRemove;
    private final Identifier targetLootTable;

    public static final Supplier<Codec<LootModifierRemove>> CODEC = () -> RecordCodecBuilder.create(inst -> LootModifier.codecStart(inst)
            .and(inst.group(
                            ForgeRegistries.ITEMS.getCodec()
                                    .fieldOf("item")
                                    .forGetter(m -> m.itemToRemove),
                            Identifier.CODEC
                                    .fieldOf("lootTable")
                                    .forGetter(m -> m.targetLootTable)
                    )
            )
            .apply(inst, LootModifierRemove::new)
    );


    public LootModifierRemove(LootCondition[] conditions, Item itemToRemove, Identifier lootTable) {
        super(conditions);
        this.itemToRemove = itemToRemove;
        this.targetLootTable = lootTable;
    }


    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getQueriedLootTableId().equals(this.targetLootTable)) {
            generatedLoot.removeIf((itemStack) -> itemStack.getItem() == this.itemToRemove);
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return AQGlobalLootModifiers.REMOVE_ITEM_MODIFIER;
    }
}