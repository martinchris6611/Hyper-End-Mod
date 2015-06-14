package ultimat3.endgamemod.blocks.machines.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;
import ultimat3.endgamemod.helpers.LogHelper;
import ultimat3.endgamemod.init.ModRecipes;
import ultimat3.endgamemod.init.ModTileEntities;

public class TileEntitySuperCompressor extends TileEntityMachine implements ISidedInventory, IEnergyHandler {
	
	/**
	 * The amount of time left for this metallurgy to keep burning (in ticks).
	 */
	public short				compressorTimeLeft;
	
	/**
	 * The amount of time this item has been cooking for.
	 */
	public short				cookTime;
	
	/**
	 * The amount of ticks it takes for a single item to cook.
	 */
	public static final short	ITEM_TIME_DONE	= 10;								// 20 = 1 sec.
	
	/**
	 * Amount of Energy this item can internally store
	 */
	
	
	protected EnergyStorage storage = new EnergyStorage(32000);
					
	// Slot related stuff
	private static final int	OUTPUT_SLOT	= 9;
	private int[]				topSlots		= { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	private int[]				notTopSlots		= { 9 };
	
	// ================ Tag names start ===============

	public static final String	TAG_ITEM_TIME				= "itemTime";
	public static final String	TAG_BATTERY_RF				= "batteryRF";
	public static final String	TAG_COMPRESSOR_TIME_LEFT	= "compressorTime";

	// ================= Tag names end ================
	
	
	public TileEntitySuperCompressor() {
		super(new ItemStack[10], "container." + ModTileEntities.SUPER_COMPRESSOR_ID);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);
		storage.readFromNBT(mainTag);
		// reads other things
		this.compressorTimeLeft = mainTag.getShort(TAG_COMPRESSOR_TIME_LEFT);
		this.cookTime = mainTag.getShort(TAG_ITEM_TIME);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);
		storage.writeToNBT(mainTag);
		// Writes other things
		mainTag.setShort(TAG_COMPRESSOR_TIME_LEFT, compressorTimeLeft);
		mainTag.setShort(TAG_ITEM_TIME, cookTime);
	}
	
	private boolean canCompress() {
		if (this.items[0] == null)
			return false;
		
		//If there is no energy storage, we can't do anything
		if (storage.getEnergyStored() == 0)
			return false;
		
		ItemStack itemstack = ModRecipes.compression().findMatchingRecipe(this, this.worldObj);
		
		// If the result is nothing, we can't smelt
		if (itemstack == null) {
			LogHelper.debug("No matching recipe for compressor.");
			return false;
		}
		// If the current output is empty, we can smelt for sure
		if (this.items[OUTPUT_SLOT] == null)
			return true;
		
		// If the result and output aren't the same, we can't smelt either
		if (!this.items[OUTPUT_SLOT].isItemEqual(itemstack))
			return false;
		
		// Otherwise, it depends on the stack limit
		int result = items[9].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= this.items[OUTPUT_SLOT].getMaxStackSize();
	}
	
	private void compressItem() {
		if (!canCompress())
			return;
		
		ItemStack itemstack = ModRecipes.compression().findMatchingRecipe(this, this.worldObj);
		
		// If the output currently has no item, might as well copy the new result in there
		if (this.items[OUTPUT_SLOT] == null) {
			this.items[OUTPUT_SLOT] = itemstack.copy();
			
			// else we better check whether the items are equal before stacking...
		} else if (this.items[OUTPUT_SLOT].isItemEqual(itemstack)) {
			this.items[OUTPUT_SLOT].stackSize += itemstack.stackSize;
		}
		
		--this.items[0].stackSize;
		
		if (this.items[0].stackSize <= 0) {
			this.items[0] = null;
		}
	}
	
	@Override
	public void updateEntity() {
		// TODO Make sure RF is checked
		if(this.compressorTimeLeft > 0)
			--this.compressorTimeLeft;
		
		boolean shouldSave = false;
		
		// Server takes care of this
		if (!this.worldObj.isRemote) {
			// If the furnace has fuel (burning or ready) and the smeltable isn't nothing
			if(this.compressorTimeLeft != 0) {
				// TODO ADD ENERGY CHECK
				
				if (this.canCompress() && this.compressorTimeLeft > 0) {
					++this.cookTime;
					storage.extractEnergy(100, true);
					
					// if the item is done
					if (this.cookTime >= ITEM_TIME_DONE) {
						this.cookTime = 0;
						this.compressItem();
						shouldSave = true;
					}
				} else {
					this.cookTime = 0;
				}
			}
		}
		
		if (shouldSave)
			this.markDirty();
	}
	
	// TODO make sure this checks energy
	public boolean hasEnergy() {
		// return this.furnaceTimeLeft > 0;
		return true;
	}
	
	public int getCompressorTimeRemaining(int i) {
		//return compressorTimeLeft / 
		return 0;
	}
	
	public int getCookProgress(int i) {
		return cookTime / ITEM_TIME_DONE * i;
	}
	
	// ====================================================
	// ================= Interfaces start =================
	// ====================================================
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		// Can't input on the output
		if (slot == 1)
			return false;
		
		// Can only input items with a smelting result on the item input
		return false;
	}
	
	// =========================================================
	// ================= ISidedInventory start =================
	// =========================================================
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		// Makes sense, right? side 0 is bottom, side 1 is top.
		if (side == 1)
			return topSlots;
		
		return notTopSlots;
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return this.isItemValidForSlot(slot, stack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		// Can't extract fuel or input items.
		if (side == 1)
			return false;
		
		// Can't extract from the item input. Sorry man.
		if (slot == 0)
			return false;
		
		// Can extract everything else though.
		return true;
	}
	
	// =========================================================
	// ===================== Recipes start =====================
	// =========================================================
	
	public int getSizeGrid() {
		return 9;
	}
	
	public ItemStack getStackInRowAndColumn(int x, int y) {
		return this.items[x + y * 3];
	}

	
	// =========================================================
	// ===================== Energy Handlers ===================
	// =========================================================
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
}
