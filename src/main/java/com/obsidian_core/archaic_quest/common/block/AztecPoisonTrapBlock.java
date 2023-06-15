package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.AztecPoisonTrapBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class AztecPoisonTrapBlock extends Block implements EntityBlock {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final DirectionProperty ROTATION = BlockStateProperties.HORIZONTAL_FACING;

    public AztecPoisonTrapBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(ACTIVE, false).setValue(ROTATION, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection();
        return defaultBlockState().setValue(ROTATION, facing);
    }


    @Override
    public void animateTick(BlockState state, World level, BlockPos pos, RandomSource random) {
        if (state.getValue(ACTIVE)) {
            Vec3 center = new Vec3(pos.getX() + 0.5D, pos.getY() + 1.1D, pos.getZ() + 0.5D);

            for (int i = 0; i < 12; i++) {
                final double x = center.x() + (random.nextGaussian() * 1.5D);
                final double z = center.z() + (random.nextGaussian() * 1.5D);

                if (level.getBlockState(pos.above().offset((int) x, 0, (int) z)).isAir()) {
                    level.addParticle(AQParticles.POISON_CLOUD.get(), x, center.y(), z, 0.0D, -0.01D, 0.0D);
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AztecPoisonTrapBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World level, BlockState state, BlockEntityType<T> type) {
        return (lvl, pos, blockState, blockEntity) -> AztecPoisonTrapBlockEntity.tick(level, pos, state, (AztecPoisonTrapBlockEntity) blockEntity);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(ACTIVE, ROTATION));
    }
}
