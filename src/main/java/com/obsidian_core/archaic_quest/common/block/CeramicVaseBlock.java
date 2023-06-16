package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.VaseBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CeramicVaseBlock extends Block implements BlockEntityProvider {

    public static final BooleanProperty CENTERED = BooleanProperty.of("centered");

    private static final VoxelShape shape = VoxelShapes.union(
            Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 9.0D, 11.0D),
            Block.createCuboidShape(4.0D, 1.0D, 4.0D, 12.0D, 6.0D, 12.0D)
    );

    public CeramicVaseBlock(Settings properties) {
        super(properties.dynamicBounds()
                .offsetType((state) -> state.get(CeramicVaseBlock.CENTERED) ? AbstractBlock.OffsetType.NONE : AbstractBlock.OffsetType.XZ));
        setDefaultState(getDefaultState().with(CENTERED, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3 = state.getModelOffset(world, pos);
        return shape.offset(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        PlayerEntity player = context.getPlayer();
        boolean center = false;

        if (player != null)
            center = player.isSneaking();

        return this.getDefaultState().with(CENTERED, center);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VaseBlockEntity(pos, state);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
        if (world.getBlockEntity(pos) instanceof VaseBlockEntity vaseBlockEntity) {
            vaseBlockEntity.dropLootTable(player);
        }
        return super.onDestroyedByPlayer(state, world, pos, player, willHarvest, fluid);
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(CENTERED);
    }
}
