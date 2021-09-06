package com.teddy0007.rlmod.common.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.core.init.ModItems;
import com.teddy0007.rlmod.core.init.ModRecipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class CrushingRecipe implements IRecipe<IInventory> {
	
	public static final Serializer SERIALIZER = new Serializer();

	private final Ingredient input;
	private final ItemStack output;
	private final ResourceLocation id;
	
	public CrushingRecipe(Ingredient input, ItemStack output, ResourceLocation id) {
		this.input = input;
		this.output = output;
		this.id = id;
	}
	
	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return true;
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		return this.output.copy();
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.output;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public IRecipeType<?> getType() {
		return ModRecipes.CRUSHING_RECIPE;
	}

	public Ingredient[] getInputs(){
		return new Ingredient[]{input};
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(ModItems.COPPER_INGOT.get());
	}
	
	public boolean isValid(ItemStack input) {
		return this.input.test(input);
	}

	@Override
	public boolean canFit(int width, int height) {
		return false;
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<CrushingRecipe> {
		Serializer() {
			this.setRegistryName(Main.MOD_ID, "crushing_recipe");
		}
		
		@Override
		public CrushingRecipe read(ResourceLocation recipeId, JsonObject json) {
			final JsonElement inputEl = JSONUtils.isJsonArray(json, "input") ? JSONUtils.getJsonArray(json, "input")
					: JSONUtils.getJsonObject(json, "input");
			final Ingredient input = Ingredient.deserialize(inputEl);
			
			final ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
			
			return new CrushingRecipe(input, output, recipeId);
		}
		
		@Override
		public CrushingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			final Ingredient input = Ingredient.read(buffer);
			final ItemStack output = buffer.readItemStack();
			
			return new CrushingRecipe(input, output, recipeId);
		}
		
		@Override
		public void write(PacketBuffer buffer, CrushingRecipe recipe) {
			recipe.input.write(buffer);
			buffer.writeItemStack(recipe.output);
		}
	}
}