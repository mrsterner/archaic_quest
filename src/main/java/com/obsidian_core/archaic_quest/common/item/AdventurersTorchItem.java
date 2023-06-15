package com.obsidian_core.archaic_quest.common.item;

import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.api.TorchInteraction;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class AdventurersTorchItem extends Item {

    private static final Map<Block, Pair<Predicate<BlockState>, Boolean>> TORCH_LIGHTERS = new HashMap<>();
    private static final Map<Block, TorchInteraction> TORCH_INTERACTABLES = new HashMap<>();

    private static final byte UNLIT = (byte) 0;
    private static final byte LIT = (byte) 1;
    private static final byte SOULFIRE = (byte) 2;


    public static void registerDefaults() {
        registerTorchLightable(Blocks.FIRE, (state) -> true, false);
        registerTorchLightable(Blocks.SOUL_FIRE, (state) -> true, true);
        registerTorchLightable(Blocks.CAMPFIRE, (state) -> state.get(CampfireBlock.LIT), false);
        registerTorchLightable(Blocks.SOUL_CAMPFIRE, (state) -> state.get(CampfireBlock.LIT), true);

        registerTorchInteraction(Blocks.CAMPFIRE, (world, state, pos, soulfire) -> {
            if (!state.get(CampfireBlock.LIT)) {
                if (soulfire) {
                    world.setBlockStateAndUpdate(pos, Blocks.SOUL_CAMPFIRE.getDefaultState().with(CampfireBlock.LIT, true));
                }
                else {
                    world.setBlockStateAndUpdate(pos, state.with(CampfireBlock.LIT, true));
                }
                return true;
            }
            return false;
        });

        registerTorchInteraction(Blocks.SOUL_CAMPFIRE, (world, state, pos, soulfire) -> {
            if (!state.get(CampfireBlock.LIT)) {
                if (soulfire) {
                    world.setBlockStateAndUpdate(pos, state.with(CampfireBlock.LIT, true));
                }
                else {
                    world.setBlockStateAndUpdate(pos, Blocks.CAMPFIRE.getDefaultState().with(CampfireBlock.LIT, true));
                }
                return true;
            }
            return false;
        });
    }

    private static final String modDataKey = "archaic_quest_data";
    private static final String litKey = "Lit";


    public AdventurersTorchItem() {
        super(new Item.Settings()
                .tab(AQCreativeTabs.ITEMS)
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @Override
    public ActionResultHolder<ItemStack> use(World world, PlayerEntity player, InteractionHand hand) {
        BlockHitResult hitResult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.WATER);

        if (world.getBlockState(hitResult.getBlockPos()).getFluidState().is(FluidTags.WATER)) {
            ItemStack itemStack = player.getStackInHand(hand);

            if (getLitState(itemStack) > 0) {

                setLit(itemStack, UNLIT);
                world.playSound(null, hitResult.getBlockPos(), SoundEvents.FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.8F, 1.0F);

                double x = hitResult.getBlockPos().getX();
                double y = hitResult.getBlockPos().getY();
                double z = hitResult.getBlockPos().getZ();

                for (int l = 0; l < 8; ++l) {
                    world.addParticle(ParticleTypes.LARGE_SMOKE, x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
                }
                return ActionResultHolder.sidedSuccess(itemStack, world.isClient());
            }
        }
        return super.use(world, player, hand);
    }

    @Override
    public ActionResult useOn(UseOnContext context) {
        ItemStack torch = context.getStackInHand();
        BlockState clickedState = context.getWorld().getBlockState(context.getBlockPos());

        if (getLitState(torch) > 0) {
            if (clickedState.getFluidState().isEmpty() && TORCH_INTERACTABLES.containsKey(clickedState.getBlock())) {
                return TORCH_INTERACTABLES.get(clickedState.getBlock()).interact(context.getWorld(), clickedState, context.getBlockPos(), getLitState(torch) == SOULFIRE)
                        ? ActionResult.SUCCESS
                        : ActionResult.FAIL;
            }
        }
        else {
            if (TORCH_LIGHTERS.containsKey(clickedState.getBlock())) {
                if (TORCH_LIGHTERS.get(clickedState.getBlock()).getFirst().test(clickedState)) {
                    setLit(torch, TORCH_LIGHTERS.get(clickedState.getBlock()).getSecond() ? SOULFIRE : LIT);
                    return ActionResult.SUCCESS;
                }
            }
        }
        return super.useOn(context);
    }

    public static void registerTorchLightable(Block block, Predicate<BlockState> predicate, boolean soulfire) {
        Objects.requireNonNull(block);
        Objects.requireNonNull(predicate);

        if (!ForgeRegistries.BLOCKS.containsValue(block)) {
            ArchaicQuest.LOGGER.warn("Attempted to register torch lighter for unregistered block! Block obj: {}", block.toString());
        }
        else if (TORCH_LIGHTERS.containsKey(block)) {
            ArchaicQuest.LOGGER.warn("Attempted to register duplicate torch lighter for block '{}'", ForgeRegistries.BLOCKS.getKey(block));
        }
        else {
            TORCH_LIGHTERS.put(block, Pair.of(predicate, soulfire));
        }
    }

    public static void registerTorchInteraction(Block block, TorchInteraction torchInteraction) {
        Objects.requireNonNull(block);
        Objects.requireNonNull(torchInteraction);

        if (!ForgeRegistries.BLOCKS.containsValue(block)) {
            ArchaicQuest.LOGGER.warn("Attempted to register torch interactor for unregistered block! Block obj: {}", block.toString());
        }
        else if (TORCH_INTERACTABLES.containsKey(block)) {
            ArchaicQuest.LOGGER.warn("Attempted to register duplicate torch interactor for block '{}'", ForgeRegistries.BLOCKS.getKey(block));
        }
        else {
            TORCH_INTERACTABLES.put(block, torchInteraction);
        }
    }

    public static byte getLitState(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof AdventurersTorchItem)) return 0;

        NbtCompound compoundTag = itemStack.getOrCreateTag();

        if (compoundTag.contains(modDataKey, Tag.TAG_COMPOUND)) {
            NbtCompound modData = compoundTag.getCompound(modDataKey);

            if (modData.contains(litKey, Tag.TAG_BYTE)) {
                return modData.getByte(litKey);
            }
        }
        return 0;
    }

    public static void setLit(ItemStack itemStack, byte type) {
        if (!(itemStack.getItem() instanceof AdventurersTorchItem)) return;
        NbtCompound compoundTag = itemStack.getOrCreateTag();

        if (compoundTag.contains(modDataKey, Tag.TAG_COMPOUND)) {
            NbtCompound modData = compoundTag.getCompound(modDataKey);
            modData.putByte(litKey, type);
        }
        else {
            NbtCompound modData = new NbtCompound();
            modData.putByte(litKey, type);
            compoundTag.put(modDataKey, modData);
        }
        itemStack.setTag(compoundTag);
    }
}
