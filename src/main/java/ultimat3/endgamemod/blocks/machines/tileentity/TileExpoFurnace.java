package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import ultimat3.endgamemod.init.ModTileEntities;
import cofh.api.energy.EnergyStorage;

public class TileExpoFurnace extends TileEntityMachine implements ISidedInventory {

	private static final int initBattery = 400000;
	private static final int batteryPerCoil = 400000;
	
	private static final double itemPerTickCoil = 0.0625;
			
	private static final double initRFperItem = 1;
	private static final double RFgrowthPerCoil = 1.02;
	
	private static final int coilTierMultiplier = 4;	
	
	private final int coilNum;
	private final int energyUse;
	private final double smeltPower;
	private final double smeltCurrent;
	
	public TileExpoFurnace() {
		super(new ItemStack[9],
				"container." + ModTileEntities.EXPO_FURNACE_ID,
				new EnergyStorage(initBattery));
		coilNum = 0;
		energyUse = 0;
		smeltPower = 0;
		smeltCurrent = 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if(slot == 13) return stack.getItem() == Items.stick;
		if(slot >= 6) return false;
		return FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if(side == 1) return new int[] {0, 1, 2, 3, 4, 5};
		return new int[] {6, 7, 8, 9, 10, 11};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot >= 6 && slot < 12;
	}

	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		
	}
	
}
