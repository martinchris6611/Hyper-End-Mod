package ultimat3.endgamemod.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ultimat3.endgamemod.blocks.tileentity.TileEntityHighProductionFurnace;
import ultimat3.endgamemod.blocks.tileentity.TileEntityProductionFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class ContainerHighProductionFurnace extends Container {
	
	private World							world;
	private TileEntityHighProductionFurnace	machine;
	private short							lastCookTime;
	private short							lastBurnTime;
	
	public ContainerHighProductionFurnace(EntityPlayer player, World world, int x, int y, int z) {
		this.world = world;
		
		this.machine = ((TileEntityHighProductionFurnace) world.getTileEntity(x, y, z));
		
		// Furnace slots
		addSlotToContainer(new Slot(machine, 0, 76, 17));
		addSlotToContainer(new Slot(machine, 1, 56, 17));
		addSlotToContainer(new Slot(machine, 2, 36, 17));
		addSlotToContainer(new Slot(machine, 3, 16, 17));
		addSlotToContainer(new Slot(machine, 4, 56, 53));
		addSlotToContainer(new SlotFurnace(player, this.machine, 5, 116, 60));
		addSlotToContainer(new SlotFurnace(player, this.machine, 6, 116, 40));
		addSlotToContainer(new SlotFurnace(player, this.machine, 7, 116, 20));
		addSlotToContainer(new SlotFurnace(player, this.machine, 8, 116, 0));
		
		// player slots
		this.bindPlayerInventory(player.inventory);
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
	
	public void addCraftingToCrafters(ICrafting p_75132_1_) {
		super.addCraftingToCrafters(p_75132_1_);
		p_75132_1_.sendProgressBarUpdate(this, 0, this.machine.cookTime);
		p_75132_1_.sendProgressBarUpdate(this, 1, this.machine.cookTime);
		p_75132_1_.sendProgressBarUpdate(this, 2, this.machine.cookTime);
		p_75132_1_.sendProgressBarUpdate(this, 3, this.machine.cookTime);
		p_75132_1_.sendProgressBarUpdate(this, 4, this.machine.furnaceTimeLeft);
		p_75132_1_.sendProgressBarUpdate(this, 5, this.machine.NEW_FUEL_TIME);
		p_75132_1_.sendProgressBarUpdate(this, 6, this.machine.NEW_FUEL_TIME);
		p_75132_1_.sendProgressBarUpdate(this, 7, this.machine.NEW_FUEL_TIME);
		p_75132_1_.sendProgressBarUpdate(this, 8, this.machine.NEW_FUEL_TIME);
	}
	
	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			
			if (this.lastCookTime != this.machine.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.machine.cookTime);
			}
			
			if (this.lastBurnTime != this.machine.furnaceTimeLeft) {
				icrafting.sendProgressBarUpdate(this, 1, this.machine.furnaceTimeLeft);
			}
		}
		
		this.lastCookTime = this.machine.cookTime;
		this.lastBurnTime = this.machine.furnaceTimeLeft;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int updateID, int updateValue) {
		if (updateID == 0) {
			this.machine.cookTime = (short) updateValue;
		}
		
		if (updateID == 1) {
			this.machine.furnaceTimeLeft = (short) updateValue;
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			// Output slot
			if (slotID == 2) {
				
				// Add to inv
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
				
				// Inventory slots
			} else if (slotID != 1 && slotID != 0) {
				
				// If it's smeltable, add it to the input slot
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
					
					// Fuel? Add to the fuel slot
				} else if (itemstack1.getItem() == Item.getItemFromBlock(Blocks.coal_block)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
					
					// Main inventory? Add to hotbar
				} else if (slotID >= 3 && slotID < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
					
					// Hotbar? Add to main inv
				} else if (slotID >= 30 && slotID < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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
