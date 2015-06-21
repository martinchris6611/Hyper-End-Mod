package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMachine;

abstract public class ContainerMachine extends Container {

	protected World world;
	protected TileEntityMachine machine;

	public ContainerMachine(EntityPlayer player, World world, int x, int y, int z) {
		this.world = world;

		this.machine = ((TileEntityMachine) world.getTileEntity(x, y, z));
		Slot[] slots = this.getSlotsForAdding();
		for(int i=0; i<slots.length; i++) {
			addSlotToContainer(slots[i]);
		}
		
		this.bindPlayerInventory(player.inventory);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		// Main inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
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
	
	protected abstract Slot[] getSlotsForAdding();
	
	@Override
	public final ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		
		// returned stack
		ItemStack stack = null;
		// slot that is shift clicked
		Slot slot = (Slot) this.inventorySlots.get(slotID);
		
		if(slot != null && slot.getHasStack()) {
			
			// stack that the slot contains
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			// if it's inventory slot
			if(isInventory(slotID)) {
				
				if (slotAcceptStack(slotID, slotStack)) {
					if (!mergeItemStack(slotStack, 0, inventoryBegin(), false)) {
						return null;
					}					
				}
				
				// if it's in the main inventory add it to the hotbar
				else if(isMain(slotID)) {
					if (!mergeItemStack(slotStack, hotbarBegin(), inventoryEnd(), false)) {
						return null;
					}
				}
				
				// if it's in the hotbar add it to the main inventory
				else if(isHotbar(slotID)) {
					if(!mergeItemStack(slotStack, inventoryBegin(), hotbarBegin(), false)) {
						return null;
					}
				}
				
			}
			
			// it's not inventory, send to inventory
			else if(!mergeItemStack(slotStack, inventoryBegin(), inventoryEnd(), false)) {
				return null;
			}
			
			if (slotStack.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			
			// if the whole stack is moved
			if (slotStack.stackSize == stack.stackSize) {
				return null;
			}
			
			slot.onPickupFromSlot(player, slotStack);
			
		}
		
		return stack;
	}
	
	abstract public boolean slotAcceptStack(int slotID, ItemStack stack);
	
	public int inventoryBegin() {
		return inventoryEnd() - 36;
	}
	
	public int inventoryEnd() {
		return this.inventorySlots.size();
	}
	
	public int hotbarBegin() {
		return inventoryEnd() - 10;
	}
	
	public boolean isInventory(int slotID) {
		return slotID >= inventoryBegin() && slotID < inventoryEnd();
	}
	
	public boolean isHotbar(int slotID) {
		return isInventory(slotID) && slotID >= hotbarBegin();
	}
	
	public boolean isMain(int slotID) {
		return isInventory(slotID) && slotID < hotbarBegin();
	}
	
}
