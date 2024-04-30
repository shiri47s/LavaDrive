package com.shiri47s.mod.lavadrive.items;

import com.shiri47s.mod.lavadrive.LavaDrive;
import com.shiri47s.mod.lavadrive.RenderingContext;
import com.shiri47s.mod.lavadrive.materials.LavaArmorMaterials;
import net.minecraft.client.item.TooltipType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class LavaArmorItem extends ArmorItem {
    public LavaArmorItem(Type type, Settings settings) {
        super(LavaArmorMaterials.INSTANCE, type, settings.fireproof().rarity(Rarity.RARE).maxDamage(type.getMaxDamage(43)));
    }

    public static boolean isWearLavaSets(Entity entity) {
        AtomicReference<Boolean> wearLavaHelmet = new AtomicReference<>(false);
        AtomicReference<Boolean> wearLavaChestplate = new AtomicReference<>(false);
        AtomicReference<Boolean> wearLavaLeggings = new AtomicReference<>(false);
        AtomicReference<Boolean> wearLavaBoots = new AtomicReference<>(false);
        if (!(entity instanceof PlayerEntity player)) {
            return false;
        }

        Iterable<ItemStack> armorItems = player.getArmorItems();
        armorItems.forEach((armorItem) -> {
            if (armorItem.getItem() == LavaDrive.LavaHelmetItem.get()) {
                wearLavaHelmet.set(true);
            }

            if (armorItem.getItem() == LavaDrive.LavaChestplateItem.get()) {
                wearLavaChestplate.set(true);
            }

            if (armorItem.getItem() == LavaDrive.LavaLeggingsItem.get()) {
                wearLavaLeggings.set(true);
            }

            if (armorItem.getItem() == LavaDrive.LavaBootsItem.get()) {
                wearLavaBoots.set(true);
            }
        });

        return wearLavaHelmet.get() && wearLavaChestplate.get() && wearLavaLeggings.get() && wearLavaBoots.get();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.lavadrive.lava_armors.tooltip_summary0").formatted(Formatting.YELLOW));
        tooltip.add(Text.translatable("item.lavadrive.lava_armors.tooltip_summary1").formatted(Formatting.YELLOW));

        MutableText resistText = Text.translatable("item.lavadrive.lava_armors.tooltip_resist");
        MutableText visionText = Text.translatable("item.lavadrive.lava_armors.tooltip_vision");
        MutableText speedText = Text.translatable("item.lavadrive.lava_armors.tooltip_speed");

        if (RenderingContext.LavaSetsWearer != null)
        {
            tooltip.add(resistText.formatted(Formatting.GREEN));
            tooltip.add(visionText.formatted(Formatting.GREEN));
            tooltip.add(speedText.formatted(Formatting.GREEN));
        }
        else
        {
            tooltip.add(resistText);
            tooltip.add(visionText);
            tooltip.add(speedText);
        }
    }
}
