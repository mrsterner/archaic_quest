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
import net.minecraft.world.world.block.state.properties.BlockStateProperties;
import net.minecraft.world.world.block.state.properties.BooleanProperty;
import net.minecraft.world.world.block.state.properties.DirectionProperty;
import net.minecraft.world.world.block.state.properties.EnumProperty;
import net.minecraft.world.world.material.FluidState;
import net.minecraft.world.world.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class ChiselPillarBlock extends Block implements SimpleWaterloggedBlock {

    public static final EnumProperty<Type> PILLAR_TYPE = EnumProperty.create("pillar_type", Type.class);
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 16.0D),
            Block.box(0.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D)
    };


    public ChiselPillarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(PILLAR_TYPE, Type.BOTTOM).setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH, SOUTH -> SHAPES[0];
            case EAST, WEST -> SHAPES[1];
            default -> SHAPES[2];
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Type type = Type.BOTTOM;
        Direction clickedFace = context.getClickedFace();
        BlockPos clickedPos = context.getClickedPos();
        World world = context.getWorld();
        boolean waterlogged = world.getBlockState(clickedPos).getFluidState().is(FluidTags.WATER);

        if (context.getPlayer() != null) {
            BlockState clickedState = world.getBlockState(clickedPos.relative(clickedFace.getOpposite()));

            if (clickedState.is(this)) {
                Direction clickedStateFace = clickedState.getValue(FACING);

                if (clickedFace == clickedStateFace) {
                    type = Type.TOP;
                }
            }
        }
        return this.defaultBlockState().setValue(FACING, clickedFace).setValue(PILLAR_TYPE, type).setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if (!state.is(this))
            super.setPlacedBy(world, pos, state, livingEntity, itemStack);

        Direction facing = state.getValue(FACING);
        BlockPos behindPos = pos.relative(facing.getOpposite());
        BlockState behindState = world.getBlockState(behindPos);

        if (behindState.is(this)) {
            if (behindState.getValue(PILLAR_TYPE) == Type.TOP && behindState.getValue(FACING) == facing) {
                world.setBlock(behindPos, behindState.setValue(PILLAR_TYPE, Type.SMOOTH), 3);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(PILLAR_TYPE, FACING, WATERLOGGED);
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
