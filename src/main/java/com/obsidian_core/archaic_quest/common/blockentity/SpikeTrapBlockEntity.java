package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.misc.AQDamageSources;
import com.obsidian_core.archaic_quest.registry.AQBlockEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


import static com.obsidian_core.archaic_quest.common.block.SpikeTrapBlock.Mode;

import java.util.List;

public class SpikeTrapBlockEntity extends BlockEntity {

    /** The collision box up the trap to check for players to damage, when trap is active. */
    private Box effectBox = null;
    private boolean active;

    private final int maxSpikeRise = 3;
    private int spikeRise = 0;

    private Mode mode = Mode.NORMAL;


    public SpikeTrapBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntityTypes.SPIKE_TRAP, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, SpikeTrapBlockEntity trap) {
        // Tick damage
        if (trap.active) {
            // Rise the spikes
            if (trap.spikeRise < trap.maxSpikeRise)
                ++trap.spikeRise;

            // Create collision box if needed
            if (trap.effectBox == null) {
                trap.effectBox = new Box(pos.up()).expand(0.0D, 0.75D, 0.0D);
            }
            List<LivingEntity> entities = world.getNonSpectatingEntities(LivingEntity.class, trap.effectBox);

            // Affect/damage entities inside the spikes
            for (LivingEntity entity : entities) {
                entity.damage(AQDamageSources.SPIKE_TRAP, 2.0F);
                entity.slowMovement(state, new Vec3d(0.25D, 0.05F, 0.25D));
            }
        }
        else {
            // Remove collision box if needed
            if (trap.effectBox != null)
                trap.effectBox = null;

            // Lower the spikes
            if (trap.spikeRise > 0)
                --trap.spikeRise;
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public int getSpikeRise() {
        return spikeRise;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public void readNbt(NbtCompound compoundTag) {
        super.readNbt(compoundTag);

        if (compoundTag.contains("Active", NbtElement.BYTE_TYPE)) {
            active = compoundTag.getBoolean("Active");
        }

        if (compoundTag.contains("SpikeRise", NbtElement.INT_TYPE)) {
            spikeRise = MathHelper.clamp(compoundTag.getInt("SpikeRise"), 0, maxSpikeRise);
        }

        if (compoundTag.contains("Mode", NbtElement.STRING_TYPE)) {
            Mode mode = Mode.getFromName(compoundTag.getString("Mode"));
            this.mode = mode == null ? Mode.NORMAL : mode;
        }
    }

    @Override
    public void writeNbt(NbtCompound compoundTag) {
        super.writeNbt(compoundTag);

        compoundTag.putBoolean("Active", active);
        compoundTag.putInt("SpikeRise", spikeRise);
        compoundTag.putString("Mode", mode.asString());
    }

    private void writeUpdateData(NbtCompound compoundTag) {
        compoundTag.putBoolean("Active", active);
        compoundTag.putInt("SpikeRise", spikeRise);
        compoundTag.putString("Mode", mode.asString());
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

        if (tag.contains("Active", NbtElement.BYTE_TYPE)) {
            active = tag.getBoolean("Active");
        }
        if (tag.contains("SpikeRise", NbtElement.INT_TYPE)) {
            spikeRise = MathHelper.clamp(tag.getInt("SpikeRise"), 0, maxSpikeRise);
        }
        if (tag.contains("Mode", NbtElement.STRING_TYPE)) {
            Mode mode = Mode.getFromName(tag.getString("Mode"));
            this.mode = mode == null ? Mode.NORMAL : mode;
        }
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if(world.isClient()) {
            if (pkt.getTag() != null)
                handleUpdateTag(pkt.getTag());
        }
    }

    @Override
    public boolean copyItemDataRequiresOperator() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Box getRenderBoundingBox() {
        BlockPos pos = getBlockPos();
        return getBlockState().getBlock() instanceof AztecDungeonDoorBlock
                ? new Box(pos.offset(0, -1, 0), pos.offset(0, 2, 0))
                : INFINITE_EXTENT_Box;
    }
}
