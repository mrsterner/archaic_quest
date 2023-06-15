package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.state.AQStateSettings;
import com.obsidian_core.archaic_quest.common.misc.AQDamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.BlockGetter;
import net.minecraft.world.world.World;
import net.minecraft.world.world.WorldAccessor;
import net.minecraft.world.world.WorldReader;
import net.minecraft.world.world.block.*;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.Properties;
import net.minecraft.world.world.block.state.properties.BooleanProperty;
import net.minecraft.world.world.block.state.properties.DripstoneThickness;
import net.minecraft.world.world.material.FluidState;
import net.minecraft.world.world.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SpearTrapBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty EXTENDED = AQStateSettings.EXTENDED;

    private final float damageMult;

    private static final VoxelShape[] shapes = new VoxelShape[] {
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D)
    };


    public SpearTrapBlock(float baseDamage, Settings properties) {
        super(properties.speedFactor(0.4F));
        setDefaultState(getDefaultState().with(WATERLOGGED, false).with(EXTENDED, false));
        this.damageMult = Math.max(0.0F, baseDamage);
    }

    @Override
    public void fallOn(World world, BlockState state, BlockPos pos, Entity entity, float fallHeight) {
        entity.causeFallDamage(fallHeight + damageMult, 2.0F, AQDamageSources.SPEAR_TRAP);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return isExtended(state) ? shapes[0] : shapes[1];
    }

    private boolean isExtended(BlockState state) {
        return state.is(this) && state.get(EXTENDED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState state, WorldReader world, BlockPos pos) {
        return world.getBlockState(pos.down()).isFaceSturdy(world, pos.down(), Direction.UP) || world.getBlockState(pos.down()).is(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState newState, Direction direction, BlockState state, WorldAccessor world, BlockPos pos, BlockPos pos1) {
        if (!newState.canSurvive(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        else {
            BlockState upState = world.getBlockState(pos.up());

            if (newState.is(this) && newState.get(EXTENDED) && !upState.is(this)) {
                world.setBlockState(pos, newState.with(EXTENDED, false), 3);
            }
            return super.updateShape(newState, direction, state, world, pos, pos1);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos clickedPos = context.getBlockPos();
        World world = context.getWorld();
        boolean waterlogged = world.getBlockState(clickedPos).getFluidState().is(FluidTags.WATER);

        return this.getDefaultState().with(WATERLOGGED, waterlogged);
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if (!state.is(this))
            super.setPlacedBy(world, pos, state, livingEntity, itemStack);

        BlockPos downPos = pos.down();
        boolean spearsBelow = world.getBlockState(downPos).is(this);

        if (spearsBelow) {
            BlockState downState = world.getBlockState(downPos);
            world.setBlockState(downPos, downState.with(EXTENDED, true), 3);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(EXTENDED, WATERLOGGED);
    }
}
