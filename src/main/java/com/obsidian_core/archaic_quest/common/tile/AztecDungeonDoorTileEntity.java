package com.obsidian_core.archaic_quest.common.tile;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.block.data.DungeonDoorType;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.network.NetworkHelper;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.register.AQTileEntities;
import net.minecraft.block.*;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class AztecDungeonDoorTileEntity extends TileEntity implements ITickableTileEntity {

    private DoorState doorState = DoorState.STAND_BY;
    private final int minDoorPos = 0;
    private final int maxDoorPos = 60;
    private int doorPosition = minDoorPos;
    private DungeonDoorType doorType;


    public AztecDungeonDoorTileEntity() {
        super(AQTileEntities.AZTEC_DUNGEON_DOOR.get());
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (level != null) {
            if (getBlockState().getBlock() instanceof AztecDungeonDoorBlock) {
                doorType = ((AztecDungeonDoorBlock) getBlockState().getBlock()).getDoorType();
            }
        }
    }

    @Override
    public void tick() {
        if (doorType == null || doorType.isFrame())
            return;

        if (level != null) {
            if (doorState == DoorState.STAND_BY)
                return;

            if (doorState == DoorState.OPENING) {
                if (doorPosition < 60) {
                    ++doorPosition;
                }
                else {
                    setDoorState(DoorState.STAND_BY);
                    toggleDoorBlocks(true);
                }
            }
            else if (doorState == DoorState.CLOSING) {
                if (doorPosition > 0) {
                    --doorPosition;
                }
                else {
                    setDoorState(DoorState.STAND_BY);
                    toggleDoorBlocks(false);
                }
            }
        }
    }

    /**
     * Toggles this dungeon door tile entity's
     * physical blocks' collision.
     *
     * @param isOpen Whether the door should be opened or closed.
     */
    @SuppressWarnings("ConstantConditions")
    private void toggleDoorBlocks(boolean isOpen) {
        if (!level.isClientSide) {
            switch (getBlockState().getValue(AztecDungeonDoorBlock.FACING)) {
                case NORTH:
                case SOUTH:
                {
                    for (BlockPos pos : BlockPos.betweenClosed(getBlockPos().west(), getBlockPos().east().above(2))) {
                        level.setBlock(pos, level.getBlockState(pos).setValue(AztecDungeonDoorBlock.IS_OPEN, isOpen), 2);
                    }
                    break;
                }
                case EAST:
                case WEST:
                {
                    for (BlockPos pos : BlockPos.betweenClosed(getBlockPos().north(), getBlockPos().south().above(2))) {
                        level.setBlock(pos, level.getBlockState(pos).setValue(AztecDungeonDoorBlock.IS_OPEN, isOpen), 2);
                    }
                    break;
                }
            }
        }
    }

    public DungeonDoorType getDoorType() {
        return doorType;
    }

    public boolean isOpen() {
        return doorPosition >= maxDoorPos;
    }

    public boolean isClosed() {
        return doorPosition <= minDoorPos;
    }

    public DoorState getDoorState() {
        return doorState;
    }

    public int getDoorPosition() {
        return doorPosition;
    }

    public void setDoorState(DoorState doorState) {
        this.doorState = doorState;
    }

    public void sendDoorStateUpdate() {
        if (level != null && !level.isClientSide) {
            NetworkHelper.updateDoorState((ServerWorld) level, getBlockPos(), doorState);
        }
    }

    @Override
    public void load(BlockState state, CompoundNBT compoundNBT) {
        super.load(state, compoundNBT);

        if (compoundNBT.contains("DoorPosition", Constants.NBT.TAG_ANY_NUMERIC)) {
            doorPosition = MathHelper.clamp(compoundNBT.getInt("DoorPosition"), minDoorPos, maxDoorPos);
        }

        if (compoundNBT.contains("DoorState", Constants.NBT.TAG_ANY_NUMERIC)) {
            int stateId = compoundNBT.getInt("DoorState");
            DoorState doorState = DoorState.byId(stateId);
            setDoorState(doorState == null ? DoorState.STAND_BY : doorState);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compoundNBT) {
        compoundNBT = super.save(compoundNBT);

        compoundNBT.putInt("DoorPosition", doorPosition);
        compoundNBT.putInt("DoorState", getDoorState().ordinal());

        return compoundNBT;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getBlockPos(), 0, getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        if(level.isClientSide) {
            super.handleUpdateTag(this.getBlockState(), pkt.getTag());

            CompoundNBT tag = pkt.getTag();

            if (tag.contains("DoorPosition", Constants.NBT.TAG_ANY_NUMERIC)) {
                doorPosition = MathHelper.clamp(tag.getInt("DoorPosition"), minDoorPos, maxDoorPos);
            }
            if (tag.contains("DoorState", Constants.NBT.TAG_ANY_NUMERIC)) {
                int stateId = tag.getInt("DoorState");
                DoorState doorState = DoorState.byId(stateId);
                setDoorState(doorState == null ? DoorState.STAND_BY : doorState);
            }
        }
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public double getViewDistance() {
        return 128.0D;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        BlockPos pos = getBlockPos();
        return getBlockState().getBlock() instanceof AztecDungeonDoorBlock
                ? new AxisAlignedBB(pos.offset(-2, 0, -2), pos.offset(2, 3, 2))
                : INFINITE_EXTENT_AABB;
    }


    public enum DoorState {
        OPENING,
        CLOSING,
        STAND_BY;

        @Nullable
        public static DoorState byId(int id) {
            if (id > values().length || id < 0) {
                return null;
            }
            return values()[id];
        }
    }
}
