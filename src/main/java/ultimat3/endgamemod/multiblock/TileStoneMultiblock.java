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

    @Override
    public boolean checkMultiBlockForm() {
       
    	if(worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.dirt && worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.dirt) {
    		xDirection = true;
    		int x = xCoord+2;
    		int y = yCoord;
    		int z = zCoord;
    		for (x++; worldObj.getBlock(x, y, z+1) != Blocks.stone; ++x) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				//DO NOTHING
    			}
    			else
    				return false;
    		}
    		
    		++z;
    		
    		for (z++; worldObj.getBlock(x-1, y, z) != Blocks.stone; ++z) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				//DO NOTHING FOR NOW
    			}
    			else
    				return false;
    		}

    		
    		--x;
    		
    		for (x--; worldObj.getBlock(x, y, z-1) != Blocks.stone; --x) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				//DO NOTHING FOR NOW
    			}
    			else 
    				return false;
    		}
    		
    		--z;
    		
    		for (z--; worldObj.getBlock(x+1, y, z) == Blocks.stone; --z) {
    			if(worldObj.getBlock(x, y, z) == Blocks.stone) {
    				//DO NOTHING FOR NOW
    			}
    			else
    				return false;
    		}
    		
    		return true;
    	}
    	else if (worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.dirt && worldObj.getBlock(xCoord, yCoord, zCoord+1) == Blocks.dirt) {
    		zDirection = true;
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
