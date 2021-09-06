package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.core.itemgroup.ModItemGroup;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFood {
	
	public static final DeferredRegister<Item> FOOD = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

	public static final RegistryObject<Item> OLIVE = FOOD.register("olive", () -> new Item(new Item.Properties()
			.group(ModItemGroup.ITEMGROUP).food(new Food.Builder().hunger(1).saturation(1.2f).build())));

	public static final RegistryObject<Item> FRIES = FOOD.register("fries", () -> new Item(new Item.Properties()
			.group(ModItemGroup.ITEMGROUP).food(new Food.Builder().hunger(5).saturation(6.3f).build())));

	public static final RegistryObject<Item> FRIES_IN_BOX = FOOD.register("fries_in_box",
			() -> new Item(new Item.Properties().group(ModItemGroup.ITEMGROUP)
					.food(new Food.Builder().hunger(5).saturation(7.1f).build())));
}
