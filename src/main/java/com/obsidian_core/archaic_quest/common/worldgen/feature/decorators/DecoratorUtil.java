package com.obsidian_core.archaic_quest.common.worldgen.feature.decorators;

import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.world.block.state.properties.Properties;
import net.minecraft.world.world.worldgen.feature.treedecorators.TreeDecorator;

public final class DecoratorUtil {

    protected static void placeVineVar(TreeDecorator.Context context, BlockPos pos, Direction facing) {
        context.setBlockState(pos, AQBlocks.VINES_1.get().getDefaultState().with(Properties.HORIZONTAL_FACING, facing));
    }
}
