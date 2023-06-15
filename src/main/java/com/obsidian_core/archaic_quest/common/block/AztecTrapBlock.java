package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerWorld;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class AztecTrapBlock extends DispenserBlock {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public AztecTrapBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
    }

    @Override
    public void neighborChanged(BlockState state, World level, BlockPos pos, Block block, BlockPos neighborPos, boolean wtfIsThis) {
        boolean powered = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
        boolean triggered = state.getValue(TRIGGERED);

        if (powered && !triggered) {
            level.scheduleTick(pos, this, 4);
            level.setBlock(pos, state.setValue(TRIGGERED, true).setValue(ACTIVE, true), 3);
        }
        else if (!powered && triggered) {
            level.setBlock(pos, state.setValue(TRIGGERED, false), 3);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld serverWorld, BlockPos pos, RandomSource random) {
        super.tick(state, serverWorld, pos, random);
        serverWorld.setBlock(pos, state.setValue(ACTIVE, false), 3);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(ACTIVE));
    }
}
