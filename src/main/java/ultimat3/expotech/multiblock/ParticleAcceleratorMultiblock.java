package ultimat3.expotech.multiblock;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ultimat3.expotech.init.ModBlocks;
import ultimat3.expotech.init.ModTileEntities;
import ultimat3.expotech.recipes.MechanicalAssemblerRecipes;
import cofh.api.energy.EnergyStorage;
//MIGHT REMOVE

public class ParticleAcceleratorMultiblock extends TileMultiBlock {

	boolean xDirection 	= false;
	boolean zDirection 	= false;
	int multiSize 		= 0;
	
	public ParticleAcceleratorMultiblock() {
		super(1000, 1000000, new ItemStack[10], "container." + ModTileEntities.PARTICLE_ACCELERATOR_ID, new EnergyStorage(10000000));
	}
	
	@Override
    public void doMultiBlockStuff() {
		
    }
	
	//Function called in checking multi block form to see if Magnets are in correct location when moving along X Axis
	public boolean checkXMagnets(int x, int y, int z) {
		
		//Check if blocks above and below tube are magnets
		if(worldObj.getBlock(x, y+1, z) != ModBlocks.blockElectroMagnet || worldObj.getBlock(x, y-1, z) != ModBlocks.blockElectroMagnet) 
			return true;
		
		//Check if blocks + and - 1 from tubing in Z direction are magnets
		if(worldObj.getBlock(x, y, z+1) != ModBlocks.blockElectroMagnet || worldObj.getBlock(x, y, z-1) != ModBlocks.blockElectroMagnet)
			return true;
		
		//We return false to signify that it is error free, that is magnets are correct
		return false;
	}
	
	//Function called in checking multi block form to see if Magnets are in correct location when moving along Z Axis
	public boolean checkZMagnets(int x, int y, int z) {
		
		//Check if blocks above and below tube are magnets
		if(worldObj.getBlock(x, y+1, z) != ModBlocks.blockElectroMagnet || worldObj.getBlock(x, y-1, z) != ModBlocks.blockElectroMagnet) 
			return true;
		
		//Check if blocks + and - 1 from tubing in X direction are magnets
		if(worldObj.getBlock(x+1, y, z) != ModBlocks.blockElectroMagnet || worldObj.getBlock(x-1, y, z) != ModBlocks.blockElectroMagnet)
			return true;
		
		//We return false to signify that it is error free, that is magnets are correct
		return false;
	}
	
    @Override
    public boolean checkMultiBlockForm() {
       
    	//If the proton lasers are in the x direction when compared to controller then this is the Test we do to check the multiblock
    	if(worldObj.getBlock(xCoord-1, yCoord, zCoord) == ModBlocks.blockProtonLaser && worldObj.getBlock(xCoord+1, yCoord, zCoord) == ModBlocks.blockProtonLaser) {
    		xDirection = true;
    		boolean altDirection = false;
    		
    		multiSize = 3;
    		int x = xCoord+2;
    		int y = yCoord;
    		int z = zCoord;
    		
    		//Check for particle tubing and magnets until the first turn of the pipe
    		for (x++; worldObj.getBlock(x, y, z+1) != ModBlocks.blockParticleTube && worldObj.getBlock(x, y, z-1) != ModBlocks.blockParticleTube; ++x) {
    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
    				if(checkXMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    			multiSize += 5;
    		}
    		
    		//This checks for the tubing at the corner
    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
    			return false;
    		
    		//Since there was a corner, increment size by one
    		++multiSize;
    		
    		if (worldObj.getBlock(x, y, z-1) == ModBlocks.blockParticleTube)
    			altDirection = true;
    		
    		if (!altDirection) {
	    		++z;
	    		
	    		//Checks for particle tubing and magnets until the next turn of the pipe
	    		for (z++; worldObj.getBlock(x-1, y, z) != ModBlocks.blockParticleTube && worldObj.getBlock(x+1, y, z) != ModBlocks.blockParticleTube; ++z) {
	    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkZMagnets(x, y, z))
	    					return false;
	    			}
	    			else
	    				return false;
	    			multiSize += 5;
	    		}
	    		
	    		//Checks for the next corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increment size by one due to the corner existing
	    		++multiSize;
	    		
	    		--x;
	    		
	    		//Checks for particle tubing and magnets until the next turn of the pipe as well as the Detector
	    		for (x--; worldObj.getBlock(x, y, z-1) != ModBlocks.blockParticleTube && worldObj.getBlock(x, y, z+1) != ModBlocks.blockParticleTube; --x) {
	    			if (x == xCoord && y == yCoord) {
	    				if (worldObj.getBlock(x, y, z) != ModBlocks.blockDetector)
	    					return false;
	    				++multiSize;
	    			} else if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkXMagnets(x, y, z))
	    					return false;
	    				multiSize += 5;
	    			}
	    			else 
	    				return false;
	    		}
	    		
