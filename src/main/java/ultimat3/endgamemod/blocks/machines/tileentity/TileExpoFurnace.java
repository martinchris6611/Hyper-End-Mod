package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import ultimat3.endgamemod.init.ModTileEntities;
import cofh.api.energy.EnergyStorage;

public class TileExpoFurnace extends TileEntityMachine implements ISidedInventory {

	private static final int initBattery = 400000;
	private static final int batteryPerCoil = 400000;
	
	private static final double itemPerTickCoil = 0.0625;
			
	private static final double initRFperItem = 1;
	private static final double RFgrowthPerCoil = 1.02;
	
	private static final int coilTierMultiplier = 4;	
	
	private int coilNum;
	private int energyUse;
	private double smeltPower;
	private double smeltCurrent;
	private double curRFperItem;
	
	public TileExpoFurnace() {
		super(new ItemStack[13],
				"container." + ModTileEntities.EXPO_FURNACE_ID,
				new EnergyStorage(initBattery));
		coilNum = 0;
		energyUse = 0;
		smeltPower = 0;
		smeltCurrent = 0;
		curRFperItem = 0;
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
	
	public void updateCoil() {
		coilNum = items[0].stackSize;
		if(items[0].getItemDamage() >= 1) coilNum *= coilTierMultiplier;
		if(items[0].getItemDamage() >= 2) coilNum *= coilTierMultiplier;
		smeltPower = itemPerTickCoil * coilNum;
		curRFperItem = Math.pow(RFgrowthPerCoil, coilNum) * initRFperItem;
		energyUse = (int) (smeltPower * curRFperItem);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);	
		smeltCurrent = mainTag.getDouble("smeltCurrent");
		updateCoil();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);
		mainTag.setDouble("smeltCurrent", smeltCurrent);
	}
	
	private int getEnergyUse() {
		return Math.min(energyUse, storage.getEnergyStored());
	}
	
	private boolean smeltSlot(int i) {
		if(items[i] == null) return false;
		ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(items[i]);
		if(stack == null) return false;
		if(items[i+6] == null) {
			items[i+6] = stack.copy();
		} else if(items[i+6].isItemEqual(stack) && items[i+6].stackSize + stack.stackSize <= stack.getMaxStackSize()) {
			this.items[i+6].stackSize += stack.stackSize;
		} else return false;
		if(items[i].stackSize == 1) items[i] = null;
		else items[i].stackSize--;
		return true;
	}

	@Override
	public void updateEntity() {
		if(!worldObj.isRemote) {
			if(items[0] == null) return;
			if(items[0].stackSize != coilNum) {
				updateCoil();
			}
			if(smeltCurrent < 1) {
				int used = getEnergyUse();
				storage.modifyEnergyStored(-used);
				smeltCurrent += used / curRFperItem;
			}
			
			while(smeltCurrent >= 1) {
				for(int i=1; i<=6; i++) {
					if(smeltSlot(i)) smeltCurrent -= 1;
					if(smeltCurrent < 1) break;
				}
			}
		}
	}
	
}
