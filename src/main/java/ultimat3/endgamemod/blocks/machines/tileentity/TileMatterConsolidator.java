package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.item.ItemStack;
import ultimat3.endgamemod.init.ModTileEntities;
import ultimat3.endgamemod.recipes.MatterConsolidatorRecipes;
import cofh.api.energy.EnergyStorage;

public class TileMatterConsolidator extends TileEntityProcessingMachine {

	public TileMatterConsolidator() {
		super(1, 640, new ItemStack[4],
			"container." + ModTileEntities.MATTER_CONSOLIDATOR_ID,
			new EnergyStorage(600000));
	}

	private static final int outputSlot = 3;
	
	@Override
	public boolean canProcessItem() {
		ItemStack out = MatterConsolidatorRecipes.getOutput(items[0], items[1], items[2]);
		if(out == null) return false;
		if(items[outputSlot] == null) return true;
		return	items[outputSlot].isItemEqual(out) &&
				items[outputSlot].stackSize + out.stackSize <= out.getMaxStackSize();
	}

	@Override
	public void processItem() {
		if(items[outputSlot] == null) {
			items[outputSlot] = MatterConsolidatorRecipes.getOutput(items[0], items[1], items[2]).copy();
		} else {
			items[outputSlot].stackSize += MatterConsolidatorRecipes.getOutput(items[0], items[1], items[2]).stackSize;
		}
		items[0].stackSize--;
		if(items[0].stackSize == 0) items[0] = null;
		items[1].stackSize--;
		if(items[1].stackSize == 0) items[1] = null;
		items[2].stackSize--;
		if(items[2].stackSize == 0) items[2] = null;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot!=outputSlot;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if(side == 1)
			return new int[]{0, 1, 2};
		return new int[]{3};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return this.isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot==outputSlot;
	}

}
