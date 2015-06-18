package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.EnergyStorage;

public abstract class TileEntityProcessingMachine extends TileEntityMachine implements ISidedInventory {

	protected TileEntityProcessingMachine(int _itemTime, int _energyUse, ItemStack[] _items,
			String _inventoryName, EnergyStorage _storage) {
		super(_items, _inventoryName, _storage);
		slots = new int[6][items.length];
		isOutput = new boolean[items.length];
		outputSlots = new int[items.length];
		int cnt = 0;
		for(int i=0; i<items.length; i++) if(isOutput[i]) {
			outputSlots[cnt]=i;
			cnt++;
		}
		itemTime = _itemTime;
		energyUse = _energyUse;
		// TODO
	}
	public int processTime;
	public int itemTime;
	public int energyUse;
	
	// slots: [0] - Bottom, [1] - Top etc.
	protected int[][] slots;
	protected boolean[] isOutput;
	protected int[]	outputSlots;

	// Tags
	public static final String TAG_ITEM_TIME = "itemTime";
	public static final String TAG_PROCESS_TIME_LEFT = "processTime";
	
	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);
		storage.readFromNBT(mainTag);
		// reads other things
		this.processTime = mainTag.getInteger(TAG_ITEM_TIME);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);
		storage.writeToNBT(mainTag);
		// Writes other things
		mainTag.setInteger(TAG_ITEM_TIME, processTime);
	}
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote) {
			boolean shouldSave = false;
			if(this.canProcess() && storage.getEnergyStored() >= energyUse) {
				storage.modifyEnergyStored(energyUse);
				this.processTime++;
				if(this.processTime>=itemTime) {
					processTime -= itemTime;
					this.processItem();
					shouldSave = true;
				}
			} else processTime = 0;
			if(shouldSave) this.markDirty();
		}
	}
	abstract public boolean canProcess();
	abstract public void processItem();
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return !isOutput[slot];
	}
	// =========================================================
	// ================= ISidedInventory start =================
	// =========================================================
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return slots[side];
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return !canInsertItem(slot, stack, side);
	}
	
}
