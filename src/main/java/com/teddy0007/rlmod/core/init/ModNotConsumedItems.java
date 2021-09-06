package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.common.items.KitchenKnife;
import com.teddy0007.rlmod.core.itemgroup.ModItemGroup;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModNotConsumedItems {

	public static final DeferredRegister<Item> NOT_CONSUMED_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	public static final RegistryObject<Item> KITCHEN_KNIFE = NOT_CONSUMED_ITEMS.register("kitchen_knife",
			() -> new KitchenKnife(new Item.Properties().group(ModItemGroup.ITEMGROUP)));
}
