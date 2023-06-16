package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ChiselPillarBlock extends Block implements Waterloggable {

    public static final EnumProperty<Type> PILLAR_TPOSITIVE_YE = EnumProperty.of("pillar_type", Type.class);
    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.createCuboidShape(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 16.0D),
            Block.createCuboidShape(0.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D),
            Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D)
    };


    public ChiselPillarBlock(Settings properties) {
        super(properties);
        this.setDefaultState(getDefaultState().with(PILLAR_TPOSITIVE_YE, Type.BOTTOM).with(WATERLOGGED, false).with(FACING, Direction.NORTH));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH, SOUTH -> SHAPES[0];
            case EAST, WEST -> SHAPES[1];
            default -> SHAPES[2];
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        Type type = Type.BOTTOM;
        Direction clickedFace = context.getSide();
        BlockPos clickedPos = context.getBlockPos();
        World world = context.getWorld();
        boolean waterlogged = world.getBlockState(clickedPos).getFluidState().isIn(FluidTags.WATER);

        if (context.getPlayer() != null) {
            BlockState clickedState = world.getBlockState(clickedPos.offset(clickedFace.getOpposite()));

            if (clickedState.isOf(this)) {
                Direction clickedStateFace = clickedState.get(FACING);

                if (clickedFace == clickedStateFace) {
                    type = Type.TOP;
                }
            }
        }
        return this.getDefaultState().with(FACING, clickedFace).with(PILLAR_TPOSITIVE_YE, type).with(WATERLOGGED, waterlogged);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if (!state.isOf(this))
            super.onPlaced(world, pos, state, livingEntity, itemStack);

        Direction facing = state.get(FACING);
        BlockPos behindPos = pos.offset(facing.getOpposite());
        BlockState behindState = world.getBlockState(behindPos);

        if (behindState.isOf(this)) {
            if (behindState.get(PILLAR_TPOSITIVE_YE) == Type.TOP && behindState.get(FACING) == facing) {
                world.setBlockState(behindPos, behindState.with(PILLAR_TPOSITIVE_YE, Type.SMOOTH), 3);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(PILLAR_TPOSITIVE_YE, FACING, WATERLOGGED);
    }

    public enum Type implements StringIdentifiable {

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
        public String asString() {
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
