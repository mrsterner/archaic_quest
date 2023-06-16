package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.block.AztecThroneBlock;
import com.obsidian_core.archaic_quest.common.block.data.ThroneType;
import com.obsidian_core.archaic_quest.registry.AQBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;


public class AztecThroneBlockEntity extends BlockEntity {

    private ThroneType throneType = null;

    public AztecThroneBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntityTypes.AZTEC_THRONE, pos, state);
    }

    @Override
    public void onLoad() {
        if (world != null) {
            BlockState state = world.getBlockState(getPos());

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
    protected void saveAdditional(NbtCompound compoundTag) {
        super.saveAdditional(compoundTag);

        if (throneType != null) {
            compoundTag.putString("ThroneType", throneType.getName());
        }
    }

    @Override
    public void load(NbtCompound compoundTag) {
        super.load(compoundTag);

        if (compoundTag.contains("ThroneType", Tag.TAG_STRING)) {
            ThroneType type = ThroneType.getFromName(compoundTag.getString("ThroneType"));

            if (type != null)
                throneType = type;
        }
    }
}
