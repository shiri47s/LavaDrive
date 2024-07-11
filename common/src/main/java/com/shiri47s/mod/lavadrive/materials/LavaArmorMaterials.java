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
import java.util.HashMap;
import java.util.List;

public class LavaArmorMaterials {
    public static RegistryEntry<ArmorMaterial> INSTANCE;

    private static final String NAME = "lava_armor";
    private static final float TOUGHNESS = 3.5f;
    private static final float KNOCKBACK = 0.15f;
    private static final int ENCHANTMENT = 12;

    static {
        INSTANCE = init();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static RegistryEntry<ArmorMaterial> init() {
        var enumMap = Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, 4);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 8);
            map.put(ArmorItem.Type.HELMET, 5);
            map.put(ArmorItem.Type.BODY, 12);
        });

        var id = Identifier.of(NAME);
        return Registry.registerReference(
                Registries.ARMOR_MATERIAL,
                id,
                new ArmorMaterial(
                        enumMap,
                        ENCHANTMENT,
                        SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                        () -> Ingredient.ofItems(Items.NETHERITE_INGOT),
                        List.of(new ArmorMaterial.Layer(id)),
                        TOUGHNESS,
                        KNOCKBACK));
    }
}
