package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.block.CoolVinesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MacheteItem extends AQSimpleWeaponItem {

    public MacheteItem(IItemTier itemTier, int durability, int damage, float attackSpeed) {
        super(itemTier, durability, damage, attackSpeed);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();

        if (player == null)
            return ActionResultType.PASS;

        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        ItemStack stack = context.getItemInHand();

        if (state.getBlock() instanceof CoolVinesBlock && !state.getValue(CoolVinesBlock.CUT)) {
            if (player.isShiftKeyDown()) {
                world.setBlock(pos, state.setValue(CoolVinesBlock.CAN_GROW, false), 2);
            }
            else {
                world.setBlock(pos, state.setValue(CoolVinesBlock.CUT, true).setValue(CoolVinesBlock.CAN_GROW, false), 2);
            }
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
            return ActionResultType.sidedSuccess(world.isClientSide);
        }
        return ActionResultType.PASS;
    }
}
