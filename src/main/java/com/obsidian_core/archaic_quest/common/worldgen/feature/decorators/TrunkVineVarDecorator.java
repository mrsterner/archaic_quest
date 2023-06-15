package com.obsidian_core.archaic_quest.common.worldgen.feature.decorators;

import com.mojang.serialization.Codec;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.world.block.state.properties.BlockStateProperties;
import net.minecraft.world.world.worldgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.world.worldgen.feature.treedecorators.TreeDecoratorType;

public class TrunkVineVarDecorator extends TreeDecorator {

    public static final Codec<TrunkVineVarDecorator> CODEC = Codec.unit(() -> TrunkVineVarDecorator.INSTANCE);
    public static final TrunkVineVarDecorator INSTANCE = new TrunkVineVarDecorator();

    public TrunkVineVarDecorator() {

    }

    @Override
    protected TreeDecoratorType<?> type() {
        return AQTreeDecoratorType.TRUNK_VINE.get();
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource randomsource = context.random();

        context.logs().forEach((pos) -> {
            if (randomsource.nextInt(3) > 0) {
                BlockPos westPos = pos.west();
                if (context.isAir(westPos)) {
                    placeVineVar(context, westPos, Direction.WEST);
                }
            }

            if (randomsource.nextInt(3) > 0) {
                BlockPos eastPos = pos.east();
                if (context.isAir(eastPos)) {
                    placeVineVar(context, eastPos, Direction.EAST);
                }
            }

            if (randomsource.nextInt(3) > 0) {
                BlockPos northPos = pos.north();
                if (context.isAir(northPos)) {
                    placeVineVar(context, northPos, Direction.NORTH);
                }
            }

            if (randomsource.nextInt(3) > 0) {
                BlockPos southPos = pos.south();
                if (context.isAir(southPos)) {
                    placeVineVar(context, southPos, Direction.SOUTH);
                }
            }
        });
    }

    private void placeVineVar(TreeDecorator.Context context, BlockPos pos, Direction facing) {
        context.setBlock(pos, AQBlocks.VINES_1.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing));
    }
}
