package com.shiri47s.mod.lavadrive;

import com.shiri47s.mod.lavadrive.items.*;
import com.shiri47s.mod.lavadrive.materials.LavaArmorMaterials;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

@SuppressWarnings("unused")
public class LavaDrive
{
	public static final String MOD_ID = "lavadrive";

	public static final RegistryKey<PlacedFeature> RED_DIAMOND_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID, Ids.RedDiamondPlacedKey));
	public static final RegistryKey<PlacedFeature> RED_DIAMOND_HIGHER_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID, Ids.RedDiamondHigherPlacedKey));

	public static final TagKey<DamageType> INVULNERABLE_DAMAGE_TAG =
			TagKey.of(
					RegistryKeys.DAMAGE_TYPE,
					new Identifier(
							MOD_ID,
							"lavadrive_lavasets_immue_to"
					));

	public static DeferredRegister<Item> LavaDriveItems = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);
	public static DeferredRegister<Block> LavaDriveBlocks = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);


	public static RegistrySupplier<Item> LavaHelmetItem =
			LavaDriveItems.register(
					Ids.HelmetKey,
					() -> new LavaHelmet(
							new LavaArmorMaterials(),
							new Item.Settings()));

	public static RegistrySupplier<Item> LavaChestplateItem =
			LavaDriveItems.register(
					Ids.ChestplateKey,
					() -> new LavaChestplate(
							new LavaArmorMaterials(),
							new Item.Settings()));

	public static RegistrySupplier<Item> LavaLeggingsItem =
			LavaDriveItems.register(
					Ids.LeggingsKey,
					() -> new LavaLeggings(
							new LavaArmorMaterials(),
							new Item.Settings()));

	public static RegistrySupplier<Item> LavaBootsItem =
			LavaDriveItems.register(
					Ids.BootsKey,
					() -> new LavaBoots(
							new LavaArmorMaterials(),
							new Item.Settings()));

	public static RegistrySupplier<Item> RedDiamondItem =
			LavaDriveItems.register(
					Ids.RedDiamondKey,
					() -> new Item(new Item.Settings()));

	public static RegistrySupplier<Item> RedDiamondIngotItem =
			LavaDriveItems.register(
					Ids.RedDiamondIngotKey,
					() -> new Item(new Item.Settings()));

	public static RegistrySupplier<Item> LavaUpgradeTemplateItem =
			LavaDriveItems.register(
					Ids.LavaUpgradeTemplateKey,
					() -> new Item(new Item.Settings()));

	public static final RegistrySupplier<Block> RedDiamondOreBlock =
			LavaDriveBlocks.register(
					Ids.RedDiamondOreKey,
					() -> new ExperienceDroppingBlock(
							UniformIntProvider.create(3, 7),
							AbstractBlock.Settings.create().
									mapColor(MapColor.STONE_GRAY).
									instrument(Instrument.BASEDRUM).
									requiresTool().
									strength(4.5f, 3.0f))
			);

	public static final RegistrySupplier<Block> DeepslateRedDiamondOreBlock =
			LavaDriveBlocks.register(
					Ids.DeepslateRedDiamondOreKey,
					() -> new ExperienceDroppingBlock(
							UniformIntProvider.create(3, 7),
							AbstractBlock.Settings.create().
									mapColor(MapColor.DEEPSLATE_GRAY).
									instrument(Instrument.BASEDRUM).
									requiresTool().
									strength(5.5f, 3.0f))
			);

	public static final RegistrySupplier<Item> RedDiamondOreBlockItem =
			LavaDriveItems.register(
					Ids.RedDiamondOreKey,
					() -> new BlockItem(RedDiamondOreBlock.get(), new Item.Settings())
			);

	public static final RegistrySupplier<Item> DeepslateRedDiamondOreBlockItem =
			LavaDriveItems.register(
					Ids.DeepslateRedDiamondOreKey,
					() -> new BlockItem(DeepslateRedDiamondOreBlock.get(), new Item.Settings())
			);

	@SuppressWarnings("UnstableApiUsage")
	public static void init(IModPlatform platform) {
		LavaDriveBlocks.register();
		LavaDriveItems.register();

		BiomeModifications.addProperties((biomeContext, mutable) -> {
			if (biomeContext.hasTag(BiomeTags.IS_OVERWORLD)) {
				mutable.getGenerationProperties().addFeature(
						GenerationStep.Feature.UNDERGROUND_ORES,
						RED_DIAMOND_PLACED_KEY
				);
				mutable.getGenerationProperties().addFeature(
						GenerationStep.Feature.UNDERGROUND_ORES,
						RED_DIAMOND_HIGHER_PLACED_KEY
				);
			}
		});

		ClientTickEvent.CLIENT_POST.register((client) -> {
            if (client.player != null) {
				if (LavaArmorItem.isWearLavaSets(client.player)) {
					RenderingContext.LavaSetsWearer = client.player;
				}
				else {
					RenderingContext.LavaSetsWearer = null;
				}
			}
		});
	}
}
