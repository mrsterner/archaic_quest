package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.block.data.DungeonDoorType;
import com.obsidian_core.archaic_quest.common.network.NetworkHelper;
import com.obsidian_core.archaic_quest.registry.AQBlockEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtTypes;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AztecDungeonDoorBlockEntity extends BlockEntity {

    private DoorState doorState = DoorState.STAND_BY;
    private final int minDoorPos = 0;
    private final int maxDoorPos = 60;
    private int doorPosition = minDoorPos;
    private DungeonDoorType doorType;

    public AztecDungeonDoorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public AztecDungeonDoorBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntityTypes.AZTEC_DUNGEON_DOOR, pos, state);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (world != null) {
            if (getCachedState().getBlock() instanceof AztecDungeonDoorBlock) {
                doorType = ((AztecDungeonDoorBlock) getCachedState().getBlock()).getDoorType();
            }
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, AztecDungeonDoorBlockEntity dungeonDoor) {
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
        if (!world.isClient()) {
            switch (getCachedState().get(AztecDungeonDoorBlock.FACING)) {
                case NORTH, SOUTH -> {
                    for (BlockPos pos : BlockPos.iterate(getPos().west(), getPos().east().up(2))) {
                        world.setBlockState(pos, world.getBlockState(pos).with(AztecDungeonDoorBlock.IS_OPEN, isOpen), 2);
                    }
                }
                case EAST, WEST -> {
                    for (BlockPos pos : BlockPos.iterate(getPos().north(), getPos().south().up(2))) {
                        world.setBlockState(pos, world.getBlockState(pos).with(AztecDungeonDoorBlock.IS_OPEN, isOpen), 2);
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
        if (world != null && !world.isClient()) {
            NetworkHelper.updateDoorState((ServerWorld) world, getPos(), doorState);
        }
    }

    @Override
    public void readNbt(NbtCompound compoundTag) {
        super.readNbt(compoundTag);

        if (compoundTag.contains("DoorPosition", NbtElement.NUMBER_TYPE)) {
            doorPosition = MathHelper.clamp(compoundTag.getInt("DoorPosition"), minDoorPos, maxDoorPos);
        }

        if (compoundTag.contains("DoorState", NbtElement.NUMBER_TYPE)) {
            int stateId = compoundTag.getInt("DoorState");
            DoorState doorState = DoorState.byId(stateId);
            setDoorState(doorState == null ? DoorState.STAND_BY : doorState);
        }
    }

    @Override
    public void writeNbt(NbtCompound compoundTag) {
        super.writeNbt(compoundTag);

        compoundTag.putInt("DoorPosition", doorPosition);
        compoundTag.putInt("DoorState", getDoorState().ordinal());
    }

    private void writeUpdateData(NbtCompound compoundTag) {
        compoundTag.putInt("DoorPosition", doorPosition);
        compoundTag.putInt("DoorState", getDoorState().ordinal());
    }


    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound compoundTag = new NbtCompound();
        writeUpdateData(compoundTag);
        return compoundTag;
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public void handleUpdateTag(NbtCompound tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if(world.isClient()) {
            super.handleUpdateTag(pkt.getTag());

            NbtCompound compoundTag = pkt.getTag();

            if (compoundTag == null)
                return;

            if (compoundTag.contains("DoorPosition", NbtElement.INT_TYPE)) {
                doorPosition = MathHelper.clamp(compoundTag.getInt("DoorPosition"), minDoorPos, maxDoorPos);
            }
            if (compoundTag.contains("DoorState", NbtElement.INT_TYPE)) {
                int stateId = compoundTag.getInt("DoorState");
                DoorState doorState = DoorState.byId(stateId);
                setDoorState(doorState == null ? DoorState.STAND_BY : doorState);
            }
        }
    }

    @Override
    public boolean copyItemDataRequiresOperator() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Box getRenderBoundingBox() {
        BlockPos pos = getPos();
        return getCachedState().getBlock() instanceof AztecDungeonDoorBlock
                ? new Box(pos.offset(-2, 0, -2), pos.offset(2, 3, 2))
                : INFINITE_EXTENT_Box;
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
