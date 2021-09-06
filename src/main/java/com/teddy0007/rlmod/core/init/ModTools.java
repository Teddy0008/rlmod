package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.common.material.ToolMaterials;
import com.teddy0007.rlmod.core.itemgroup.ModItemGroup;

import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTools {

	public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	public static final RegistryObject<Item> BRONZE_SWORD = TOOLS.register("bronze_sword",
			() -> new SwordItem(ToolMaterials.BRONZE_TOOL, 3, -2.4f,
					new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> BRONZE_PICKAXE = TOOLS.register("bronze_pickaxe",
			() -> new PickaxeItem(ToolMaterials.BRONZE_TOOL, -1, -0.8f,
					new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> BRONZE_AXE = TOOLS.register("bronze_axe",
			() -> new AxeItem(ToolMaterials.BRONZE_TOOL, 6, -3.0f,
					new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> BRONZE_SHOVEL = TOOLS.register("bronze_shovel",
			() -> new ShovelItem(ToolMaterials.BRONZE_TOOL, -1, -0.8f,
					new Item.Properties().group(ModItemGroup.ITEMGROUP)));

	public static final RegistryObject<Item> BRONZE_HOE = TOOLS.register("bronze_hoe",
			() -> new HoeItem(ToolMaterials.BRONZE_TOOL, -1, -0.8f,
					new Item.Properties().group(ModItemGroup.ITEMGROUP)));
	
}
