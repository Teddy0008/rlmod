package com.teddy0007.rlmod.common.material;

import java.util.function.Supplier;

import com.teddy0007.rlmod.core.init.ModItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum ToolMaterials implements IItemTier {
	
	BRONZE_TOOL(2, 200, 6f, 1.5f, 14, () -> Ingredient.fromItems(ModItems.BRONZE_INGOT.get()));

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final Ingredient repairMaterial;
	
	ToolMaterials(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial.get();
	}
	
	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}
	@Override
	public float getEfficiency() {
		return this.efficiency;
	}
	@Override
	public int getEnchantability() {
		return this.enchantability;
	}
	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}
	@Override
	public int getMaxUses() {
		return this.maxUses;
	}
	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial;
	}
}
