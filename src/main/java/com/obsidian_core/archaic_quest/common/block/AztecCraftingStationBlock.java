package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.tile.AztecCraftingStationTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AztecCraftingStationBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    // Whether this block state is the "master" block or a sub-block for collision purposes.
    public static final EnumProperty<BlockType> BLOCK_TYPE = EnumProperty.create("block_type", BlockType.class);

    private static final VoxelShape[] dummyBlockCollisions = new VoxelShape[] {
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
    };


    public AztecCraftingStationBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BLOCK_TYPE, BlockType.MASTER));
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        else {
            this.openContainer(world, pos, playerEntity);
            return ActionResultType.CONSUME;
        }
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        BlockType type = state.getValue(BLOCK_TYPE);

        if (type == BlockType.MASTER) {
            return VoxelShapes.block();
        }
        else {
            int i = state.getValue(FACING).get2DDataValue();
            return dummyBlockCollisions[type == BlockType.LEFT ? i : i + 4];
        }
    }

    protected void openContainer(World world, BlockPos pos, PlayerEntity playerEntity) {
        TileEntity tileEntity = world.getBlockEntity(pos);

        if (tileEntity instanceof AztecCraftingStationTileEntity) {
            playerEntity.openMenu((INamedContainerProvider)tileEntity);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext useContext) {
        return this.defaultBlockState().setValue(FACING, useContext.getHorizontalDirection()).setValue(BLOCK_TYPE, BlockType.MASTER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean b) {
        super.onPlace(state, world, pos, oldState, b);
    }

    @Nullable
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AztecCraftingStationTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.getValue(BLOCK_TYPE) == BlockType.MASTER;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, IBlockReader world, BlockPos pos, PathType pathType) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, BLOCK_TYPE);
    }

    private enum BlockType implements IStringSerializable {
        MASTER("master"),
        LEFT("left"),
        RIGHT("right");

        BlockType(String name) {
            this.name = name;
        }

        private final String name;

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
