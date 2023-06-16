package com.obsidian_core.archaic_quest.common.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FoundryBlock extends Block implements BlockEntityProvider {

    public FoundryBlock() {
        super(Settings.of(Material.STONE)
                .requiresTool()
                .ticksRandomly()
                .sounds(BlockSoundGroup.STONE)
                .strength(2.0F));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> blockEntityType) {
        return null;
        //return (lvl, blockState, pos, blockEntity) -> FoundryBlockEntity.tick(lvl, blockState, pos, (FoundryBlockEntity) blockEntity);
    }
}
