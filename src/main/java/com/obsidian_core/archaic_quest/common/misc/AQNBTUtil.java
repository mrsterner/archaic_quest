package com.obsidian_core.archaic_quest.common.misc;


import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Property;

import java.util.Map;
import java.util.Objects;

public class AQNBTUtil {

    /**
     * Copy-paste of {@link net.minecraft.nbt.NbtUtils#writeBlockState(BlockState)},
     * Except this version 100% guaranteed looks through the Forge registry.
     *
     * Not sure if this is redundant or not, very likely it is.
     */
    public static NbtCompound writeBlockState(BlockState state) {
        NbtCompound blockTag = new NbtCompound();
        blockTag.putString("Name", Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(state.getBlock())).toString());
        ImmutableMap<Property<?>, Comparable<?>> properties = state.gets();

        if (!properties.isEmpty()) {
            NbtCompound propertyTag = new NbtCompound();

            for(Map.Entry<Property<?>, Comparable<?>> entry : properties.entrySet()) {
                Property<?> property = entry.getKey();
                propertyTag.putString(property.getName(), getName(property, entry.get()));
            }
            blockTag.put("Settings", propertyTag);
        }
        return blockTag;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> String getName(Property<T> property, Comparable<?> comparable) {
        return property.getName((T) comparable);
    }
}
