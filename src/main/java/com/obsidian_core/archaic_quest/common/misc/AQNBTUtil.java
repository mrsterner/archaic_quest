package com.obsidian_core.archaic_quest.common.misc;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.Property;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class AQNBTUtil {

    /**
     * Copy-paste of {@link net.minecraft.nbt.NBTUtil#writeBlockState(BlockState)},
     * Except this version 100% guaranteed looks through the Forge registry.
     *
     * Not sure if this is redundant or not, very likely it is.
     */
    public static CompoundNBT writeBlockState(BlockState state) {
        CompoundNBT blockTag = new CompoundNBT();
        blockTag.putString("Name", ForgeRegistries.BLOCKS.getKey(state.getBlock()).toString());
        ImmutableMap<Property<?>, Comparable<?>> properties = state.getValues();

        if (!properties.isEmpty()) {
            CompoundNBT propertyTag = new CompoundNBT();

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
