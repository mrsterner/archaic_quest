package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.register.AQSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class AztecDeathWhistleItem extends Item {

    public AztecDeathWhistleItem() {
        super(new Item.Properties().tab(AQCreativeTabs.ITEMS).stacksTo(1));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getItemInHand(hand);

        if (playerEntity.getCooldowns().isOnCooldown(this)) {
            return ActionResult.pass(itemStack);
        }
        if (!world.isClientSide) {
            world.playSound(null, playerEntity.blockPosition(), AQSoundEvents.DEATH_WHISTLE_SHRIEK.get(), SoundCategory.PLAYERS, 2.0F, 0.8F + world.random.nextFloat() / 5);
        }
        playerEntity.getCooldowns().addCooldown(this, 50);
        return ActionResult.sidedSuccess(itemStack, world.isClientSide);
    }
}
