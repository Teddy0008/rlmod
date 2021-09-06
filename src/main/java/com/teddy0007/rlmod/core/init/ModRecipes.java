package com.teddy0007.rlmod.core.init;

import java.util.Map;

import com.teddy0007.rlmod.common.recipe.CrushingRecipe;
import com.teddy0007.rlmod.common.recipe.CrushingRecipeType;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ModRecipes {
	
	public static final IRecipeType<CrushingRecipe> CRUSHING_RECIPE = new CrushingRecipeType();

	public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
		registerRecipe(event, CRUSHING_RECIPE, CrushingRecipe.SERIALIZER);
	}

	private static void registerRecipe(Register<IRecipeSerializer<?>> event, IRecipeType<?> type,
			IRecipeSerializer<?> serializer) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
		event.getRegistry().register(serializer);
	}

	public static Map<ResourceLocation, IRecipe<?>> getRecipes(IRecipeType<?> type, RecipeManager manager) {
		final Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipes = ObfuscationReflectionHelper
				.getPrivateValue(RecipeManager.class, manager, "field_199522_d");
		return recipes.get(type);
	}
}
