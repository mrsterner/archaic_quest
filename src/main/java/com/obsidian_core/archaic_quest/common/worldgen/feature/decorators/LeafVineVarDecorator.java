package com.obsidian_core.archaic_quest.common.worldgen.feature.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.world.worldgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.world.worldgen.feature.treedecorators.TreeDecoratorType;

public class LeafVineVarDecorator extends TreeDecorator {

    public static final Codec<LeafVineVarDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(LeafVineVarDecorator::new, (decorator) -> decorator.probability).codec();

    private final float probability;

    @Override
    protected TreeDecoratorType<?> type() {
        return AQTreeDecoratorType.LEAF_VINE.get();
    }

    public LeafVineVarDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource random = context.random();

        context.leaves().forEach((pos) -> {
            if (random.nextFloat() < probability) {
                BlockPos westPos = pos.west();
                if (context.isAir(westPos)) {
                    addHangingVine(westPos, Direction.WEST, context);
                }
            }

            if (random.nextFloat() < probability) {
                BlockPos eastPos = pos.east();
                if (context.isAir(eastPos)) {
                    addHangingVine(eastPos, Direction.EAST, context);
                }
            }

            if (random.nextFloat() < probability) {
                BlockPos northPos = pos.north();
                if (context.isAir(northPos)) {
                    addHangingVine(northPos, Direction.NORTH, context);
                }
            }

            if (random.nextFloat() < probability) {
                BlockPos southPos = pos.south();
                if (context.isAir(southPos)) {
                    addHangingVine(southPos, Direction.SOUTH, context);
                }
            }
        });
    }

    private static void addHangingVine(BlockPos pos, Direction direction, TreeDecorator.Context context) {
        DecoratorUtil.placeVineVar(context, pos, direction);
        int i = 4;

        for(BlockPos blockpos = pos.below(); context.isAir(blockpos) && i > 0; --i) {
            DecoratorUtil.placeVineVar(context, blockpos, direction);
            blockpos = blockpos.below();
        }
    }
}
