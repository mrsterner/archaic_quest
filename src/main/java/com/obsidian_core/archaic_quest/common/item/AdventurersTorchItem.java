package com.obsidian_core.archaic_quest.common.item;

import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.api.TorchInteraction;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerWorld;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.ForgeRegistries;

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
        registerTorchLightable(Blocks.CAMPFIRE, (state) -> state.getValue(CampfireBlock.LIT), false);
        registerTorchLightable(Blocks.SOUL_CAMPFIRE, (state) -> state.getValue(CampfireBlock.LIT), true);

        registerTorchInteraction(Blocks.CAMPFIRE, (level, state, pos, soulfire) -> {
            if (!state.getValue(CampfireBlock.LIT)) {
                if (soulfire) {
                    level.setBlockAndUpdate(pos, Blocks.SOUL_CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, true));
                }
                else {
                    level.setBlockAndUpdate(pos, state.setValue(CampfireBlock.LIT, true));
                }
                return true;
            }
            return false;
        });

        registerTorchInteraction(Blocks.SOUL_CAMPFIRE, (level, state, pos, soulfire) -> {
            if (!state.getValue(CampfireBlock.LIT)) {
                if (soulfire) {
                    level.setBlockAndUpdate(pos, state.setValue(CampfireBlock.LIT, true));
                }
                else {
                    level.setBlockAndUpdate(pos, Blocks.CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, true));
                }
                return true;
            }
            return false;
        });
    }

    private static final String modDataKey = "archaic_quest_data";
    private static final String litKey = "Lit";


    public AdventurersTorchItem() {
        super(new Item.Properties()
                .tab(AQCreativeTabs.ITEMS)
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(World level, Player player, InteractionHand hand) {
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.WATER);

        if (level.getBlockState(hitResult.getBlockPos()).getFluidState().is(FluidTags.WATER)) {
            ItemStack itemStack = player.getItemInHand(hand);

            if (getLitState(itemStack) > 0) {

                setLit(itemStack, UNLIT);
                level.playSound(null, hitResult.getBlockPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.8F, 1.0F);

                double x = hitResult.getBlockPos().getX();
                double y = hitResult.getBlockPos().getY();
                double z = hitResult.getBlockPos().getZ();

                for (int l = 0; l < 8; ++l) {
                    level.addParticle(ParticleTypes.LARGE_SMOKE, x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
                }
                return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
            }
        }
        return super.use(level, player, hand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack torch = context.getItemInHand();
        BlockState clickedState = context.getWorld().getBlockState(context.getClickedPos());

        if (getLitState(torch) > 0) {
            if (clickedState.getFluidState().isEmpty() && TORCH_INTERACTABLES.containsKey(clickedState.getBlock())) {
                return TORCH_INTERACTABLES.get(clickedState.getBlock()).interact(context.getWorld(), clickedState, context.getClickedPos(), getLitState(torch) == SOULFIRE)
                        ? InteractionResult.SUCCESS
                        : InteractionResult.FAIL;
            }
        }
        else {
            if (TORCH_LIGHTERS.containsKey(clickedState.getBlock())) {
                if (TORCH_LIGHTERS.get(clickedState.getBlock()).getFirst().test(clickedState)) {
                    setLit(torch, TORCH_LIGHTERS.get(clickedState.getBlock()).getSecond() ? SOULFIRE : LIT);
                    return InteractionResult.SUCCESS;
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

        CompoundTag compoundTag = itemStack.getOrCreateTag();

        if (compoundTag.contains(modDataKey, Tag.TAG_COMPOUND)) {
            CompoundTag modData = compoundTag.getCompound(modDataKey);

            if (modData.contains(litKey, Tag.TAG_BYTE)) {
                return modData.getByte(litKey);
            }
        }
        return 0;
    }

    public static void setLit(ItemStack itemStack, byte type) {
        if (!(itemStack.getItem() instanceof AdventurersTorchItem)) return;
        CompoundTag compoundTag = itemStack.getOrCreateTag();

        if (compoundTag.contains(modDataKey, Tag.TAG_COMPOUND)) {
            CompoundTag modData = compoundTag.getCompound(modDataKey);
            modData.putByte(litKey, type);
        }
        else {
            CompoundTag modData = new CompoundTag();
            modData.putByte(litKey, type);
            compoundTag.put(modDataKey, modData);
        }
        itemStack.setTag(compoundTag);
    }
}
