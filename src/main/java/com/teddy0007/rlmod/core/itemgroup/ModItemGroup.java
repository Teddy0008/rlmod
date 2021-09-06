package com.teddy0007.rlmod.core.itemgroup;

import com.teddy0007.rlmod.core.init.ModFood;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup extends ItemGroup {
	
	public static final ModItemGroup ITEMGROUP = new ModItemGroup(ItemGroup.GROUPS.length, "itemgroup");

	public ModItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModFood.FRIES_IN_BOX.get());
	}

}
