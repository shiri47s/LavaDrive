package com.shiri47s.mod.lavadrive.materials;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LavaArmorMaterials {

    public static RegistryEntry<ArmorMaterial> INSTANCE;

    private static final String NAME = "lava_armor";
    private static final float KNOCKBACK_RESISTANCE = 0.15f;
    private static final float TOUGHNESS = 0.15f;
    private static final int ENCHANTABILITY = 15;

    static {
        var enumMap = Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, 5);
            map.put(ArmorItem.Type.LEGGINGS, 8);
            map.put(ArmorItem.Type.CHESTPLATE, 7);
            map.put(ArmorItem.Type.HELMET, 4);
            map.put(ArmorItem.Type.BODY, 12);
        });
        INSTANCE = Registry.registerReference(
                Registries.ARMOR_MATERIAL,
                new Identifier(NAME),
                new ArmorMaterial(
                        enumMap,
                        ENCHANTABILITY,
                        SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                        () -> Ingredient.ofItems(Items.NETHERITE_INGOT),
                        List.of(new ArmorMaterial.Layer(new Identifier(NAME))),
                        TOUGHNESS,
                        KNOCKBACK_RESISTANCE));
    }
}
