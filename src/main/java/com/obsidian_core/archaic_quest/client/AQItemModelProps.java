package com.obsidian_core.archaic_quest.client;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import com.obsidian_core.archaic_quest.common.item.AdventurersTorchItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.Entity;

public class AQItemModelProps {

    public static void register() {
        // Adventurer's Torch
        ItemProperties.register(AQItems.ADVENTURERS_TORCH.get(), ArchaicQuest.resourceLoc("lit"), (itemStack, clientWorld, livingEntity, seed) -> {
            Entity entity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();

            if (entity != null) {
                return switch (AdventurersTorchItem.getLitState(itemStack)) {
                    default -> 0.0F;
                    case 1 -> 1.0F;
                    case 2 -> 2.0F;
                };
            }
            return 0.0F;
        });
    }
}
