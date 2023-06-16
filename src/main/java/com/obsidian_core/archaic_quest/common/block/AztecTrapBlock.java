package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;


public class AztecTrapBlock extends DispenserBlock {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

    public AztecTrapBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(ACTIVE, false));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean wtfIsThis) {
        boolean powered = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.up());
        boolean triggered = state.get(TRIGGERED);

        if (powered && !triggered) {
            world.createAndScheduleBlockTick(pos, this, 4);
            world.setBlockState(pos, state.with(TRIGGERED, true).with(ACTIVE, true), 3);
        }
        else if (!powered && triggered) {
            world.setBlockState(pos, state.with(TRIGGERED, false), 3);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld serverWorld, BlockPos pos, Random random) {
        super.scheduledTick(state, serverWorld, pos, random);
        serverWorld.setBlockState(pos, state.with(ACTIVE, false), 3);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        super.appendProperties(stateBuilder.add(ACTIVE));
    }
}
