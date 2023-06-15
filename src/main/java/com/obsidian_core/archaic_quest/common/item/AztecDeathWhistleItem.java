package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.core.register.AQSoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.world.World;

public class AztecDeathWhistleItem extends Item {

    public AztecDeathWhistleItem() {
        super(new Item.Properties().tab(AQCreativeTabs.ITEMS).stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(World world, PlayerEntity player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.pass(itemStack);
        }
        if (!world.isClient()) {
            world.playSound(null, player.blockPosition(), AQSoundEvents.DEATH_WHISTLE_SHRIEK.get(), SoundSource.PLAYERS, 2.0F, 0.8F + world.random.nextFloat() / 5);
        }
        player.getCooldowns().addCooldown(this, 50);
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClient());
    }
}
