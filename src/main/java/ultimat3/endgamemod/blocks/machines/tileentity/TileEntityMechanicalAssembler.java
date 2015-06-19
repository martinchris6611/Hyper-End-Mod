package ultimat3.endgamemod.blocks.machines.tileentity;

import ultimat3.endgamemod.init.ModTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import cofh.api.energy.EnergyStorage;

public class TileEntityMechanicalAssembler extends TileEntityProcessingMachine {

	public TileEntityMechanicalAssembler() {
		super(20, 200, new ItemStack[17], "container." + ModTileEntities.MECHANICAL_ASSEMBLER_ID, new EnergyStorage(400000));
	}

	@Override
	public boolean canProcessItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot != 16;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if(side == 1) return new int[]{16};
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return slot != 16;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return true;
	}
	

}
