package ultimat3.expotech.blocks.machines.tileentity;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ultimat3.expotech.init.ModItems;
import ultimat3.expotech.init.ModTileEntities;
import ultimat3.expotech.recipes.ExpoFurnaceRecipes;
import cofh.api.energy.EnergyStorage;

public class TileExpoFurnace extends TileMachine implements ISidedInventory {

	private static final int initBattery = 400000;
	private static final int batteryPerCoil = 400000;
	
	private static final double itemPerTickCoil = 0.0625;
			
	private static final double initRFperItem = 1000;
	private static final double RFgrowthPerCoil = 1.007;
	
	private static final int coilTierMultiplier = 4;	
	
	private int coilNum;
	private int energyUse;
	private int coilMeta;
	private int curSlot;
	private double smeltPower;
	private double smeltCurrent;
	private double curRFperItem;
	
	public TileExpoFurnace() {
		super(new ItemStack[13],
				"container." + ModTileEntities.EXPO_FURNACE_ID,
				new EnergyStorage(initBattery));
		coilNum = 0;
		coilMeta = 0;
		energyUse = 0;
		smeltPower = 0;
		smeltCurrent = 0;
		curRFperItem = 0;
		curSlot = 0;
	}

	@Override
	public boolean isItemValidForSlot(int slotID, ItemStack stack) {
		if(stack == null) return false;
		if(slotID == 0) return stack.isItemEqual(ModItems.heatCoilMk1) || stack.isItemEqual(ModItems.heatCoilMk2) || stack.isItemEqual(ModItems.heatCoilMk3);
		if(slotID <= 6) return ExpoFurnaceRecipes.getOutput(stack, getCoilPower()) != null;
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if(side == 1) return new int[] {0, 1, 2, 3, 4, 5, 6};
		return new int[] {7, 8, 9, 10, 11, 12};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot >= 7 && slot <= 12;
	}
	
	public void updateCoil() {
		coilNum = items[0].stackSize;
		coilMeta = items[0].getItemDamage();
		if(coilMeta >= 1) coilNum *= coilTierMultiplier;
		if(coilMeta >= 2) coilNum *= coilTierMultiplier;
		storage.setCapacity(initBattery + coilNum * batteryPerCoil);
		smeltPower = itemPerTickCoil * coilNum;
		curRFperItem = Math.pow(RFgrowthPerCoil, coilNum) * initRFperItem;
		energyUse = (int) (smeltPower * curRFperItem);
		if(coilMeta >= 1) coilNum /= coilTierMultiplier;
		if(coilMeta >= 2) coilNum /= coilTierMultiplier;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);	
		smeltCurrent = mainTag.getDouble("smeltCurrent");
		curSlot = mainTag.getInteger("curSlot");
		updateCoil();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);
		mainTag.setDouble("smeltCurrent", smeltCurrent);
		mainTag.setInteger("curSlot", curSlot);
	}
	
	private int getEnergyUse() {
		return Math.min(energyUse, storage.getEnergyStored());
	}
	
	private int getCoilPower() {
		return (storage.getMaxEnergyStored() - initBattery) / batteryPerCoil;
	}
	
	private boolean smeltSlot(int i) {
		if(items[i] == null) return false;
		ItemStack stack = ExpoFurnaceRecipes.getOutput(items[i], getCoilPower());
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
			if(items[0].stackSize != coilNum || items[0].getItemDamage() != coilMeta) {
				updateCoil();
			}
			if(smeltCurrent < 1) {
				int used = getEnergyUse();
				storage.modifyEnergyStored(-used);
				smeltCurrent += used / curRFperItem;
			}
			
			while(smeltCurrent >= 1) {
				boolean emptyFlag = true;
				for(int i=1; i<=6; i++) {
					if(smeltSlot(i))  {
						setActive();
						smeltCurrent -= 1;
						emptyFlag = false;
					}
					if(smeltCurrent < 1) break;
				}
				if(emptyFlag) {
					setInactive();
					break;
				}
			}
		}
	}
	
}
