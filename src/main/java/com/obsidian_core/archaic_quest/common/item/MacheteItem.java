package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.block.CoolVinesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class MacheteItem extends AQSimpleWeaponItem {

    public MacheteItem(ToolMaterial itemTier, int durability, int damage, float attackSpeed) {
        super(itemTier, durability, damage, attackSpeed);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();

        if (player == null)
            return ActionResult.PASS;

        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        ItemStack stack = context.getStack();

        if (state.getBlock() instanceof CoolVinesBlock && !state.get(CoolVinesBlock.CUT)) {
            if (player.isSneaking()) {
                world.setBlockState(pos, state.with(CoolVinesBlock.CAN_GROW, false), 2);
            }
            else {
                world.setBlockState(pos, state.with(CoolVinesBlock.CUT, true).with(CoolVinesBlock.CAN_GROW, false), 2);
            }
            stack.damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
            return ActionResult.success(world.isClient());
        }
        return ActionResult.PASS;
    }
}
