package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import ultimat3.endgamemod.init.ModTileEntities;
import cofh.api.energy.EnergyStorage;

public class TileEntityHighProductionFurnace extends TileEntityFueledMachine {
	
	public TileEntityHighProductionFurnace() {
		super(20, 4, 1, 1280, new ItemStack[9],
				"container." + ModTileEntities.HIGH_PRODUCTION_FURNACE_ID,
				new EnergyStorage(768000));
		// TODO Auto-generated constructor stub
	}
	
	public boolean canProcessItemAt(int i) {
		if (this.items[i] == null)
			return false;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[i]);
		
		// If the result is nothing, we can't smelt
		if (itemstack == null)
			return false;
		
		// If the current output is empty, we can smelt for sure
		if (this.items[i+5] == null)
			return true;
		
		// If the result and output aren't the same, we can't smelt either
		if (!this.items[i+5].isItemEqual(itemstack))
			return false;
		
		// Otherwise, it depends on the stack limit
		int result = items[i+5].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= this.items[i+5].getMaxStackSize();
	}
	


	@Override
	public boolean canProcessItem() {
		for(int i=0; i<4; i++) {
			if(canProcessItemAt(i)) return true;
		}
		return false;
	}
	
	@Override
	public void processItem() {
		for(int i=0; i<4; i++) {
			if(canProcessItemAt(i)) {
				ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[i]);
				
				// If the output currently has no item, might as well copy the new result in there
				if (this.items[i+5] == null) {
					this.items[i+5] = itemstack.copy();
					// else we better check whether the items are equal before stacking...
				} else if (this.items[i+5].isItemEqual(itemstack)) {
					this.items[i+5].stackSize += itemstack.stackSize;
				}
				
				--this.items[i].stackSize;
				
				if (this.items[i].stackSize <= 0) {
					this.items[i] = null;
				}
			}
		}
	}
	
	// ====================================================
	// ================= Interfaces start =================
	// ====================================================
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		// Can't input on the output
		if (slot == 5 || slot == 6 || slot == 7 || slot == 8)
			return false;
		
		// Can only input valid fuel (coal blocks) on the fuel input
		if (slot == 4)
			return stack.getItem() == Item.getItemFromBlock(Blocks.coal_block);
		
		// Can only input items with a smelting result on the item input
		return FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
		// return true;
	}
	
	// =========================================================
	// ================= ISidedInventory start =================
	// =========================================================
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		// Makes sense, right? side 0 is bottom, side 1 is top.
		if (side == 0)
			return new int[]{4};
		
		if (side == 1)
			return new int[]{0, 1, 2, 3};
		
		return new int[]{5, 6, 7, 8};
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return this.isItemValidForSlot(slot, stack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		// Can't extract fuel or input items.
		if (side == 0 || side == 1)
			return false;
		
		// Can't extract from the item input. Sorry man.
		if (slot == 0)
			return false;
		
		// Can extract everything else though.
		return true;
	}
}
