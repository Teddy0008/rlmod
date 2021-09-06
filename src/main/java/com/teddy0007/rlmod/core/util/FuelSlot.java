package com.teddy0007.rlmod.core.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

public class FuelSlot extends Slot {

	public FuelSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isItemValid(ItemStack stack) {
		return ForgeHooks.getBurnTime(stack) > 0;
	}

}