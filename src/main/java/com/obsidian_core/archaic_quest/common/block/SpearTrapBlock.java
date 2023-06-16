package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.state.AQStateProperties;
import com.obsidian_core.archaic_quest.common.misc.AQDamageSources;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class SpearTrapBlock extends Block implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty EXTENDED = AQStateProperties.EXTENDED;

    private final float damageMult;

    private static final VoxelShape[] shapes = new VoxelShape[] {
            Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D)
    };


    public SpearTrapBlock(float baseDamage, Settings properties) {
        super(properties.velocityMultiplier(0.4F));
        setDefaultState(getDefaultState().with(WATERLOGGED, false).with(EXTENDED, false));
        this.damageMult = Math.max(0.0F, baseDamage);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallHeight) {
        entity.handleFallDamage(fallHeight + damageMult, 2.0F, AQDamageSources.SPEAR_TRAP);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return isExtended(state) ? shapes[0] : shapes[1];
    }

    private boolean isExtended(BlockState state) {
        return state.isOf(this) && state.get(EXTENDED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP) || world.getBlockState(pos.down()).isOf(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState getStateForNeighborUpdate(BlockState newState, Direction direction, BlockState state, WorldAccess world, BlockPos pos, BlockPos pos1) {
        if (!newState.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        else {
            BlockState upState = world.getBlockState(pos.up());

            if (newState.isOf(this) && newState.get(EXTENDED) && !upState.isOf(this)) {
                world.setBlockState(pos, newState.with(EXTENDED, false), 3);
            }
            return super.getStateForNeighborUpdate(newState, direction, state, world, pos, pos1);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockPos clickedPos = context.getBlockPos();
        World world = context.getWorld();
        boolean waterlogged = world.getBlockState(clickedPos).getFluidState().isIn(FluidTags.WATER);

        return this.getDefaultState().with(WATERLOGGED, waterlogged);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if (!state.isOf(this))
            super.onPlaced(world, pos, state, livingEntity, itemStack);

        BlockPos downPos = pos.down();
        boolean spearsBelow = world.getBlockState(downPos).isOf(this);

        if (spearsBelow) {
            BlockState downState = world.getBlockState(downPos);
            world.setBlockState(downPos, downState.with(EXTENDED, true), 3);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(EXTENDED, WATERLOGGED);
    }
}
