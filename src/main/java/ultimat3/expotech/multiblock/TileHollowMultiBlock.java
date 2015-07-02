package ultimat3.expotech.multiblock;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cofh.api.energy.EnergyStorage;

public class TileHollowMultiBlock extends TileMultiBlock {

	    protected TileHollowMultiBlock(int _itemTime, int _energyUse,
			ItemStack[] _items, String _inventoryName, EnergyStorage _storage) {
		super(_itemTime, _energyUse, _items, _inventoryName, _storage);
	}

		@Override
	    public void doMultiBlockStuff() {
	        // Sets diamond block 6 blocks above the master
	        if (worldObj.isAirBlock(xCoord, yCoord + 6, zCoord))
	            worldObj.setBlock(xCoord, yCoord + 6, zCoord, Blocks.diamond_block);
	    }

	    @Override
	    public void masterWriteToNBT(NBTTagCompound tag) {
	    }

	    @Override
	    public void masterReadFromNBT(NBTTagCompound tag) {
	    }

	    @Override
	    public boolean checkMultiBlockForm() {
	        int i = 0;
	        // Scan a 3x3x3 area, starting with the bottom left corner
	        for (int x = xCoord - 1; x < xCoord + 2; x++)
	            for (int y = yCoord; y < yCoord + 3; y++)
	                for (int z = zCoord - 1; z < zCoord + 2; z++) {
	                    TileEntity tile = worldObj.getTileEntity(x, y, z);
	                    // Make sure tile isn't null, is an instance of the same Tile, and isn't already a part of a multiblock (if ours is already part of one)
	                    if (tile != null && (tile instanceof TileHollowMultiBlock)) {
	                        if (this.isMaster()) {
	                            if (((TileHollowMultiBlock)tile).hasMaster())
	                                i++;
	                        } else if (!((TileHollowMultiBlock)tile).hasMaster())
	                            i++;
	                    }
	                }
	        // check if there are 26 blocks present ((3*3*3) - 1) and check that center block is empty
	        return i > 25 && worldObj.isAirBlock(xCoord, yCoord + 1, zCoord);
	    }

	    @Override
	    public void setupStructure() {
	        for (int x = xCoord - 1; x < xCoord + 2; x++)
	            for (int y = yCoord; y < yCoord + 3; y++)
	                for (int z = zCoord - 1; z < zCoord + 2; z++) {
	                    TileEntity tile = worldObj.getTileEntity(x, y, z);
	                    // Check if block is bottom center block
	                    boolean master = (x == xCoord && y == yCoord && z == zCoord);
	                    if (tile != null && (tile instanceof TileHollowMultiBlock)) {
	                        ((TileHollowMultiBlock) tile).setMasterCoords(xCoord, yCoord, zCoord);
	                        ((TileHollowMultiBlock) tile).setHasMaster(true);
	                        ((TileHollowMultiBlock) tile).setIsMaster(master);
	                    }
	                }
	    }

	    @Override
	    public void resetStructure() {
	        for (int x = xCoord - 1; x < xCoord + 2; x++)
	            for (int y = yCoord; y < yCoord + 3; y++)
	                for (int z = zCoord - 1; z < zCoord + 2; z++) {
	                    TileEntity tile = worldObj.getTileEntity(x, y, z);
	                    if (tile != null && (tile instanceof TileMultiBlock))
	                        ((TileMultiBlock) tile).reset();
	                }
	    }

		@Override
		public boolean canProcessItem() {
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
