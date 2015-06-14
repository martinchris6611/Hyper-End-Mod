package ultimat3.endgamemod.Multiblock;

import net.minecraft.block.Block;
import ultimat3.endgamemod.EndGame;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

public class TileStoneMultiblock extends TileMultiBlock {
	@Override
    public void doMultiBlockStuff() {

    }

    @Override
    public boolean checkMultiBlockForm() {
        // Checks to see if surrounded by stone
    	/*
    	int x = xCoord;
    	int y = yCoord;
    	int z = zCoord;
    	if ((worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.dirt) && (worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.dirt)) {
    		while((worldObj.getBlock(x, y, z+1) != Blocks.stone && worldObj.getBlock(x, y, z-1) != Blocks.stone)) {
    			if (worldObj.getBlock(x+1, y, z) == Blocks.stone) {
    				++x;
    			}
    			else
    				return false;
    		}
    		while((worldObj.getBlock(x-1, y, z) != Blocks.stone && worldObj.getBlock(x+1, y, z) != Blocks.stone)) {
    			if(worldObj.getBlock(x, y, z+1) == Blocks.stone) {
    				++z;
    			}
    			else
    				return false;
    		}
    		while((worldObj.getBlock(x, y, z+1) != Blocks.stone && worldObj.getBlock(x, y, z-1) != Blocks.stone)) {
    			if (worldObj.getBlock(x-1, y, z) == Blocks.stone) {
    				--x;
    			}
    			else
    				return false;
    		}
    		while((worldObj.getBlock(x-1, y, z) != Blocks.stone && worldObj.getBlock(x+1, y, z) != Blocks.stone)) {
    			if(worldObj.getBlock(x, y, z-1) == Blocks.stone) {
    				--z;
    			}
    			else
    				return false;
    		}
    		while((worldObj.getBlock(x, y, z) != Blocks.dirt && worldObj.getBlock(x, y, z) != Blocks.dirt)) {
    			if (worldObj.getBlock(x+1, y, z) == Blocks.stone) {
    				++x;
    			}
    			else
    				return false;
    		}
    		if (worldObj.getBlock(x+1, y, z) == Blocks.diamond_block) {
    			return true;
    		}
    	}
    	else if ((worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.dirt) && (worldObj.getBlock(xCoord, yCoord, zCoord+1) == Blocks.dirt)) {
    		return false;
    	} else {
    		return false;
    	}
    	return true; */
    	
    	for(int x = xCoord-1; x < xCoord + 1; ++x) {
    		if(worldObj.getBlock(x, yCoord, zCoord) == Blocks.dirt) {
    			
    		}
    		else
    			return false;
    	}
    	return true;
    	
    	/*
        int i = 0;
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord -1; y < yCoord + 2; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    if (worldObj.getBlock(x, y, z) == Blocks.stone)
                        i++;
                }
        return i == 26;*/
    }

    @Override
    public void setupStructure() {
        // replaces stone with diamond blocks
    	/*
    	int x = xCoord;
    	int y = yCoord;
    	int z = zCoord;
    	if ((worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.dirt) && (worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.dirt)) {
    		while((worldObj.getBlock(x, y, z+1) != Blocks.stone && worldObj.getBlock(x, y, z-1) != Blocks.stone)) {
    			if (worldObj.getBlock(x+1, y, z) == Blocks.stone) {
    				worldObj.setBlock(x, y, z, Blocks.diamond_block);
    				++x;
    			}
    		}
    		while((worldObj.getBlock(x-1, y, z) != Blocks.stone && worldObj.getBlock(x+1, y, z) != Blocks.stone)) {
    			if(worldObj.getBlock(x, y, z+1) == Blocks.stone) {
    				worldObj.setBlock(x, y, z, Blocks.diamond_block);
    				++z;
    			}
    		}
    		while((worldObj.getBlock(x, y, z+1) != Blocks.stone && worldObj.getBlock(x, y, z-1) != Blocks.stone)) {
    			if (worldObj.getBlock(x-1, y, z) == Blocks.stone) {
    				worldObj.setBlock(x, y, z, Blocks.diamond_block);
    				--x;
    			}
    		}
    		while((worldObj.getBlock(x-1, y, z) != Blocks.stone && worldObj.getBlock(x+1, y, z) != Blocks.stone)) {
    			if(worldObj.getBlock(x, y, z-1) == Blocks.stone) {
    				worldObj.setBlock(x, y, z, Blocks.diamond_block);
    				--z;
    			}
    		}
    		while((worldObj.getBlock(x, y, z) != Blocks.dirt && worldObj.getBlock(x, y, z) != Blocks.dirt)) {
    			if (worldObj.getBlock(x+1, y, z) == Blocks.stone) {
    				worldObj.setBlock(x, y, z, Blocks.diamond_block);
    				++x;
    			}
    		}
    	}
    	else if ((worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.dirt) && (worldObj.getBlock(xCoord, yCoord, zCoord+1) == Blocks.dirt)) {
    		
    	}*/
    	for(int x = xCoord-1; x < xCoord + 1; ++x) {
    		if(worldObj.getBlock(x, yCoord, zCoord) == Blocks.dirt) {
    			worldObj.setBlock(x, yCoord, zCoord, Blocks.diamond_block);
    		}
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
