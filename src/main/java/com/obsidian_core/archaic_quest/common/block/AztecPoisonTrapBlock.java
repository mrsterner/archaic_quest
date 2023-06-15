package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.AztecPoisonTrapBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AztecPoisonTrapBlock extends Block implements BlockEntityProvider {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    public static final DirectionProperty ROTATION = Properties.HORIZONTAL_FACING;

    public AztecPoisonTrapBlock(Settings properties) {
        super(properties);
        setDefaultState(getDefaultState().with(ACTIVE, false).with(ROTATION, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction facing = context.getPlayerFacing();
        return getDefaultState().with(ROTATION, facing);
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(ACTIVE)) {
            Vec3d center = new Vec3d(pos.getX() + 0.5D, pos.getY() + 1.1D, pos.getZ() + 0.5D);

            for (int i = 0; i < 12; i++) {
                final double x = center.getX() + (random.nextGaussian() * 1.5D);
                final double z = center.getZ() + (random.nextGaussian() * 1.5D);

                if (world.getBlockState(pos.up().add((int) x, 0, (int) z)).isAir()) {
                    world.addParticle(AQParticles.POISON_CLOUD, x, center.getY(), z, 0.0D, -0.01D, 0.0D);
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AztecPoisonTrapBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return (lvl, pos, blockState, blockEntity) -> AztecPoisonTrapBlockEntity.tick(world, pos, state, (AztecPoisonTrapBlockEntity) blockEntity);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(ACTIVE, ROTATION));
    }
}
