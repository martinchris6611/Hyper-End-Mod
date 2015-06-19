package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.EnergyStorage;

abstract public class TileEntityFueledMachine extends
		TileEntityProcessingMachine {

	public int fuelTime;
	public int burnTime;
	public int fuelSlot;

	public static final String TAG_FUEL_TIME = "fuelTime";

	protected TileEntityFueledMachine(int _burnTime, int _fuelSlot, int _itemTime, int _energyUse,
			ItemStack[] _items, String _inventoryName, EnergyStorage _storage) {
		super(_itemTime, _energyUse, _items, _inventoryName, _storage);
		burnTime = _burnTime;
		fuelSlot = _fuelSlot;
		fuelTime = 0;
		
	}

	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);
		// reads other things
		fuelTime = mainTag.getInteger(TAG_FUEL_TIME);
	}

	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);
		// Writes other things
		mainTag.setInteger(TAG_FUEL_TIME, fuelTime);
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (fuelTime > 0)
				--fuelTime;
			else if (canProcess() && items[fuelSlot] != null) {
				if (items[fuelSlot].stackSize == 1)
					items[fuelSlot] = null;
				else
					items[fuelSlot].stackSize--;
				fuelTime += burnTime;
			} else
				return;
			super.updateEntity();
		}
	}

}
