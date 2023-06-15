package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.block.AztecThroneBlock;
import com.obsidian_core.archaic_quest.common.block.data.ThroneType;
import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.world.block.entity.BlockEntity;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.phys.Box;
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
        if (world != null) {
            BlockState state = world.getBlockState(getBlockPos());

            if (state.getBlock() instanceof AztecThroneBlock throneBlock) {
                setThroneType(throneBlock.getThroneType());
            }
        }
    }

    public void setThroneType(ThroneType type) {
        throneType = type;
    }

    @Nullable
    public ThroneType getThroneType() {
        return throneType;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Box getRenderBoundingBox() {
        BlockPos pos = getBlockPos();
        return getBlockState().getBlock() instanceof AztecThroneBlock
                ? new Box(pos.offset(-2, 0, -2), pos.offset(2, 3, 2))
                : INFINITE_EXTENT_Box;
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        if (throneType != null) {
            compoundTag.putString("ThroneType", throneType.getName());
        }
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);

        if (compoundTag.contains("ThroneType", Tag.TAG_STRING)) {
            ThroneType type = ThroneType.getFromName(compoundTag.getString("ThroneType"));

            if (type != null)
                throneType = type;
        }
    }
}
