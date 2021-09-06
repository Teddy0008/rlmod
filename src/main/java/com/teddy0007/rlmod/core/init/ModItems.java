package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.core.itemgroup.ModItemGroup;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

	public static final RegistryObject<Item> BOX_FOR_FRIES = ITEMS.register("box_for_fries",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> RAW_IRON = ITEMS.register("raw_iron",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> RAW_GOLD = ITEMS.register("raw_gold",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> RAW_COPPER = ITEMS.register("raw_copper",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> COPPER_DUST = ITEMS.register("copper_dust",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> BRONZE_DUST = ITEMS.register("bronze_dust",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)));
}
