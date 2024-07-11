package com.shiri47s.mod.lavadrive;

import com.shiri47s.mod.lavadrive.items.*;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

@SuppressWarnings("unused")
public class LavaDrive
{
	public static final String MOD_ID = "lavadrive";

	public static final RegistryKey<PlacedFeature> RED_DIAMOND_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, Ids.RedDiamondPlacedKey));
	public static final RegistryKey<PlacedFeature> RED_DIAMOND_HIGHER_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, Ids.RedDiamondHigherPlacedKey));

	private static final DeferredRegister<ItemGroup> TAB_REGISTER = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM_GROUP);
	public static DeferredSupplier<ItemGroup> TAB_SUPPLIER;

	public static final TagKey<DamageType> INVULNERABLE_DAMAGE_TAG =
			TagKey.of(
					RegistryKeys.DAMAGE_TYPE,
					Identifier.of(
							MOD_ID,
							"lavadrive_lavasets_immue_to"
					));

	public static DeferredRegister<Item> LavaDriveItems = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);
	public static DeferredRegister<Block> LavaDriveBlocks = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);

	@SuppressWarnings("UnstableApiUsage")
	public static void init(IModPlatform platform) {
		TAB_SUPPLIER = TAB_REGISTER.register(MOD_ID, () -> CreativeTabRegistry.create(
				Text.translatable("item.tag"),
				() -> new ItemStack(Items.LAVA_BUCKET)));

		registerBlocks();
		registerItems();
		registerArmors();
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

	private static void registerBlocks() {
		var redDiamondOreSupplier = LavaDriveBlocks.register(
				Ids.RedDiamondOreKey,
				() -> new ExperienceDroppingBlock(
						UniformIntProvider.create(3, 7),
						AbstractBlock.Settings.create().
								mapColor(MapColor.STONE_GRAY).
								instrument(NoteBlockInstrument.BASEDRUM).
								requiresTool().
								strength(4.5f, 3.0f)));
		var deepslateRedDiamondOreSupplier = LavaDriveBlocks.register(
				Ids.DeepslateRedDiamondOreKey,
				() -> new ExperienceDroppingBlock(
							UniformIntProvider.create(3, 7),
							AbstractBlock.Settings.create().
									mapColor(MapColor.DEEPSLATE_GRAY).
									instrument(NoteBlockInstrument.BASEDRUM).
									requiresTool().
									strength(5.5f, 3.0f)));

		LavaDriveBlocks.register();

		LavaDriveItems.register(
				Ids.RedDiamondOreKey,
				() -> new BlockItem(redDiamondOreSupplier.get(), new Item.Settings()));
		LavaDriveItems.register(
				Ids.DeepslateRedDiamondOreKey,
				() -> new BlockItem(deepslateRedDiamondOreSupplier.get(), new Item.Settings()));
	}

	private static void registerArmors() {
		LavaDriveItems.register(Ids.HelmetKey, LavaHelmet::new);
		LavaDriveItems.register(Ids.ChestplateKey, LavaChestplate::new);
		LavaDriveItems.register(Ids.LeggingsKey, LavaLeggings::new);
		LavaDriveItems.register(Ids.BootsKey, LavaBoots::new);
	}

	private static void registerItems() {
		LavaDriveItems.register(
				Ids.RedDiamondKey,
				() -> new Item(new Item.Settings()));
		LavaDriveItems.register(
				Ids.RedDiamondIngotKey,
				() -> new Item(new Item.Settings()));
		LavaDriveItems.register(
				Ids.LavaUpgradeTemplateKey,
				() -> new Item(new Item.Settings()));
	}
}
