package com.teddy0007.rlmod.common.material;

import java.util.function.Supplier;

import com.teddy0007.rlmod.core.init.ModItems;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum ArmorMaterials implements IArmorMaterial {

	BRONZE_ARMOR("bronze", 5, new int[] { 2, 4, 6, 2 }, 14, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4f, 0.2f,
			() -> Ingredient.fromItems(ModItems.BRONZE_INGOT.get()));

	private static final int[] baseDurability = { 112, 133, 152, 106 };
	private final String name;
	private final int durabilityMultiplier;
	private final int[] armorVal;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Ingredient repairIngredient;

	ArmorMaterials(String name, int durabilityMultiplier, int[] armorVal, int enchantability, SoundEvent equipSound,
			float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.armorVal = armorVal;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient.get();
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slot) {
		return this.armorVal[slot.getIndex()];
	}

	@Override
	public int getDurability(EquipmentSlotType slot) {
		return baseDurability[slot.getIndex()] * this.durabilityMultiplier;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairIngredient;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return this.equipSound;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

}
