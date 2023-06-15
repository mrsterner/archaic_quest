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

import java.util.Random;
import java.util.function.Supplier;

public class LootModifierAdd extends LootModifier {

    public final Item itemToAdd;
    public final int maxStackCount;
    public final int minStackCount;
    public final Identifier targetLootTable;


    public static final Supplier<Codec<LootModifierAdd>> CODEC = () -> RecordCodecBuilder.create(inst -> LootModifier.codecStart(inst)
            .and(inst.group(
                    ForgeRegistries.ITEMS.getCodec()
                            .fieldOf("item")
                            .forGetter(m -> m.itemToAdd),
                    Codec.INT.fieldOf("maxCount")
                            .forGetter(m -> m.maxStackCount),
                    Codec.INT.fieldOf("minCount")
                            .forGetter(m -> m.minStackCount),
                    Identifier.CODEC
                            .fieldOf("lootTable")
                            .forGetter(m -> m.targetLootTable)
                    )
            )
            .apply(inst, LootModifierAdd::new)
    );

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public LootModifierAdd(LootCondition[] conditionsIn, Item itemToAdd, int maxStackCount, int minStackCount, Identifier lootTable) {
        super(conditionsIn);
        this.itemToAdd = itemToAdd;
        this.maxStackCount = maxStackCount;
        this.minStackCount = minStackCount;
        this.targetLootTable = lootTable;
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getQueriedLootTableId().equals(targetLootTable)) {
            Random random = new Random();
            ItemStack stack = new ItemStack(itemToAdd, random.nextInt(maxStackCount + 1));

            generatedLoot.add(stack);
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return AQGlobalLootModifiers.ADD_ITEM_MODIFIER;
    }
}
