package ultimat3.endgamemod.blocks.machines.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import ultimat3.endgamemod.init.ModItems;
import ultimat3.endgamemod.init.ModRecipes;
import ultimat3.endgamemod.init.ModTileEntities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMetallurgyChamber extends TileEntityMachine implements ISidedInventory, IEnergyHandler {
	
	/**
	 * The amount of time left for this metallurgy to keep burning (in ticks).
	 */
	public short				metallurgyTimeLeft;
	
	/**
	 * The amount of time this item has been cooking for.
	 */
	public short				cookTime;
	
	/**
	 * The amount of ticks it takes for a single item to cook.
	 */
	public static final short	ITEM_TIME_DONE				= 200;					// 20 = 1 sec.
																					
	/**
	 * How long a thermite will burn.
	 */
	public static final int		NEW_FUEL_TIME				= ITEM_TIME_DONE;

	/**
	 * Amount of Energy this item can internally store
	 */
	
	
	protected EnergyStorage storage = new EnergyStorage(32000);
	
	private int[]				bottomSlots					= { 1 };
	private int[]				topSlots					= { 0 };
	private int[]				sideSlots					= { 2 };
	
	// ================ Tag names start ===============
	
	public static final String	TAG_METALLURGY_TIME_LEFT	= "metallurgyTime";
	public static final String	TAG_ITEM_TIME				= "itemTime";
	
	// ================= Tag names end ================
	
	public TileEntityMetallurgyChamber() {
		super(new ItemStack[3], "container." + ModTileEntities.METALLURGY_CHAMBER_ID);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);
		storage.readFromNBT(mainTag);
		// reads other things
		this.metallurgyTimeLeft = mainTag.getShort(TAG_METALLURGY_TIME_LEFT);
		this.cookTime = mainTag.getShort(TAG_ITEM_TIME);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);
		storage.writeToNBT(mainTag);
		// Writes other things
		mainTag.setShort(TAG_METALLURGY_TIME_LEFT, metallurgyTimeLeft);
		mainTag.setShort(TAG_ITEM_TIME, cookTime);
	}
	
	private boolean canMetallurgy() {
		if (this.items[0] == null)
			return false;
		
		//If there is no energy storage, we can't do anything
		if (storage.getEnergyStored() == 0)
			return false;
		
		ItemStack itemstack = ModRecipes.metallurgy().getResult(this.items[0]);
		
		// If the result is nothing, we can't smelt
		if (itemstack == null)
			return false;
		
		// If the current output is empty, we can smelt for sure
		if (this.items[2] == null)
			return true;
		
		// If the result and output aren't the same, we can't smelt either
		if (!this.items[2].isItemEqual(itemstack))
			return false;
		
		// Otherwise, it depends on the stack limit
		int result = items[2].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= this.items[2].getMaxStackSize();
	}
	
	private void metallurgyItem() {
		if (!canMetallurgy())
			return;
		
		ItemStack itemstack = ModRecipes.metallurgy().getResult(this.items[0]);
		
		if (this.items[2] == null) {
			this.items[2] = itemstack.copy();
			// else we better check whether the items are equal before stacking...
		} else if (this.items[2].isItemEqual(itemstack)) {
			this.items[2].stackSize += itemstack.stackSize;
		}
		
		--this.items[0].stackSize;
		
		if (this.items[0].stackSize <= 0) {
			this.items[0] = null;
		}
	}
	
	public void updateEntity() {
		// TODO Make sure RF is checked
		// Make sure the metallurgy decreases the time left continuously. This thing has to stop sometime.
		if (this.metallurgyTimeLeft > 0)
			--this.metallurgyTimeLeft;
		
		boolean shouldSave = false;
		
		// Server takes care of this
		if (!this.worldObj.isRemote) {
			// If the metallurgy has fuel (burning or ready) and the smeltable isn't nothing
			if (this.metallurgyTimeLeft != 0 || this.items[1] != null && this.items[0] != null) {
				
				// If the metallurgy is done burning and can smelt
				if (this.metallurgyTimeLeft <= 0 && this.canMetallurgy()) {
					this.metallurgyTimeLeft = NEW_FUEL_TIME;
					
					if (this.metallurgyTimeLeft > 0) {
						shouldSave = true;
						
						if (this.items[1] != null) {
							--this.items[1].stackSize;
							
							if (this.items[1].stackSize <= 0) {
								this.items[1] = null;
							}
						}
					}
				}
				
				// If this item can be smelted and the metallurgy is burning
				if (this.metallurgyTimeLeft > 0 && this.canMetallurgy()) {
					++this.cookTime;
					storage.modifyEnergyStored(-50);
					
					// if the item is done
					if (this.cookTime >= ITEM_TIME_DONE) {
						this.cookTime = 0;
						this.metallurgyItem();
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
	
	public boolean isBurning() {
		return this.metallurgyTimeLeft > 0;
	}
	
	// TODO make sure this checks energy
	public boolean hasEnergy() {
		// return this.furnaceTimeLeft > 0;
		return true;
	}
	
	public int getMetallurgyTimeRemaining(int i) {
		return metallurgyTimeLeft / NEW_FUEL_TIME * i;
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
		if (slot == 2)
			return false;
		
		// only allow Thermite to be a fuel
		if (slot == 1)
			return stack.isItemEqual(new ItemStack(ModItems.itemThermite));
		
		// Can only input items with a metallurgy result on the item input
		return ModRecipes.metallurgy().getResult(stack) != null;
		// return true;
	}
	
	// =========================================================
	// ================= ISidedInventory start =================
	// =========================================================
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if (side == 0)
			return bottomSlots;
		
		if (side == 1)
			return topSlots;
		
		return sideSlots;
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return this.isItemValidForSlot(slot, stack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		// Can't extract fuel or input items.
		if (side == 0 || side == 1)
			return false;
		
		// Can't extract from the item input. Sorry man.
		if (slot == 0)
			return false;
		
		// Can extract everything else though.
		return true;
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
