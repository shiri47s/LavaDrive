package com.shiri47s.mod.lavadrive.items;

import com.shiri47s.mod.lavadrive.RenderingContext;
import com.shiri47s.mod.lavadrive.materials.LavaArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.List;

public abstract class LavaArmorItem extends ArmorItem {

    public LavaArmorItem(Type type) {
        super(LavaArmorMaterials.INSTANCE, type, new Item.Settings().rarity(Rarity.EPIC).fireproof().maxDamage(type.getMaxDamage(39)));
    }

    public static boolean isWearLavaSets(Entity entity) {
        if (entity instanceof PlayerEntity playerEntity) {
            return all(
                    is(playerEntity.getEquippedStack(EquipmentSlot.HEAD)),
                    is(playerEntity.getEquippedStack(EquipmentSlot.CHEST)),
                    is(playerEntity.getEquippedStack(EquipmentSlot.LEGS)),
                    is(playerEntity.getEquippedStack(EquipmentSlot.FEET)));
        }

        return false;
    }

    private static boolean is(ItemStack stack) {
        return stack.getItem() instanceof LavaArmorItem;
    }

    private static boolean all(boolean... predicates) {
        for (boolean predicate : predicates) {
            if (!predicate) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.lavadrive.lava_armors.tooltip_summary0").formatted(Formatting.YELLOW));
        tooltip.add(Text.translatable("item.lavadrive.lava_armors.tooltip_summary1").formatted(Formatting.YELLOW));

        MutableText resistText = Text.translatable("item.lavadrive.lava_armors.tooltip_resist");
        MutableText visionText = Text.translatable("item.lavadrive.lava_armors.tooltip_vision");
        MutableText speedText = Text.translatable("item.lavadrive.lava_armors.tooltip_speed");

        if (RenderingContext.LavaSetsWearer != null) {
            tooltip.add(resistText.formatted(Formatting.GREEN));
            tooltip.add(visionText.formatted(Formatting.GREEN));
            tooltip.add(speedText.formatted(Formatting.GREEN));
        } else {
            tooltip.add(resistText);
            tooltip.add(visionText);
            tooltip.add(speedText);
        }
    }
}
