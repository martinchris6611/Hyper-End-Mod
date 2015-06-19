package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ultimat3.endgamemod.helpers.LogHelper;
import ultimat3.endgamemod.init.ModRecipes;
import ultimat3.endgamemod.init.ModTileEntities;
import cofh.api.energy.EnergyStorage;

public class TileEntitySuperCompressor extends TileEntityProcessingMachine  {
	
	public TileEntitySuperCompressor() {
		super(10, 640, new ItemStack[10],
				"container." + ModTileEntities.SUPER_COMPRESSOR_ID,
				new EnergyStorage(384000));
	}
	
	public int OUTPUT_SLOT = 9;
	
	public boolean canProcessItem() {
		if (this.items[0] == null)
			return false;
		
		//If there is no energy storage, we can't do anything
		if (storage.getEnergyStored() < 640)
			return false;
		
		ItemStack itemstack = ModRecipes.compression().findMatchingRecipe(this, this.worldObj);
		
		// If the result is nothing, we can't smelt
		if (itemstack == null) {
			LogHelper.debug("No matching recipe for compressor.");
			return false;
		}
		// If the current output is empty, we can smelt for sure
		if (this.items[OUTPUT_SLOT] == null)
			return true;
		
		// If the result and output aren't the same, we can't smelt either
		if (!this.items[OUTPUT_SLOT].isItemEqual(itemstack))
			return false;
		
		// Otherwise, it depends on the stack limit
		int result = items[9].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= this.items[OUTPUT_SLOT].getMaxStackSize();
	}
	
	public void processItem() {
		
		ItemStack itemstack = ModRecipes.compression().findMatchingRecipe(this, this.worldObj);
		
		// If the output currently has no item, might as well copy the new result in there
		if (this.items[OUTPUT_SLOT] == null) {
			this.items[OUTPUT_SLOT] = itemstack.copy();
			
			// else we better check whether the items are equal before stacking...
		} else if (this.items[OUTPUT_SLOT].isItemEqual(itemstack)) {
			this.items[OUTPUT_SLOT].stackSize += itemstack.stackSize;
		}
		
		--this.items[0].stackSize;
		
		if (this.items[0].stackSize <= 0) {
			this.items[0] = null;
		}
	}
	
	// ====================================================
	// ================= Interfaces start =================
	// ====================================================
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		// Can't input on the output
		if (slot == 1)
			return false;
		
		// Can only input items with a smelting result on the item input
		return false;
	}
	
	// =========================================================
	// ================= ISidedInventory start =================
	// =========================================================
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		// Makes sense, right? side 0 is bottom, side 1 is top.
		if (side == 1)
			return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
		
		return new int[]{9};
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return this.isItemValidForSlot(slot, stack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		// Can't extract fuel or input items.
		if (side == 1)
			return false;
		
		// Can't extract from the item input. Sorry man.
		if (slot == 0)
			return false;
		
		// Can extract everything else though.
		return true;
	}
	
	// =========================================================
	// ===================== Recipes start =====================
	// =========================================================
	
	public int getSizeGrid() {
		return 9;
	}
	
	public ItemStack getStackInRowAndColumn(int x, int y) {
		return this.items[x + y * 3];
	}
}
