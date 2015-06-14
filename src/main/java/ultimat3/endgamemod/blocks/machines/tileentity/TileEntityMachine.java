package ultimat3.endgamemod.blocks.machines.tileentity;

import ultimat3.endgamemod.init.ModTileEntities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityMachine extends TileEntity implements IInventory {
	public ItemStack[] items;
	
	public static final String	TAG_ITEMS				= "items";
	public static final String	TAG_SLOT				= "slot";
	
	public static String inventoryName;
	
	protected TileEntityMachine(ItemStack[] _items, String _inventoryName) {
		items=_items;
		inventoryName=_inventoryName;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);
		NBTTagList list = mainTag.getTagList(TAG_ITEMS, NBT.TAG_COMPOUND);
		this.items = new ItemStack[items.length];
		
		// Reads items
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound itemTag = list.getCompoundTagAt(i);
			byte b = itemTag.getByte(TAG_SLOT);
			
			if (b >= 0 && b < this.items.length) {
				this.items[b] = ItemStack.loadItemStackFromNBT(itemTag);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);		
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
	
	// ====================================================
	// ================= Interfaces start =================
	// ====================================================
	
	@Override
	public int getSizeInventory() {
		return items.length;
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
		return inventoryName;
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
	public boolean isItemValidForSlot(int slot, ItemStack stack) { return true; }
}
