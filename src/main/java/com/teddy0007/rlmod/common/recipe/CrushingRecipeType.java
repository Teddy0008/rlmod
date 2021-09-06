package com.teddy0007.rlmod.common.recipe;

import com.teddy0007.rlmod.Main;

import net.minecraft.item.crafting.IRecipeType;

public class CrushingRecipeType implements IRecipeType<CrushingRecipe> {

	@Override
	public String toString() {
		return Main.MOD_ID + ":alloying_recipe";
	}
	
}