	    		//Checks for Third Corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments size due to corner
	    		++multiSize;
	    		
	    		--z;
	    		
	    		//Checks for particle tubing and magnets until next turn of the pipe as well as the detector
	    		for (z--; worldObj.getBlock(x+1, y, z) != ModBlocks.blockParticleTube && worldObj.getBlock(x-1, y, z) != ModBlocks.blockParticleTube; --z) {
	    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkZMagnets(x, y, z))
	    					return false;
	    			}
	    			else
	    				return false;
	    			multiSize += 5;
	    		}
	    		
	    		//Checks for the 4th and final corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments size for the last corner
	    		++multiSize;
	    		
	    		++x;
	    		
	    		//Checks for tubing and magnets until you get back to the original controller location
	    		for (x++; worldObj.getBlock(x+1, y, z) != worldObj.getBlock(xCoord, yCoord, zCoord); ++x) {
	    			if (worldObj.getBlock(x,y,z) == ModBlocks.blockParticleTube) {
	    				if(checkXMagnets(x, y, z))
	    					return false;
	    				multiSize += 5;
	    			}
	    			else
	    				return false;
	    		}
	    		
	    		return true;
    		} else {
    			--z;
	    		
    			//Checks for tubing and magnets until the next corner
	    		for (z--; worldObj.getBlock(x-1, y, z) != ModBlocks.blockParticleTube; --z) {
	    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkZMagnets(x, y, z))	//Check for magnets in plus and minus y and x directions
	    					return false;
	    			}
	    			else
	    				return false;
	    			multiSize += 5;
	    		}
	    		
	    		//Checks to make sure there is a corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments due to corner
	    		++multiSize;
	    		
	    		--x;
	    		
	    		//Checks for tubing and magnets until next corner
	    		for (x--; worldObj.getBlock(x, y, z+1) != ModBlocks.blockParticleTube; --x) {
	    			if (x == xCoord && y == yCoord) {
	    				if (worldObj.getBlock(x, y, z) != ModBlocks.blockDetector)
	    					return false;
	    				++multiSize;
	    			} else if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkXMagnets(x, y, z))
	    					return false;
	    				multiSize += 5;
	    			}
	    			else 
	    				return false;
	    		}
	    		
	    		//Checks for corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments size due to corner
	    		++multiSize;
	    		
	    		++z;
	    		
	    		//Checks for tubing and magnets until next corner
	    		for (z++; worldObj.getBlock(x+1, y, z) != ModBlocks.blockParticleTube; ++z) {
	    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkZMagnets(x, y, z))
	    					return false;
	    			}
	    			else
	    				return false;
	    			multiSize += 5;
	    		}
	    		
	    		//Checks for a valid corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments size due to corner
	    		++multiSize;
	    		
	    		++x;
	    		
	    		//Checks for magnets and tubing until you reach original controller location
	    		for (x++; worldObj.getBlock(x+1, y, z) != worldObj.getBlock(xCoord, yCoord, zCoord); ++x) {
	    			if (worldObj.getBlock(x,y,z) == ModBlocks.blockParticleTube) {
	    				if(checkXMagnets(x, y, z))
	    					return false;
	    				multiSize += 5;
	    			}
	    			else
	    				return false;
	    		}
	    		
	    		return true;
    		}
    	}
    	else if (worldObj.getBlock(xCoord, yCoord, zCoord-1) == ModBlocks.blockProtonLaser && worldObj.getBlock(xCoord, yCoord, zCoord+1) == ModBlocks.blockProtonLaser) {
    		zDirection = true;
    		
    		boolean altDirection = false;
    		
    		multiSize = 3;
    		
    		int x = xCoord;
    		int y = yCoord;
    		int z = zCoord+2;
    		
    		//Checks for magnets and tubing until first corner
    		for (z++; worldObj.getBlock(x+1, y, z) != ModBlocks.blockParticleTube && worldObj.getBlock(x-1, y, z) != ModBlocks.blockParticleTube; ++z) {
    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
    				if(checkZMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    			multiSize += 5;
    		}
    		
    		//Checks for valid corner
    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
    			return false;
    		
    		//Increments due to corner
    		++multiSize;
    		
    		if (worldObj.getBlock(x-1, y, z) == ModBlocks.blockParticleTube)
    			altDirection = true;
    		
    		if (!altDirection) {
	    		++x;
	    		
	    		//Checks for magnets/tubing until next corner
	    		for (x++; worldObj.getBlock(x, y, z-1) != ModBlocks.blockParticleTube; ++x) {
	    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkXMagnets(x, y, z))
	    					return false;
	    			}
	    			else
	    				return false;
	    			multiSize += 5;
	    		}
	    		
	    		//Checks for valid corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments size due to corner
	    		++multiSize;
	    		
	    		--z;
	    		
	    		//Checks for magnets and tubing as well as detector
	    		for (z--; worldObj.getBlock(x-1, y, z) != ModBlocks.blockParticleTube; --z) {
	    			if (z == zCoord && y == yCoord) {
	    				if (worldObj.getBlock(x, y, z) != ModBlocks.blockDetector)
	    					return false;
	    				++multiSize;
	    			} else if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkZMagnets(x, y, z))
	    					return false;
	    				multiSize += 5;
	    			}
	    			else 
	    				return false;
	    		}
	    		
	    		//Checks for valid corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments due to corner
	    		++multiSize;
	    		
	    		--x;
	    		
	    		//Checks for tube/magnets until next corner
	    		for (x--; worldObj.getBlock(x, y, z+1) != ModBlocks.blockParticleTube; --x) {
	    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkXMagnets(x, y, z))
	    					return false;
	    			}
	    			else
	    				return false;
	    			multiSize += 5;
	    		}
	    		
	    		//Checks for valid corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments due to valid corner
	    		++multiSize; 
	    		
	    		++z;
	    		
	    		//Checks for tube and magnets until back at master coordinates for controller
	    		for (z++; worldObj.getBlock(x, y, z+1) != worldObj.getBlock(xCoord, yCoord, zCoord); ++z) {
	    			if (worldObj.getBlock(x,y,z) == ModBlocks.blockParticleTube) {
	    				if(checkZMagnets(x, y, z))
	    					return false;
	    				multiSize += 5;
	    			}
	    			else
	    				return false;
	    		}
	    		
	    		return true;
	    	} else {
	    		--x;
	    		
	    		//Checks for valid tube/magnets until corner
	    		for (x--; worldObj.getBlock(x, y, z-1) != ModBlocks.blockParticleTube; --x) {
	    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkXMagnets(x, y, z))	//Check for magnets in plus and minus y and x directions
	    					return false;
	    			}
	    			else
	    				return false;
	    			multiSize += 5;
	    		}
	    		
	    		//Checks for valid corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments size due to corner
	    		++multiSize; 
	    		
	    		--z;
	    		
	    		//Checks for magnets/tube until corner as well as detector
	    		for (z--; worldObj.getBlock(x+1, y, z) != ModBlocks.blockParticleTube; --z) {
	    			if (z == zCoord && y == yCoord) {
	    				if (worldObj.getBlock(x, y, z) != ModBlocks.blockDetector)
	    					return false;
	    				++multiSize;
	    			} else if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkZMagnets(x, y, z))
	    					return false;
	    				multiSize += 5;
	    			}
	    			else 
	    				return false;
	    		}
	    		
	    		//checks for valid corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments size due to corner
	    		++multiSize;
	    		
	    		++x;
	    		
	    		//Checks for magnets/tube until corner
	    		for (x++; worldObj.getBlock(x, y, z+1) != ModBlocks.blockParticleTube; ++x) {
	    			if(worldObj.getBlock(x, y, z) == ModBlocks.blockParticleTube) {
	    				if(checkXMagnets(x, y, z))
	    					return false;
	    			}
	    			else
	    				return false;
	    			multiSize += 5;
	    		}
	    		
	    		//Checks for valid corner
	    		if (worldObj.getBlock(x,y,z) != ModBlocks.blockParticleTube)
	    			return false;
	    		
	    		//Increments due to valid corner
	    		++multiSize;
	    		
	    		++z;
	    		
	    		//Checks for magnets/tube until master block coordinates
	    		for (z++; worldObj.getBlock(x, y, z+1) != worldObj.getBlock(xCoord, yCoord, zCoord); ++z) {
	    			if (worldObj.getBlock(x,y,z) == ModBlocks.blockParticleTube) {
	    				if(checkZMagnets(x, y, z))
	    					return false;
	    				multiSize += 5;
	    			}
	    			else
	    				return false;
	    		}
	    		
	    		return true;
	    	}
    	}
    	
    	return false;
    }

    @Override
    public void setupStructure() {
        // replaces stone with diamond blocks
    	this.setIsFormed(true);
    	this.setSize(multiSize);
    }

    @Override
    public void resetStructure() {
    	this.setIsFormed(false);
    	this.setSize(0);
    }

    @Override
    public void masterWriteToNBT(NBTTagCompound tag) {
    	// this is impressive
    }

    @Override
    public void masterReadFromNBT(NBTTagCompound tag) {

    }
    
	@Override
	public boolean canProcessItem() {
		//ItemStack out = ParticleAcceleratorRecipes.getOutput(items);
		//if(out == null) return false;
		//if(items[outputSlot] == null) return true;
		//return	items[outputSlot].isItemEqual(out) &&
		//		items[outputSlot].stackSize + out.stackSize <= out.getMaxStackSize();
		return false;
	}

	@Override
	public void processItem() {
		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}
}