package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

public class AztecDungeonChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {

    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

    private final ChestLidController chestLidController = new ChestLidController();
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        protected void onOpen(World level, BlockPos pos, BlockState state) {
            level.playSound(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }

        protected void onClose(World level, BlockPos pos, BlockState state) {
            level.playSound(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.CHEST_CLOSE, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }

        protected void openerCountChanged(World level, BlockPos pos, BlockState state, int openCount, int prevOpenCount) {
            AztecDungeonChestBlockEntity.this.signalOpenCount(level, pos, state, openCount, prevOpenCount);
        }

        protected boolean isOwnContainer(Player player) {
            if (!(player.containerMenu instanceof ChestMenu)) {
                return false;
            }
            else {
                Container container = ((ChestMenu) player.containerMenu).getContainer();
                return container == AztecDungeonChestBlockEntity.this;
            }
        }
    };

    public AztecDungeonChestBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.AZTEC_DUNGEON_CHEST.get(), pos, state);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.chest");
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, items);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        if (!trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, items);
        }
    }

    public static void lidAnimateTick(World level, BlockPos pos, BlockState state, AztecDungeonChestBlockEntity chest) {
        chest.chestLidController.tickLid();
    }

    @Override
    public boolean triggerEvent(int id, int data) {
        if (id == 1) {
            chestLidController.shouldBeOpen(data > 0);
            return true;
        }
        else {
            return super.triggerEvent(id, data);
        }
    }

    @Override
    public void startOpen(Player player) {
        if (!remove && !player.isSpectator()) {
            openersCounter.incrementOpeners(player, level, getBlockPos(), getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!remove && !player.isSpectator()) {
            openersCounter.decrementOpeners(player, level, getBlockPos(), getBlockState());
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemList) {
        items = itemList;
    }

    @Override
    public float getOpenNess(float f) {
        return chestLidController.getOpenness(f);
    }

    public static int getOpenCount(BlockGetter level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.hasBlockEntity()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof AztecDungeonChestBlockEntity chest) {
                return chest.openersCounter.getOpenerCount();
            }
        }
        return 0;
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return ChestMenu.threeRows(id, inventory, this);
    }

    private LazyOptional<IItemHandlerModifiable> chestHandler;

    @Override
    @SuppressWarnings("deprecation")
    public void setBlockState(BlockState state) {
        super.setBlockState(state);

        if (chestHandler != null) {
            LazyOptional<?> oldHandler = chestHandler;
            chestHandler = null;
            oldHandler.invalidate();
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (!remove && cap == ForgeCapabilities.ITEM_HANDLER) {
            if (chestHandler == null)
                chestHandler = LazyOptional.of(this::createHandler);
            return chestHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    private IItemHandlerModifiable createHandler() {
        BlockState state = this.getBlockState();
        if (!(state.getBlock() instanceof ChestBlock)) {
            return new InvWrapper(this);
        }
        Container inv = ChestBlock.getContainer((ChestBlock) state.getBlock(), state, level, getBlockPos(), true);
        return new InvWrapper(inv == null ? this : inv);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();

        if (chestHandler != null) {
            chestHandler.invalidate();
            chestHandler = null;
        }
    }

    public void recheckOpen() {
        if (!remove) {
            openersCounter.recheckOpeners(level, getBlockPos(), getBlockState());
        }
    }

    protected void signalOpenCount(World level, BlockPos pos, BlockState state, int id, int data) {
        Block block = state.getBlock();
        level.blockEvent(pos, block, 1, data);
    }
}
