package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ultimat3.endgamemod.fluids.tanks.TileUltimat3Tank;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

abstract public class TileFluidEnergy extends TileUltimat3Tank implements IFluidHandler, IEnergyHandler  {

	public static final String	TAG_ENERGY				= "energy";

	EnergyStorage storage;
	
	public TileFluidEnergy() {
		super(64000);
		storage = new EnergyStorage(64000);
	}
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote) {
			process();
		}
	}
	
	abstract public void process();
	
	// ENERGY HANDLERS
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);
		storage.setEnergyStored(mainTag.getInteger(TAG_ENERGY));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);
		mainTag.setInteger(TAG_ENERGY, storage.getEnergyStored());
	}
	

}
