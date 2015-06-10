package ultimat3.endgamemod.gui;

import ultimat3.endgamemod.blocks.machines.tileentity.TileEntitySuperCompressor;
import ultimat3.endgamemod.init.ModRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerSuperCompressor extends Container {
	
	private World						world;
	private TileEntitySuperCompressor	machine;
	
	public ContainerSuperCompressor(EntityPlayer player, World world, int x, int y, int z) {
		this.world = world;
		this.machine = (TileEntitySuperCompressor) world.getTileEntity(x, y, z);
		
		// Compressor slots
		addCraftingSlots();
		addSlotToContainer(new Slot(machine, 9, 116, 35));
		
		// PLayer slots
		this.bindPlayerInventory(player.inventory);
	}
	
	private void addCraftingSlots() {
		// Nice grid pattern
		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				addSlotToContainer(new Slot(machine, x + y * 3, 38 + x * 18, 17 + y * 18));
			}
		}
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		// Main inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		// Hotbar
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			// Output slot
			if (slotID == 1) {
				
				// Add to inv
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
				
				// Inventory slots
			} else if (slotID != 0) {
				
				// If it's smeltable, add it to the input slot
				// noformat
				/*if (ModRecipes.compression().getResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
					// main inv -> hotbar
				} else */
				// format
				if (slotID >= 2 && slotID < 29) {
					if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
						return null;
					}
					
					// Hotbar -> main inv
				} else if (slotID >= 29 && slotID < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
				return null;
			}
			
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			
			slot.onPickupFromSlot(player, itemstack1);
		}
		
		return itemstack;
	}
	
}
