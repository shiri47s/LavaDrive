package com.shiri47s.mod.lavadrive.fabric;

import com.shiri47s.mod.lavadrive.IModPlatform;
import com.shiri47s.mod.lavadrive.LavaDrive;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Optional;

public class FabricPlatform implements IModPlatform {
    @Override
    public void RegisterItems() {
        registerItemGroup(FabricItemGroup.builder()
                        .displayName(Text.translatable("item.tag"))
                        .icon(() -> new ItemStack(Items.LAVA_BUCKET))
                        .entries((displayContext, entries) -> Registries.ITEM.getIds()
                                    .stream()
                                    .filter(key -> key.getNamespace().equals(LavaDrive.MOD_ID))
                                    .map(Registries.ITEM::getOrEmpty)
                                    .map(Optional::orElseThrow)
                                    .forEach(entries::add))
                        .build());
    }

    private static <T extends ItemGroup> void registerItemGroup(T itemGroup) {
        Registry.register(Registries.ITEM_GROUP, LavaDrive.MOD_ID, itemGroup);
    }
}
