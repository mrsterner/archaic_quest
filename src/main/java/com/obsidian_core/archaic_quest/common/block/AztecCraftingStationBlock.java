package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.tile.AztecCraftingStationTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class AztecCraftingStationBlock extends ContainerBlock {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty MASTER = BooleanProperty.create("master");

    public AztecCraftingStationBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(MASTER, false));
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

    protected void openContainer(World world, BlockPos pos, PlayerEntity playerEntity) {
        TileEntity tileEntity = world.getBlockEntity(pos);

        if (tileEntity instanceof AztecCraftingStationTileEntity) {
            playerEntity.openMenu((INamedContainerProvider)tileEntity);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext useContext) {
        return this.defaultBlockState().setValue(FACING, useContext.getHorizontalDirection()).setValue(MASTER, true);
    }

    @Nullable
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (state.getBlock() == this && state.getValue(MASTER)) {
            return this.newBlockEntity(world);
        }
        return null;
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new AztecCraftingStationTileEntity();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return state.getValue(MASTER) ? BlockRenderType.MODEL : BlockRenderType.INVISIBLE;
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
        stateBuilder.add(FACING, MASTER);
    }
}
