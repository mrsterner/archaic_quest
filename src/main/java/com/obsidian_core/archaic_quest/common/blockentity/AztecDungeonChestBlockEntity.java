package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AztecDungeonChestBlockEntity extends LootableContainerBlockEntity implements LidOpenable {

    private DefaultedList<ItemStack> items = DefaultedList.ofSize(27, ItemStack.EMPTY);

    private final ChestLidAnimator chestLidController = new ChestLidAnimator();
    private final ViewerCountManager openersCounter = new ViewerCountManager() {
        @Override
        protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
            world.playSound(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);

        }

        @Override
        protected void onContainerClose(World world, BlockPos pos, BlockState state) {
            world.playSound(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);

        }

        @Override
        protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            AztecDungeonChestBlockEntity.this.signalOpenCount(world, pos, state, newViewerCount, oldViewerCount);

        }

        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            if (!(player.currentScreenHandler instanceof GenericContainerScreenHandler)) {
                return false;
            }
            else {
                Inventory container = ((GenericContainerScreenHandler) player.currentScreenHandler).getInventory();
                return container == AztecDungeonChestBlockEntity.this;
            }
        }
    };

    public AztecDungeonChestBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.AZTEC_DUNGEON_CHEST, pos, state);
    }

    @Override
    public int size() {
        return 27;
    }


    @Override
    protected Text getContainerName() {
        return Text.translatable("container.chest");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return null;
    }

    @Override
    public void readNbt(NbtCompound compoundTag) {
        super.readNbt(compoundTag);
        items = DefaultedList.ofSize(size(), ItemStack.EMPTY);

        if (!this.deserializeLootTable(compoundTag)) {
            Inventories.readNbt(compoundTag, items);
        }
    }

    @Override
    protected void writeNbt(NbtCompound compoundTag) {
        super.writeNbt(compoundTag);

        if (!serializeLootTable(compoundTag)) {
            Inventories.writeNbt(compoundTag, items);
        }
    }

    public static void lidAnimateTick(World world, BlockPos pos, BlockState state, AztecDungeonChestBlockEntity chest) {
        chest.chestLidController.step();
    }

    @Override
    public boolean onSyncedBlockEvent(int id, int data) {
        if (id == 1) {
            chestLidController.setOpen(data > 0);
            return true;
        }
        else {
            return super.onSyncedBlockEvent(id, data);
        }
    }

    @Override
    public void onOpen(PlayerEntity player) {
        if (!remove && !player.isSpectator()) {
            openersCounter.openContainer(player, world, getPos(), getCachedState());
        }
    }

    @Override
    public void onClose(PlayerEntity player) {
        if (!remove && !player.isSpectator()) {
            openersCounter.closeContainer(player, world, getPos(), getCachedState());
        }
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return items;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> itemList) {
        items = itemList;
    }

    @Override
    public float getAnimationProgress(float tickDelta) {
        return chestLidController.getProgress(tickDelta);
    }

    @Override
    public static int getPlayersLookingInChestCount(BlockView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.hasBlockEntity()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof AztecDungeonChestBlockEntity chest) {
                return chest.openersCounter.getViewerCount();
            }
        }
        return 0;
    }

    protected ScreenHandler createMenu(int id, PlayerInventory inventory) {
        return GenericContainerScreenHandler.createGeneric9x3(id, inventory, this);
    }

    private LazyOptional<IItemHandlerModifiable> chestHandler;

    @Override
    @SuppressWarnings("deprecation")
    public void setCachedState(BlockState state) {
        super.setCachedState(state);

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
        Inventory inv = ChestBlock.getInventory((ChestBlock) state.getBlock(), state, world, getPos(), true);
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
            openersCounter.updateViewerCount(world, getPos(), getCachedState());
        }
    }

    protected void signalOpenCount(World world, BlockPos pos, BlockState state, int id, int data) {
        Block block = state.getBlock();
        world.addSyncedBlockEvent(pos, block, 1, data);
    }
}
