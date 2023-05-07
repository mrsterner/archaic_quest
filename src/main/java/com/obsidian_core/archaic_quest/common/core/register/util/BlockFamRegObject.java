package com.obsidian_core.archaic_quest.common.core.register.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockFamRegObject {

    private final Map<BlockFamily.Variant, RegistryObject<Block>> collection = new HashMap<>();


    private BlockFamRegObject(Pair<BlockFamily.Variant, RegistryObject<Block>>[] pairs) {
        for (Pair<BlockFamily.Variant, RegistryObject<Block>> pair : pairs) {
            collection.put(pair.getFirst(), pair.getSecond());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Nullable
    public RegistryObject<Block> getVariant(BlockFamily.Variant variant) {
        if (collection.containsKey(variant))
            return collection.get(variant);

        return null;
    }

    public static class Builder {

        private Builder() {
            
        }
    }
}
