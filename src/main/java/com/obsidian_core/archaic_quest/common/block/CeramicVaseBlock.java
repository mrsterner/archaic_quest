package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.blockentity.VaseBlockEntity;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
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

    public CeramicVaseBlock(Properties properties) {
        super(properties.dynamicShape()
                .offsetType((state) -> state.getValue(CeramicVaseBlock.CENTERED) ? BlockBehaviour.OffsetType.NONE : BlockBehaviour.OffsetType.XZ));
        registerDefaultState(stateDefinition.any().setValue(CENTERED, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(level, pos);
        return shape.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Player player = context.getPlayer();
        boolean center = false;

        if (player != null)
            center = player.isShiftKeyDown();

        return this.defaultBlockState().setValue(CENTERED, center);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VaseBlockEntity(pos, state);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, World level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (level.getExistingBlockEntity(pos) instanceof VaseBlockEntity vaseBlockEntity) {
            vaseBlockEntity.dropLootTable(player);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(CENTERED);
    }
}
