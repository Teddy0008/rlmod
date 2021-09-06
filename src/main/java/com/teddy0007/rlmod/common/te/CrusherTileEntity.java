package com.teddy0007.rlmod.common.te;

import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.common.container.CrusherContainer;
import com.teddy0007.rlmod.common.recipe.CrushingRecipe;
import com.teddy0007.rlmod.core.init.ModRecipes;
import com.teddy0007.rlmod.core.init.ModTileEntitiyTypes;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.Constants;

public class CrusherTileEntity extends LockableLootTileEntity {

	public static int slots = 3;
	public static int alloyingTime = 200;

	protected NonNullList<ItemStack> items;
	private int counter, smeltingTime;
	private CrushingRecipe recipe;

	protected CrusherTileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
		items = NonNullList.withSize(slots, ItemStack.EMPTY);
		counter = 0;
		smeltingTime = 0;
		recipe = null;
	}
	
	public CrusherTileEntity() {
		this(ModTileEntitiyTypes.CRUSHER_TILE_ENTITY_TYPE.get());
	}
	
	@Override
	public int getSizeInventory() {
		return slots;
	}
	
	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}
	
	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.items = itemsIn;
	}
	
	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + Main.MOD_ID + ".crusher");
	}
	
	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new CrusherContainer(id, player, this);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (!checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.items);
		}
		return compound;
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(nbt)) {
			ItemStackHelper.loadAllItems(nbt, this.items);
		}
	}
	
	public void tick() {
		if (this.world == null || this.world.isRemote || ModRecipes.getRecipes(ModRecipes.CRUSHING_RECIPE, world.getRecipeManager()) == null)
			return;
		if (ModRecipes.getRecipes(ModRecipes.CRUSHING_RECIPE, world.getRecipeManager()).values().size() == 0)
			return;
		ItemStack input = getStackInSlot(0);
		ItemStack fuel = getStackInSlot(1);
		ItemStack output = getStackInSlot(2);
		for (final IRecipe<?> recipe : ModRecipes.getRecipes(ModRecipes.CRUSHING_RECIPE, world.getRecipeManager())
				.values()) {
			CrushingRecipe currRecipe = (CrushingRecipe) recipe;
			if (counter <= 0 && currRecipe.isValid(input) && checkOutput(output) && checkFuel(fuel)) {
				this.recipe = currRecipe;
				counter = 200;
				input.shrink(1);
			}
		}
		// activates it when your alloy smelter is doing its job.
		BlockState state = world.getBlockState(getPos());
		if (state.get(BlockStateProperties.POWERED) != smeltingTime > 0) {
			world.setBlockState(pos, state.with(BlockStateProperties.POWERED, smeltingTime > 0),
					Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
		}
		if (counter > 0 && smeltingTime > 0) {
			counter--;
			smeltingTime--;
			if (counter == 0) {
				if (output.isEmpty()) {
					setInventorySlotContents(4, this.recipe.getRecipeOutput().copy());
				} else {
					output.grow(this.recipe.getRecipeOutput().getCount());
				}
			} else {
				checkFuel(fuel);
			}
		}
	}

	@SuppressWarnings("deprecation")
	protected boolean checkFuel(ItemStack fuel) {
		if (this.smeltingTime > 0)
			return true;
		if (ForgeHooks.getBurnTime(fuel) > 0) {
			fuel.shrink(1);
			smeltingTime = ForgeHooks.getBurnTime(fuel);
			return true;
		}
		return false;
	}

	private boolean checkOutput(ItemStack output) {
		if (output.isEmpty())
			return true;
		if (output.getItem() == recipe.getRecipeOutput().getItem()
				&& output.getCount() + recipe.getRecipeOutput().getCount() <= output.getMaxStackSize())
			return true;
		return false;
	}
}