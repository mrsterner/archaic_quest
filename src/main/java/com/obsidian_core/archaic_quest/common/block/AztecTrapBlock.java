package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.model.obj.OBJLoader;

import java.util.Random;

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
            level.getBlockTicks().scheduleTick(pos, this, 4);
            level.setBlock(pos, state.setValue(TRIGGERED, true).setValue(ACTIVE, true), 3);
        }
        else if (!powered && triggered) {
            level.setBlock(pos, state.setValue(TRIGGERED, false), 3);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld serverLevel, BlockPos pos, Random random) {
        super.tick(state, serverLevel, pos, random);
        serverLevel.setBlock(pos, state.setValue(ACTIVE, false), 3);
    }

    @Override
    public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(ACTIVE));
    }
}
