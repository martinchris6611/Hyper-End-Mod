package ultimat3.endgamemod.blocks.tileentity;

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
import ultimat3.endgamemod.helpers.LogHelper;
import ultimat3.endgamemod.init.ModRecipes;
import ultimat3.endgamemod.init.ModTileEntities;

public class TileEntitySuperCompressor extends TileEntity implements ISidedInventory {
	
	/**
	 * The inventory of this furnace.
	 */
	private ItemStack[]			items			= new ItemStack[10];
	
	/**
	 * The amount of time this item has been cooking for.
	 */
	public short				cookTime;
	
	/**
	 * The amount of ticks it takes for a single item to cook.
	 */
	public static final short	ITEM_TIME_DONE	= 10;								// 20 = 1 sec.
					
	// Slot related stuff
	private static final int	OUTPUT_SLOT	= 9;
	private int[]				topSlots		= { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	private int[]				notTopSlots		= { 9 };
	
	// ================ Tag names start ===============
	
	public static final String	TAG_ITEMS		= "items";
	public static final String	TAG_SLOT		= "slot";
	public static final String	TAG_ITEM_TIME	= "itemTime";
	public static final String	TAG_BATTERY_RF	= "batteryRF";
	public static final String	TAG_TANK_LAVA	= "tankLava";

	// ================= Tag names end ================
	
	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);
		NBTTagList list = mainTag.getTagList(TAG_ITEMS, NBT.TAG_COMPOUND);
		this.items = new ItemStack[this.getSizeInventory()];
		
		// Reads items
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound itemTag = list.getCompoundTagAt(i);
			byte b = itemTag.getByte(TAG_SLOT);
			
			if (b >= 0 && b < this.items.length) {
				this.items[b] = ItemStack.loadItemStackFromNBT(itemTag);
			}
		}
		
		// reads other things
		this.cookTime = mainTag.getShort(TAG_ITEM_TIME);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);
		
		// Writes other things
		mainTag.setShort(TAG_ITEM_TIME, cookTime);
		
		// Writes items
		NBTTagList list = new NBTTagList();
		for (byte index = 0; index < this.items.length; ++index) {
			if (this.items[index] != null) {
				NBTTagCompound itemTag = new NBTTagCompound();
				itemTag.setByte(TAG_SLOT, index);
				this.items[index].writeToNBT(itemTag);
				list.appendTag(itemTag);
			}
		}
		
		mainTag.setTag(TAG_ITEMS, list);
	}
	
	private boolean canCompress() {
		if (this.items[0] == null)
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
		boolean shouldSave = false;
		
		// Server takes care of this
		if (!this.worldObj.isRemote) {
			// If the furnace has fuel (burning or ready) and the smeltable isn't nothing
			
			// TODO ADD ENERGY CHECK
			if (this.canCompress()) {
				++this.cookTime;
				
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
		
		if (shouldSave)
			this.markDirty();
	}
	
	// TODO make sure this checks energy
	public boolean hasEnergy() {
		// return this.furnaceTimeLeft > 0;
		return true;
	}
	
	public int getCookProgress(int i) {
		return cookTime / ITEM_TIME_DONE * i;
	}
	
	// ====================================================
	// ================= Interfaces start =================
	// ====================================================
	
	@Override
	public int getSizeInventory() {
		return 10;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.items[slot];
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		// If there's no item, can't decrease stack size
		if (this.items[slot] == null) {
			return null;
		}
		
		ItemStack stack;
		
		// If there's not enough of the item, give as much as possible
		if (this.items[slot].stackSize <= amount) {
			stack = this.items[slot];
			this.items[slot] = null;
			return stack;
		}
		
		// Just give everything it wants now
		stack = this.items[slot].splitStack(amount);
		if (this.items[slot].stackSize <= 0) {
			this.items[slot] = null;
		}
		
		return stack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		// Return a null item if the item in the slot is null (duh...)
		if (this.items[slot] == null)
			return null;
		
		// Return the item in the slot and set the slot to null
		ItemStack stack = this.items[slot];
		this.items[slot] = null;
		return stack;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.items[slot] = stack;
		
		if (stack != null && stack.stackSize >= this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}
	
	@Override
	public String getInventoryName() {
		return "container." + ModTileEntities.SUPER_COMPRESSOR_ID;
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	@Override
	public int getInventoryStackLimit() {
		// The stack limit in every slot according to this inventory
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// Return false if the TileEntity at this place is not this TileEntity or if the player is too far away.
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
						(double) this.zCoord + 0.5D) <= 64.0D;
	}
	
	@Override
	public void openInventory() {}
	
	@Override
	public void closeInventory() {}
	
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
}
