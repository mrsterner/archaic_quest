package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.block.CoolVinesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


public class MacheteItem extends AQSimpleWeaponItem {

    public MacheteItem(Tier itemTier, int durability, int damage, float attackSpeed) {
        super(itemTier, durability, damage, attackSpeed);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();

        if (player == null)
            return InteractionResult.PASS;

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = context.getItemInHand();

        if (state.getBlock() instanceof CoolVinesBlock && !state.getValue(CoolVinesBlock.CUT)) {
            if (player.isShiftKeyDown()) {
                level.setBlock(pos, state.setValue(CoolVinesBlock.CAN_GROW, false), 2);
            }
            else {
                level.setBlock(pos, state.setValue(CoolVinesBlock.CUT, true).setValue(CoolVinesBlock.CAN_GROW, false), 2);
            }
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }
}
