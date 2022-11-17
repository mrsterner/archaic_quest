package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ChiselPillarBlock extends Block implements IWaterLoggable {

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
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH:
            case SOUTH:
                return SHAPES[0];
            case EAST:
            case WEST:
                return SHAPES[1];
            case UP:
            default:
                return SHAPES[2];
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Type type = Type.BOTTOM;
        Direction clickedFace = context.getClickedFace();
        BlockPos clickedPos = context.getClickedPos();
        World world = context.getLevel();
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
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(PILLAR_TYPE, FACING, WATERLOGGED);
    }

    public enum Type implements IStringSerializable {

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
