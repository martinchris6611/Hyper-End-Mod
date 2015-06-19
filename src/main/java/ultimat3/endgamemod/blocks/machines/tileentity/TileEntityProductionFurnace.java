package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import ultimat3.endgamemod.init.ModTileEntities;
import cofh.api.energy.EnergyStorage;

public class TileEntityProductionFurnace extends TileEntityFueledMachine {
	
	public TileEntityProductionFurnace() {
		super(200, 1, 10, 160, new ItemStack[3],
				"container." + ModTileEntities.PRODUCTION_FURNACE_ID,
				new EnergyStorage(96000));
		// TODO Auto-generated constructor stub
	}

	public boolean canProcessItem() {
		if (this.items[0] == null)
			return false;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[0]);
		
		// If the result is nothing, we can't smelt
		if (itemstack == null)
			return false;
		
		// If the current output is empty, we can smelt for sure
		if (this.items[2] == null)
			return true;
		
		// If the result and output aren't the same, we can't smelt either
		if (!this.items[2].isItemEqual(itemstack))
			return false;
		
		// Otherwise, it depends on the stack limit
		int result = items[2].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= this.items[2].getMaxStackSize();
	}
	
	public void processItem() {
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[0]);
		if(itemstack == null) return;
		
		if (this.items[2] == null) {
			this.items[2] = itemstack.copy();
			// else we better check whether the items are equal before stacking...
		} else if (this.items[2].isItemEqual(itemstack)) {
			this.items[2].stackSize += itemstack.stackSize;
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
		if (slot == 2)
			return false;
		
		// Can only input valid fuel (coal blocks) on the fuel input
		if (slot == 1)
			return stack.getItem() == Item.getItemFromBlock(Blocks.coal_block);
		
		// Can only input items with a smelting result on the item input
		return FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
	}
	
	// =========================================================
	// ================= ISidedInventory start =================
	// =========================================================
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		// Makes sense, right? side 0 is bottom, side 1 is top.
		if (side == 0)
			return new int[]{1};
		
		if (side == 1)
			return new int[]{0};
		
		return new int[]{2};
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
