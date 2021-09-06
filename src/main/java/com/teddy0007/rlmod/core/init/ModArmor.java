package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.common.material.ArmorMaterials;
import com.teddy0007.rlmod.core.itemgroup.ModItemGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModArmor {

	public static final DeferredRegister<Item> ARMOR = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	public static final RegistryObject<Item> BRONZE_HELMET = ARMOR.register("bronze_helmet",
			() -> new ArmorItem(ArmorMaterials.BRONZE_ARMOR, EquipmentSlotType.HEAD,
					new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> BRONZE_CHESTPLATE = ARMOR.register("bronze_chestplate",
			() -> new ArmorItem(ArmorMaterials.BRONZE_ARMOR, EquipmentSlotType.CHEST,
					new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> BRONZE_LEGGINGS = ARMOR.register("bronze_leggings",
			() -> new ArmorItem(ArmorMaterials.BRONZE_ARMOR, EquipmentSlotType.LEGS,
					new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> BRONZE_BOOTS = ARMOR.register("bronze_boots",
			() -> new ArmorItem(ArmorMaterials.BRONZE_ARMOR, EquipmentSlotType.FEET,
					new Item.Properties().group(ModItemGroup.ITEMGROUP)));
}
