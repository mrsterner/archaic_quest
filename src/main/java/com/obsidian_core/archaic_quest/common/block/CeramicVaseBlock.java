package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.VaseBlockEntity;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.world.BlockGetter;
import net.minecraft.world.world.World;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.EntityBlock;
import net.minecraft.world.world.block.entity.BeaconBlockEntity;
import net.minecraft.world.world.block.entity.BlockEntity;
import net.minecraft.world.world.block.state.BlockBehaviour;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.BooleanProperty;
import net.minecraft.world.world.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class CeramicVaseBlock extends Block implements EntityBlock {

    public static final BooleanProperty CENTERED = BooleanProperty.create("centered");

    private static final VoxelShape shape = Shapes.or(
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 9.0D, 11.0D),
            Block.box(4.0D, 1.0D, 4.0D, 12.0D, 6.0D, 12.0D)
    );

    public CeramicVaseBlock(Settings properties) {
        super(properties.dynamicShape()
                .offsetType((state) -> state.get(CeramicVaseBlock.CENTERED) ? BlockBehaviour.OffsetType.NONE : BlockBehaviour.OffsetType.XZ));
        setDefaultState(getDefaultState().with(CENTERED, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Vec3d vec3 = state.getOffset(world, pos);
        return shape.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        PlayerEntity player = context.getPlayer();
        boolean center = false;

        if (player != null)
            center = player.isShiftKeyDown();

        return this.getDefaultState().with(CENTERED, center);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VaseBlockEntity(pos, state);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
        if (world.getExistingBlockEntity(pos) instanceof VaseBlockEntity vaseBlockEntity) {
            vaseBlockEntity.dropLootTable(player);
        }
        return super.onDestroyedByPlayer(state, world, pos, player, willHarvest, fluid);
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(CENTERED);
    }
}
