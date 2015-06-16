package ultimat3.endgamemod.multiblock;

import net.minecraft.block.Block;
import ultimat3.endgamemod.EndGame;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

public class TileStoneMultiblock extends TileMultiBlock {

	boolean xDirection = false;
	boolean zDirection = false;
	
	@Override
    public void doMultiBlockStuff() {

    }

	//Function called in checking multi block form to see if Magnets are in correct location when moving along X Axis
	public boolean checkXMagnets(int x, int y, int z) {
		
		//Check if blocks above and below tube are magnets
		if(worldObj.getBlock(x, y+1, z) != Blocks.emerald_block || worldObj.getBlock(x, y-1, z) != Blocks.emerald_block) 
			return true;
		
		//Check if blocks + and - 1 from tubing in Z direction are magnets
		if(worldObj.getBlock(x, y, z+1) != Blocks.emerald_block || worldObj.getBlock(x, y, z-1) != Blocks.emerald_block)
			return true;
		
		//We return false to signify that it is error free, that is magnets are correct
		return false;
	}
	
	//Function called in checking multi block form to see if Magnets are in correct location when moving along Z Axis
	public boolean checkZMagnets(int x, int y, int z) {
		
		//Check if blocks above and below tube are magnets
		if(worldObj.getBlock(x, y+1, z) != Blocks.emerald_block || worldObj.getBlock(x, y-1, z) != Blocks.emerald_block) 
			return false;
		
		//Check if blocks + and - 1 from tubing in X direction are magnets
		if(worldObj.getBlock(x+1, y, z) != Blocks.emerald_block || worldObj.getBlock(x-1, y, z) != Blocks.emerald_block)
			return false;
		
		//We return false to signify that it is error free, that is magnets are correct
		return false;
	}
	
    @Override
    public boolean checkMultiBlockForm() {
       
    	if(worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.dirt && worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.dirt) {
    		xDirection = true;
    		int x = xCoord+2;
    		int y = yCoord;
    		int z = zCoord;
    		for (x++; worldObj.getBlock(x, y, z+1) != Blocks.stone && worldObj.getBlock(x, y, z-1) != Blocks.stone; ++x) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				if(checkXMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    		}
    		
    		if (worldObj.getBlock(x,y,z) != Blocks.stone)
    			return false;
    		
    		++z;
    		
    		for (z++; worldObj.getBlock(x-1, y, z) != Blocks.stone && worldObj.getBlock(x+1, y, z) != Blocks.stone; ++z) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				if(checkZMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    		}

    		if (worldObj.getBlock(x,y,z) != Blocks.stone)
    			return false;
    		
    		--x;
    		
    		for (x--; worldObj.getBlock(x, y, z-1) != Blocks.stone && worldObj.getBlock(x, y, z+1) != Blocks.stone; --x) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				if(checkXMagnets(x, y, z))
    					return false;
    			}
    			else 
    				return false;
    		}
    		
    		if (worldObj.getBlock(x,y,z) != Blocks.stone)
    			return false;
    		
    		--z;
    		
    		for (z--; worldObj.getBlock(x+1, y, z) != Blocks.stone && worldObj.getBlock(x-1, y, z) != Blocks.stone; --z) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				if(checkZMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    		}
    		
    		if (worldObj.getBlock(x,y,z) != Blocks.stone)
    			return false;
    		
    		++x;
    		
    		for (x++; worldObj.getBlock(x+1, y, z) != worldObj.getBlock(xCoord, yCoord, zCoord); ++x) {
    			if (worldObj.getBlock(x,y,z) == Blocks.stone) {
    				if(checkXMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    		}
    		
    		return true;
    	}
    	else if (worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.dirt && worldObj.getBlock(xCoord, yCoord, zCoord+1) == Blocks.dirt) {
    		zDirection = true;
    		
    		int x = xCoord;
    		int y = yCoord;
    		int z = zCoord+2;
    		for (z++; worldObj.getBlock(x+1, y, z) != Blocks.stone && worldObj.getBlock(x-1, y, z) != Blocks.stone; ++z) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				if(checkZMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    		}
    		
    		if (worldObj.getBlock(x,y,z) != Blocks.stone)
    			return false;
    		
    		++x;
    		
    		for (x++; worldObj.getBlock(x, y, z-1) != Blocks.stone && worldObj.getBlock(x, y, z+1) != Blocks.stone; ++x) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				if(checkXMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    		}

    		if (worldObj.getBlock(x,y,z) != Blocks.stone)
    			return false;
    		
    		--z;
    		
    		for (z--; worldObj.getBlock(x-1, y, z) != Blocks.stone && worldObj.getBlock(x+1, y, z) != Blocks.stone; --z) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				if(checkZMagnets(x, y, z))
    					return false;
    			}
    			else 
    				return false;
    		}
    		
    		if (worldObj.getBlock(x,y,z) != Blocks.stone)
    			return false;
    		
    		--x;
    		
    		for (x--; worldObj.getBlock(x, y, z+1) != Blocks.stone && worldObj.getBlock(x, y, z-1) != Blocks.stone; --x) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				if(checkXMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    		}
    		
    		if (worldObj.getBlock(x,y,z) != Blocks.stone)
    			return false;
    		
    		++z;
    		
    		for (z++; worldObj.getBlock(x, y, z+1) != worldObj.getBlock(xCoord, yCoord, zCoord); ++z) {
    			if (worldObj.getBlock(x,y,z) == Blocks.stone) {
    				if(checkZMagnets(x, y, z))
    					return false;
    			}
    			else
    				return false;
    		}
    		
    		return true;
    	}
    	/*
        int i = 0;
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord -1; y < yCoord + 2; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    if (worldObj.getBlock(x, y, z) == Blocks.stone)
                        i++;
                }*/
        //return i == 26;
    	return false;
    }

    @Override
    public void setupStructure() {
        // replaces stone with diamond blocks
    	if(xDirection) {
    		if(worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.dirt && worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.dirt)
    			worldObj.setBlock(xCoord-1, yCoord, zCoord, Blocks.diamond_block);
    	}
    	else if (zDirection) {
    		if (worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.dirt && worldObj.getBlock(xCoord, yCoord, zCoord+1) == Blocks.dirt)
    			worldObj.setBlock(xCoord, yCoord, zCoord-1, Blocks.diamond_block);
    	}
    	/*
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord -1; y < yCoord + 2; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    if (worldObj.getBlock(x, y, z) == Blocks.stone)
                        worldObj.setBlock(x, y, z, Blocks.diamond_block);
                }*/
    }

    @Override
    public void resetStructure() {

    }

    @Override
    public void masterWriteToNBT(NBTTagCompound tag) {

    }

    @Override
    public void masterReadFromNBT(NBTTagCompound tag) {

    }
}
