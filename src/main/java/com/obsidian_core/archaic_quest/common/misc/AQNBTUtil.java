package com.obsidian_core.archaic_quest.common.misc;

import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Objects;

public class AQNBTUtil {

    /**
     * Copy-paste of {@link net.minecraft.nbt.NbtUtils#writeBlockState(BlockState)},
     * Except this version 100% guaranteed looks through the Forge registry.
     *
     * Not sure if this is redundant or not, very likely it is.
     */
    public static CompoundTag writeBlockState(BlockState state) {
        CompoundTag blockTag = new CompoundTag();
        blockTag.putString("Name", Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(state.getBlock())).toString());
        ImmutableMap<Property<?>, Comparable<?>> properties = state.getValues();

        if (!properties.isEmpty()) {
            CompoundTag propertyTag = new CompoundTag();

            for(Map.Entry<Property<?>, Comparable<?>> entry : properties.entrySet()) {
                Property<?> property = entry.getKey();
                propertyTag.putString(property.getName(), getName(property, entry.getValue()));
            }
            blockTag.put("Properties", propertyTag);
        }
        return blockTag;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> String getName(Property<T> property, Comparable<?> comparable) {
        return property.getName((T) comparable);
    }
}
