package com.teddy0007.rlmod.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class KitchenKnife extends Item {

	public KitchenKnife(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
	  return this.getDefaultInstance();
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
	  return true;
	}
}
