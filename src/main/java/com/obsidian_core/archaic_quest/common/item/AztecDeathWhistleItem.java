package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.core.register.AQSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AztecDeathWhistleItem extends Item {

    public AztecDeathWhistleItem() {
        super(new Item.Settings().group(AQCreativeTabs.ITEMS).stacksTo(1));
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (player.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.pass(itemStack);
        }
        if (!world.isClient()) {
            world.playSound(null, player.getBlockPos(), AQSoundEvents.DEATH_WHISTLE_SHRIEK, SoundCategory.PLAYERS, 2.0F, 0.8F + world.random.nextFloat() / 5);
        }
        player.getItemCooldownManager().set(this, 50);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
