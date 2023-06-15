package com.obsidian_core.archaic_quest.common.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.BlockGetter;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.SimpleWaterloggedBlock;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.Properties;
import net.minecraft.world.world.block.state.properties.BooleanProperty;
import net.minecraft.world.world.block.state.properties.DirectionProperty;
import net.minecraft.world.world.block.state.properties.EnumProperty;
import net.minecraft.world.world.material.FluidState;
import net.minecraft.world.world.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class ChiselPillarBlock extends Block implements SimpleWaterloggedBlock {

    public static final EnumProperty<Type> PILLAR_TPOSITIVE_YE = EnumProperty.create("pillar_type", Type.class);
    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 16.0D),
            Block.box(0.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D)
    };


    public ChiselPillarBlock(Settings properties) {
        super(properties);
        this.setDefaultState(getDefaultState().with(PILLAR_TPOSITIVE_YE, Type.BOTTOM).with(WATERLOGGED, false).with(FACING, Direction.NORTH));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.get(FACING)) {
            case NORTH, SOUTH -> SHAPES[0];
            case EAST, WEST -> SHAPES[1];
            default -> SHAPES[2];
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Type type = Type.BOTTOM;
        Direction clickedFace = context.getClickedFace();
        BlockPos clickedPos = context.getBlockPos();
        World world = context.getWorld();
        boolean waterlogged = world.getBlockState(clickedPos).getFluidState().is(FluidTags.WATER);

        if (context.getPlayer() != null) {
            BlockState clickedState = world.getBlockState(clickedPos.relative(clickedFace.getOpposite()));

            if (clickedState.is(this)) {
                Direction clickedStateFace = clickedState.get(FACING);

                if (clickedFace == clickedStateFace) {
                    type = Type.TOP;
                }
            }
        }
        return this.getDefaultState().with(FACING, clickedFace).with(PILLAR_TPOSITIVE_YE, type).with(WATERLOGGED, waterlogged);
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if (!state.is(this))
            super.setPlacedBy(world, pos, state, livingEntity, itemStack);

        Direction facing = state.get(FACING);
        BlockPos behindPos = pos.relative(facing.getOpposite());
        BlockState behindState = world.getBlockState(behindPos);

        if (behindState.is(this)) {
            if (behindState.get(PILLAR_TPOSITIVE_YE) == Type.TOP && behindState.get(FACING) == facing) {
                world.setBlockState(behindPos, behindState.with(PILLAR_TPOSITIVE_YE, Type.SMOOTH), 3);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(PILLAR_TPOSITIVE_YE, FACING, WATERLOGGED);
    }

    public enum Type implements StringRepresentable {

        BOTTOM("bottom"),
        TOP("top"),
        SMOOTH("smooth"),
        SKULLS("skulls"),
        FACES("faces"),
        CHISELED("chiseled"),
        HORIZONTAL_SWIRLY("horizontal_swirly"),
        VERTICAL_SWIRLY("vertical_swirly"),
        INDENTED("indented"),
        LINES("lines"),
        WIDE("wide");


        Type(String name) {
            this.name = name;
        }
        private final String name;

        @Override
        public String getSerializedName() {
            return this.name;
        }


        public static Type chiselCycle(boolean backwardCycle, Type type) {
            if (type == BOTTOM || type == TOP)
                return type;

            int index;

            if (backwardCycle) {
                index = type.ordinal() - 1;

                if (index < 2)
                    index = values().length - 1;
            }
            else {
                index = type.ordinal() + 1;

                if (index >= values().length)
                    index = 2;
            }
            return values()[index];
        }

        public boolean canBeChiseled() {
            return this != BOTTOM && this != TOP;
        }
    }
}
