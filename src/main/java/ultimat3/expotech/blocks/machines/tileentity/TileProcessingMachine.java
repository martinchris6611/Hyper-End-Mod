package ultimat3.expotech.blocks.machines.tileentity;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.EnergyStorage;

public abstract class TileProcessingMachine extends TileMachine implements ISidedInventory {

	protected TileProcessingMachine(int _itemTime, int _energyUse, ItemStack[] _items,
			String _inventoryName, EnergyStorage _storage) {
		super(_items, _inventoryName, _storage);
		itemTime = _itemTime;
		energyUse = _energyUse;
	}
	public int processTime;
	public int itemTime;
	public int energyUse;

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
			if(this.canProcess()) {
				setActive();
				storage.modifyEnergyStored(getEnergyUse());
				this.processTime++;
				if(this.processTime>=itemTime) {
					processTime -= itemTime;
					this.processItem();
					shouldSave = true;
				}
			} else  {
				processTime = 0;
				setInactive();
			}
			if(shouldSave) this.markDirty();
		}
	}
	
	// Processing
	public boolean canProcess() {
		return this.canProcessItem() && storage.getEnergyStored() >= getEnergyUse();
	}
	
	public int getEnergyUse() {
		return energyUse;
	}
	
	abstract public boolean canProcessItem();
	abstract public void processItem();
	
	//Interface
	@Override
	abstract public boolean isItemValidForSlot(int slot, ItemStack stack);
	
	//ISidedInventory
	@Override
	abstract public int[] getAccessibleSlotsFromSide(int side);	
	@Override
	abstract public boolean canInsertItem(int slot, ItemStack stack, int side);	
	@Override
	abstract public boolean canExtractItem(int slot, ItemStack stack, int side);
	
}
