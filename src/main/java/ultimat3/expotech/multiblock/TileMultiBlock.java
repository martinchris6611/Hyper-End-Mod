package ultimat3.expotech.multiblock;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ultimat3.expotech.blocks.machines.tileentity.TileProcessingMachine;
import cofh.api.energy.EnergyStorage;

public abstract class TileMultiBlock extends TileProcessingMachine {
	 
	
	protected TileMultiBlock(int _itemTime, int _energyUse, ItemStack[] _items,
			String _inventoryName, EnergyStorage _storage) {
		super(_itemTime, _energyUse, _items, _inventoryName, _storage);
	}
	
	
		private boolean hasMaster, isMaster, isFormed;
	    private int masterX, masterY, masterZ, size;
	    
	    @Override
	    public void updateEntity() {
	        super.updateEntity();
	        if (!worldObj.isRemote) {
	            if (hasMaster()) { 
	                if (isMaster() && checkMultiBlockForm()) {
	                    doMultiBlockStuff();
	                }
	                else
	                	reset();
	            } else {
	                // Constantly check if structure is formed until it is.
	                if (checkMultiBlockForm())
	                    setupStructure();
	                else
	                	reset();
	            }
	        }
	    }

	    /**
	     * Stuff the multiblock will do when formed
	     */
	    public abstract void doMultiBlockStuff();

	    /**
	     * Check that structure is properly formed
	     */
	    public abstract boolean checkMultiBlockForm();

	    /**
	     * Setup all the blocks in the structure
	     */
	    public abstract void setupStructure();

	    /**
	     * Reset method to be run when the master is gone or tells them to
	     */
	    public void reset() {
	        masterX = 0;
	        masterY = 0;
	        masterZ = 0;
	        size = 0;
	        hasMaster = false;
	        isMaster = false;
	        isFormed = false;
	    }

	    /**
	     * Check that the master exists
	     */
	    public boolean checkForMaster() {
	        TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
	        return (tile != null && (tile instanceof TileMultiBlock));
	    }

	    /**
	     * Reset all the parts of the structure
	     */
	    public abstract void resetStructure();

	    public abstract void masterWriteToNBT(NBTTagCompound tag);

	    public abstract void masterReadFromNBT(NBTTagCompound tag);

	    @Override
	    public void writeToNBT(NBTTagCompound data) {
	        super.writeToNBT(data);
	        data.setInteger("masterX", masterX);
	        data.setInteger("masterY", masterY);
	        data.setInteger("masterZ", masterZ);
	        data.setInteger("size", size);
	        data.setBoolean("hasMaster", hasMaster);
	        data.setBoolean("isMaster", isMaster);
	        data.setBoolean("isFormed", isFormed);
	        if (hasMaster() && isMaster())
	            masterWriteToNBT(data);
	    }

	    @Override
	    public void readFromNBT(NBTTagCompound data) {
	        super.readFromNBT(data);
	        masterX = data.getInteger("masterX");
	        masterY = data.getInteger("masterY");
	        masterZ = data.getInteger("masterZ");
	        size = data.getInteger("size");
	        hasMaster = data.getBoolean("hasMaster");
	        isMaster = data.getBoolean("isMaster");
	        isFormed = data.getBoolean("isFormed");
	        if (hasMaster() && isMaster())
	            masterReadFromNBT(data);
	    }
	    
	    public int getSize() {
	    	return size;
	    }
	    
	    public boolean isFormed() {
	    	return isFormed;
	    }
	    
	    public boolean hasMaster() {
	        return hasMaster;
	    }

	    public boolean isMaster() {
	        return isMaster;
	    }

	    public int getMasterX() {
	        return masterX;
	    }

	    public int getMasterY() {
	        return masterY;
	    }

	    public int getMasterZ() {
	        return masterZ;
	    }
	    
	    public void setSize(int i) {
	    	size = i;
	    }
	    
	    public void setHasMaster(boolean bool) {
	        hasMaster = bool;
	    }

	    public void setIsMaster(boolean bool) {
	        isMaster = bool;
	    }
	    
	    public void setIsFormed(boolean bool) {
	    	isFormed = bool;
	    }

	    public void setMasterCoords(int x, int y, int z) {
	        masterX = x;
	        masterY = y;
	        masterZ = z;
	    }
}
