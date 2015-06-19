package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntitySuperCompressor;

public class ContainerSuperCompressor extends ContainerMachine {
	
<<<<<<< HEAD
	public ContainerSuperCompressor(EntityPlayer player, World world, int x,
			int y, int z) {
		super(player, world, x, y, z);
=======
	@SuppressWarnings("unused")
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
>>>>>>> fa3e81984486903a91271b84c55562d3acb95396
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

	@Override
	protected Slot[] getSlotsForAdding() {
		Slot[] slots = new Slot[10];
		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				slots[x*3 + y] = new Slot(machine, x + y * 3, 38 + x * 18, 17 + y * 18);
			}
		}
		slots[9] = new Slot(machine, 9, 116, 35);
		return slots;
	}
	
}
