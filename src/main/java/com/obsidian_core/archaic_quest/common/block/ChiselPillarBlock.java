package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndRodBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ChiselPillarBlock extends Block {

    public static final EnumProperty<Type> PILLAR_TYPE = EnumProperty.create("pillar_type", Type.class);
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public ChiselPillarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PILLAR_TYPE, Type.BOTTOM));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Type type = Type.BOTTOM;
        Direction facing = context.getClickedFace();

        if (context.getPlayer() != null) {
            World world = context.getPlayer().level;
            BlockState lookAtState = world.getBlockState(context.getClickedPos());

            if (lookAtState.is(this)) {
                type = Type.TOP;
                facing = lookAtState.getValue(FACING);

                if (world.getBlockState(context.getClickedPos().relative(context.getClickedFace())).is(this)) {
                    type = Type.SMOOTH;
                }
            }
        }
        return this.defaultBlockState().setValue(FACING, facing).setValue(PILLAR_TYPE, type);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, IBlockReader world, BlockPos pos, PathType pathType) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(PILLAR_TYPE, FACING);
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

        /*
        public static Type chiselCycle(Type type) {
            if (type == BOTTOM || type == TOP)
                return type;

            List<Type> types = new ArrayList<>();
            Collections.addAll(types, values());
            types.remove(BOTTOM);
            types.remove(TOP);

        }
         */
    }
}
