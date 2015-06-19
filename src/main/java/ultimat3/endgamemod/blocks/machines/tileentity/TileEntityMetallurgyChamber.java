package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ultimat3.endgamemod.init.ModItems;
import ultimat3.endgamemod.init.ModRecipes;
import ultimat3.endgamemod.init.ModTileEntities;
import cofh.api.energy.EnergyStorage;

public class TileEntityMetallurgyChamber extends TileEntityFueledMachine {

	
	public TileEntityMetallurgyChamber() {
		super(20, 1, 20, 10240, new ItemStack[3],
				"_container." + ModTileEntities.METALLURGY_CHAMBER_ID,
				new EnergyStorage(6114000));
	}

	public boolean canProcessItem() {
		if (this.items[0] == null)
			return false;
		
		ItemStack itemstack = ModRecipes.metallurgy().getResult(this.items[0]);
		
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
		
		ItemStack itemstack = ModRecipes.metallurgy().getResult(this.items[0]);
		
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
		
		// only allow Thermite to be a fuel
		if (slot == 1)
			return stack.isItemEqual(new ItemStack(ModItems.itemThermite));
		
		// Can only input items with a metallurgy result on the item input
		return ModRecipes.metallurgy().getResult(stack) != null;
		// return true;
	}
	
	// =========================================================
	// ================= ISidedInventory start =================
	// =========================================================
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
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
