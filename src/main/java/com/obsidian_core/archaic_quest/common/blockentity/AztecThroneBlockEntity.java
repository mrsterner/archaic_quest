package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.block.AztecThroneBlock;
import com.obsidian_core.archaic_quest.common.block.data.ThroneType;
import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class AztecThroneBlockEntity extends BlockEntity {

    private ThroneType throneType = null;

    public AztecThroneBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.AZTEC_THRONE.get(), pos, state);
    }

    @Override
    public void onLoad() {
        if (level != null) {
            BlockState state = level.getBlockState(getBlockPos());

            if (state.getBlock() instanceof AztecThroneBlock throneBlock) {
                this.throneType = throneBlock.getThroneType();
            }
        }
    }

    public void setThroneType(ThroneType type) {
        this.throneType = type;
    }

    @Nullable
    public ThroneType getThroneType() {
        return throneType;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public AABB getRenderBoundingBox() {
        BlockPos pos = getBlockPos();
        return getBlockState().getBlock() instanceof AztecThroneBlock
                ? new AABB(pos.offset(-2, 0, -2), pos.offset(2, 3, 2))
                : INFINITE_EXTENT_AABB;
    }
}
