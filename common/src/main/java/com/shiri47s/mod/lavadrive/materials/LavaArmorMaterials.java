package com.shiri47s.mod.lavadrive.materials;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.HashMap;

public class LavaArmorMaterials implements ArmorMaterial {
    private static final String NAME = "lava_armor";

    private static final HashMap<ArmorItem.Type, Integer> DURABILITIES = new HashMap<>()
    {
        {
            put(ArmorItem.Type.HELMET, 607);
            put(ArmorItem.Type.CHESTPLATE, 592);
            put(ArmorItem.Type.LEGGINGS, 555);
            put(ArmorItem.Type.BOOTS, 481);
        }
    };

    private static final HashMap<ArmorItem.Type, Integer> PROTECTIONS = new HashMap<>()
    {
        {
            put(ArmorItem.Type.HELMET, 5);
            put(ArmorItem.Type.CHESTPLATE, 8);
            put(ArmorItem.Type.LEGGINGS, 7);
            put(ArmorItem.Type.BOOTS, 4);
        }
    };

    @Override
    public int getDurability(ArmorItem.Type type) {
        return DURABILITIES.get(type);
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return PROTECTIONS.get(type);
    }

    @Override
    public int getEnchantability() {
        return 7;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.NETHERITE_INGOT);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public float getToughness() {
        return 3.5F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.15F;
    }
}
