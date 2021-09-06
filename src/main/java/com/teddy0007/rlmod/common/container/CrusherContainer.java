package com.teddy0007.rlmod.common.container;

import java.util.Objects;

import com.teddy0007.rlmod.common.te.CrusherTileEntity;
import com.teddy0007.rlmod.core.init.ModContainerTypes;
import com.teddy0007.rlmod.core.init.ModMachines;
import com.teddy0007.rlmod.core.util.FuelSlot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class CrusherContainer extends Container {

	public final CrusherTileEntity te;
	private final IWorldPosCallable canInteractWithCallable;

	public CrusherContainer(final int windowId, final PlayerInventory playerInv, final CrusherTileEntity te) {
		super(ModContainerTypes.CRUSHER_CONTAINER_TYPE.get(), windowId);
		this.te = te;
		this.canInteractWithCallable = IWorldPosCallable.of(te.getWorld(), te.getPos());

		// Tile Entity
		this.addSlot(new Slot((IInventory) te, 0, 69, 17));
		this.addSlot(new FuelSlot(te, 1, 69, 53));
		this.addSlot(new Slot((IInventory) te, 2, 119, 35) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});

		// Main Player Inventory
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
			}
		}

		// Player Hotbar
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
		}
	}

	public CrusherContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static CrusherTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.world.getTileEntity(data.readBlockPos());
		if (te instanceof CrusherTileEntity) {
			return (CrusherTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity is not correct.");
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(canInteractWithCallable, playerIn, ModMachines.CRUSHER.get());
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if (index < CrusherTileEntity.slots
					&& !this.mergeItemStack(stack, CrusherTileEntity.slots, this.inventorySlots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!this.mergeItemStack(stack, 0, CrusherTileEntity.slots, false)) {
				return ItemStack.EMPTY;
			}
			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return stack;
	}
	
}
