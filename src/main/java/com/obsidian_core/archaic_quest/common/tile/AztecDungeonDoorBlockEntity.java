package com.obsidian_core.archaic_quest.common.tile;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.block.data.DungeonDoorType;
import com.obsidian_core.archaic_quest.common.network.NetworkHelper;
import com.obsidian_core.archaic_quest.common.register.AQBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class AztecDungeonDoorBlockEntity extends BlockEntity {

    private DoorState doorState = DoorState.STAND_BY;
    private final int minDoorPos = 0;
    private final int maxDoorPos = 60;
    private int doorPosition = minDoorPos;
    private DungeonDoorType doorType;


    public AztecDungeonDoorBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.AZTEC_DUNGEON_DOOR.get(), pos, state);
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

    public static void tick(Level level, BlockPos pos, BlockState state, AztecDungeonDoorBlockEntity dungeonDoor) {
        DungeonDoorType doorType = dungeonDoor.doorType;
        DoorState doorState = dungeonDoor.doorState;

        if (doorType == null || doorType.isFrame())
            return;

        if (doorState == DoorState.STAND_BY)
            return;

        if (doorState == DoorState.OPENING) {
            if (dungeonDoor.doorPosition < 60) {
                ++dungeonDoor.doorPosition;
            }
            else {
                dungeonDoor.setDoorState(DoorState.STAND_BY);
                dungeonDoor.toggleDoorBlocks(true);
            }
        }
        else if (doorState == DoorState.CLOSING) {
            if (dungeonDoor.doorPosition > 0) {
                --dungeonDoor.doorPosition;
            }
            else {
                dungeonDoor.setDoorState(DoorState.STAND_BY);
                dungeonDoor.toggleDoorBlocks(false);
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
                case NORTH, SOUTH -> {
                    for (BlockPos pos : BlockPos.betweenClosed(getBlockPos().west(), getBlockPos().east().above(2))) {
                        level.setBlock(pos, level.getBlockState(pos).setValue(AztecDungeonDoorBlock.IS_OPEN, isOpen), 2);
                    }
                }
                case EAST, WEST -> {
                    for (BlockPos pos : BlockPos.betweenClosed(getBlockPos().north(), getBlockPos().south().above(2))) {
                        level.setBlock(pos, level.getBlockState(pos).setValue(AztecDungeonDoorBlock.IS_OPEN, isOpen), 2);
                    }
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
            NetworkHelper.updateDoorState((ServerLevel) level, getBlockPos(), doorState);
        }
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);

        if (compoundTag.contains("DoorPosition", Tag.TAG_ANY_NUMERIC)) {
            doorPosition = Mth.clamp(compoundTag.getInt("DoorPosition"), minDoorPos, maxDoorPos);
        }

        if (compoundTag.contains("DoorState", Tag.TAG_ANY_NUMERIC)) {
            int stateId = compoundTag.getInt("DoorState");
            DoorState doorState = DoorState.byId(stateId);
            setDoorState(doorState == null ? DoorState.STAND_BY : doorState);
        }
    }

    @Override
    public void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        compoundTag.putInt("DoorPosition", doorPosition);
        compoundTag.putInt("DoorState", getDoorState().ordinal());
    }

    private void writeUpdateData(CompoundTag compoundTag) {
        compoundTag.putInt("DoorPosition", doorPosition);
        compoundTag.putInt("DoorState", getDoorState().ordinal());
    }


    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        writeUpdateData(compoundTag);
        return compoundTag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if(level.isClientSide) {
            super.handleUpdateTag(pkt.getTag());

            CompoundTag compoundTag = pkt.getTag();

            if (compoundTag == null)
                return;

            if (compoundTag.contains("DoorPosition", Tag.TAG_ANY_NUMERIC)) {
                doorPosition = Mth.clamp(compoundTag.getInt("DoorPosition"), minDoorPos, maxDoorPos);
            }
            if (compoundTag.contains("DoorState", Tag.TAG_ANY_NUMERIC)) {
                int stateId = compoundTag.getInt("DoorState");
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
    public AABB getRenderBoundingBox() {
        BlockPos pos = getBlockPos();
        return getBlockState().getBlock() instanceof AztecDungeonDoorBlock
                ? new AABB(pos.offset(-2, 0, -2), pos.offset(2, 3, 2))
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
