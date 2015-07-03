package ultimat3.expotech.blocks.machines.tileentity;

import net.minecraft.item.ItemStack;
import ultimat3.expotech.init.ModTileEntities;
import ultimat3.expotech.recipes.MechanicalAssemblerRecipes;
import cofh.api.energy.EnergyStorage;

public class TileMechanicalAssembler extends TileProcessingMachine {

	public TileMechanicalAssembler() {
		super(20, 200, new ItemStack[17], "container." + ModTileEntities.MECHANICAL_ASSEMBLER_ID, new EnergyStorage(400000));
	}
	
	int outputSlot = 16;

	@Override
	public boolean canProcessItem() {
		ItemStack out = MechanicalAssemblerRecipes.getOutput(items);
		if(out == null) return false;
		if(items[outputSlot] == null) return true;
		return	items[outputSlot].isItemEqual(out) &&
				items[outputSlot].stackSize + out.stackSize <= out.getMaxStackSize();
	}

	@Override
	public void processItem() {
		ItemStack out = MechanicalAssemblerRecipes.getOutput(items);
		if(items[outputSlot] == null) {
			items[outputSlot] = out.copy();
		} else {
			items[outputSlot].stackSize += out.stackSize;
		}
		for(int i=0; i<16; i++) {
			if(items[i]!=null) {
				items[i].stackSize--;
				if(items[i].stackSize == 0) items[i] = null;
			}
		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot != 16;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return slot != 16;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 16;
	}
	

}
