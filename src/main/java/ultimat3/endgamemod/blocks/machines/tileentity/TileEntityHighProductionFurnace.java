package ultimat3.endgamemod.blocks.machines.tileentity;

import ultimat3.endgamemod.init.ModTileEntities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityHighProductionFurnace extends TileEntityMachine implements ISidedInventory {
	
	/**
	 * The amount of time left for this furnace to keep burning (in ticks).
	 */
	public short				furnaceTimeLeft;
	
	/**
	 * The amount of time this item has been cooking for.
	 */
	public short				cookTime;
	
	/**
	 * The amount of ticks it takes for a single item to cook.
	 */
	public static final short	ITEM_TIME_DONE			= 1;					// 20 = 1 sec.
																				
	/**
	 * How long a new block of coal will burn.
	 */
	public static final int		NEW_FUEL_TIME			= ITEM_TIME_DONE * 20;
	
	private int[]				bottomSlots				= { 4 };
	private int[]				topSlots				= { 0, 1, 2, 3 };
	private int[]				sideSlots				= { 5, 6, 7, 8 };
	
	// ================ Tag names start ===============
	
	public static final String	TAG_FURNACE_TIME_LEFT	= "furnaceTime";
	public static final String	TAG_ITEM_TIME			= "itemTime";
	
	// ================= Tag names end ================
	
	public TileEntityHighProductionFurnace() {
		super(new ItemStack[9], "container." + ModTileEntities.HIGH_PRODUCTION_FURNACE_ID);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);
		// reads other things
		this.furnaceTimeLeft = mainTag.getShort(TAG_FURNACE_TIME_LEFT);
		this.cookTime = mainTag.getShort(TAG_ITEM_TIME);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);		
		// Writes other things
		mainTag.setShort(TAG_FURNACE_TIME_LEFT, furnaceTimeLeft);
		mainTag.setShort(TAG_ITEM_TIME, cookTime);
	}
	
	//Corresponds to the first smeltable slot (slot 0) and checks if valid input
	private boolean canSmeltSlotOne() {
		if (this.items[0] == null)
			return false;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[0]);
		
		// If the result is nothing, we can't smelt
		if (itemstack == null)
			return false;
		
		// If the current output is empty, we can smelt for sure
		if (this.items[5] == null)
			return true;
		
		// If the result and output aren't the same, we can't smelt either
		if (!this.items[5].isItemEqual(itemstack))
			return false;
		
		// Otherwise, it depends on the stack limit
		int result = items[5].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= this.items[5].getMaxStackSize();
	}
	
	
	//Actually smelts slot 0 if it is valid, outputs to slot 5
	private void smeltItemSlotOne() {
		if (!canSmeltSlotOne())
			return;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[0]);
		
		// If the output currently has no item, might as well copy the new result in there
		if (this.items[5] == null) {
			this.items[5] = itemstack.copy();
			// else we better check whether the items are equal before stacking...
		} else if (this.items[5].isItemEqual(itemstack)) {
			this.items[5].stackSize += itemstack.stackSize;
		}
		
		--this.items[0].stackSize;
		
		if (this.items[0].stackSize <= 0) {
			this.items[0] = null;
		}
	}
	
	//Corresponds to the second smeltable slot (slot 1) and checks if it is valid
	private boolean canSmeltSlotTwo() {
		if (this.items[1] == null)
			return false;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[1]);
		
		// If the result is nothing, we can't smelt
		if (itemstack == null)
			return false;
		
		// If the current output is empty, we can smelt for sure
		if (this.items[6] == null)
			return true;
		
		// If the result and output aren't the same, we can't smelt either
		if (!this.items[6].isItemEqual(itemstack))
			return false;
		
		// Otherwise, it depends on the stack limit
		int result = items[6].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= this.items[6].getMaxStackSize();
	}
	
	//Smelts slot 1 and outputs to slot 6 if it is valid 
	private void smeltItemSlotTwo() {
		if (!canSmeltSlotTwo())
			return;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[1]);
		
		// If the output currently has no item, might as well copy the new result in there
		if (this.items[6] == null) {
			this.items[6] = itemstack.copy();
			// else we better check whether the items are equal before stacking...
		} else if (this.items[6].isItemEqual(itemstack)) {
			this.items[6].stackSize += itemstack.stackSize;
		}
		
		--this.items[1].stackSize;
		
		if (this.items[1].stackSize <= 0) {
			this.items[1] = null;
		}
	}	
	
	//Corresponds to the third smeltable slot (slot 3) and checks if it is valid
	private boolean canSmeltSlotThree() {
		if (this.items[2] == null)
			return false;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[2]);
		
		// If the result is nothing, we can't smelt
		if (itemstack == null)
			return false;
		
		// If the current output is empty, we can smelt for sure
		if (this.items[7] == null)
			return true;
		
		// If the result and output aren't the same, we can't smelt either
		if (!this.items[7].isItemEqual(itemstack))
			return false;
		
		// Otherwise, it depends on the stack limit
		int result = items[7].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= this.items[7].getMaxStackSize();
	}
	
	//Smelts slot 2 and outputs to slot 7 if it is valid 
	private void smeltItemSlotThree() {
		if (!canSmeltSlotThree())
			return;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[2]);
		
		// If the output currently has no item, might as well copy the new result in there
		if (this.items[7] == null) {
			this.items[7] = itemstack.copy();
			// else we better check whether the items are equal before stacking...
		} else if (this.items[7].isItemEqual(itemstack)) {
			this.items[7].stackSize += itemstack.stackSize;
		}
		
		--this.items[2].stackSize;
		
		if (this.items[2].stackSize <= 0) {
			this.items[2] = null;
		}
	}	
	
	//Corresponds to the fourth smeltable slot and checks if it is valid
	private boolean canSmeltSlotFour() {
		if (this.items[3] == null)
			return false;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[3]);
		
		// If the result is nothing, we can't smelt
		if (itemstack == null)
			return false;
		
		// If the current output is empty, we can smelt for sure
		if (this.items[8] == null)
			return true;
		
		// If the result and output aren't the same, we can't smelt either
		if (!this.items[8].isItemEqual(itemstack))
			return false;
		
		// Otherwise, it depends on the stack limit
		int result = items[8].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= this.items[8].getMaxStackSize();
	}
	
	//Smelts slot 3 and outputs to slot 8 if it is valid 
	private void smeltItemSlotTFour() {
		if (!canSmeltSlotFour())
			return;
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[3]);
		
		// If the output currently has no item, might as well copy the new result in there
		if (this.items[8] == null) {
			this.items[8] = itemstack.copy();
			// else we better check whether the items are equal before stacking...
		} else if (this.items[8].isItemEqual(itemstack)) {
			this.items[8].stackSize += itemstack.stackSize;
		}
		
		--this.items[3].stackSize;
		
		if (this.items[3].stackSize <= 0) {
			this.items[3] = null;
		}
	}	
	@Override
	public void updateEntity() {
		// Make sure the furnace decreases the time left continuously. This thing has to stop sometime.
		if (this.furnaceTimeLeft > 0)
			--this.furnaceTimeLeft;
		
		boolean shouldSave = false;
		
		// Server takes care of this
		if (!this.worldObj.isRemote) {
			// If the furnace has fuel (burning or ready) and the smeltable isn't nothing in any slots
			if (this.furnaceTimeLeft != 0 || this.items[4] != null && ((this.items[0] != null) || (this.items[1] != null) || (this.items[2] != null) || (this.items[3] != null))) {
				
				// If the furnace is done burning and can smelt in any slot
				if (this.furnaceTimeLeft == 0 && (this.canSmeltSlotOne() || this.canSmeltSlotTwo() || this.canSmeltSlotThree() || this.canSmeltSlotFour())) {
					this.furnaceTimeLeft = NEW_FUEL_TIME;
					
					if (this.furnaceTimeLeft > 0) {
						shouldSave = true;
						
						if (this.items[4] != null) {
							--this.items[4].stackSize;
							
							if (this.items[4].stackSize <= 0) {
								this.items[4] = null;
							}
						}
					}
				}
				
				// If this item can be smelted in slot one and the furnace is burning
				if (this.furnaceTimeLeft > 0 && this.canSmeltSlotOne()) {
					++this.cookTime;
				
					// if the item is done
					if (this.cookTime >= ITEM_TIME_DONE) {
						this.cookTime = 0;
						this.smeltItemSlotOne();
						shouldSave = true;
					}
				} else {
					this.cookTime = 0;
				}
				
				// If this item can be smelted in slot two and the furnace is burning
				if (this.furnaceTimeLeft > 0 && this.canSmeltSlotTwo()) {
					++this.cookTime;
				
					// if the item is done
					if (this.cookTime >= ITEM_TIME_DONE) {
						this.cookTime = 0;
						this.smeltItemSlotTwo();
						shouldSave = true;
					}
				} else {
					this.cookTime = 0;
				}
				
				// If this item can be smelted in slot three and the furnace is burning
				if (this.furnaceTimeLeft > 0 && this.canSmeltSlotThree()) {
					++this.cookTime;
				
					// if the item is done
					if (this.cookTime >= ITEM_TIME_DONE) {
						this.cookTime = 0;
						this.smeltItemSlotThree();
						shouldSave = true;
					}
				} else {
					this.cookTime = 0;
				}
				
				// If this item can be smelted in slot four and the furnace is burning
				if (this.furnaceTimeLeft > 0 && this.canSmeltSlotFour()) {
					++this.cookTime;
				
					// if the item is done
					if (this.cookTime >= ITEM_TIME_DONE) {
						this.cookTime = 0;
						this.smeltItemSlotTFour();
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
		return this.furnaceTimeLeft > 0;
	}
	
	public int getFurnaceTimeRemaining(int i) {
		return furnaceTimeLeft / NEW_FUEL_TIME * i;
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
		if (slot == 5 || slot == 6 || slot == 7 || slot == 8)
			return false;
		
		// Can only input valid fuel (coal blocks) on the fuel input
		if (slot == 4)
			return stack.getItem() == Item.getItemFromBlock(Blocks.coal_block);
		
		// Can only input items with a smelting result on the item input
		return FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
		// return true;
	}
	
	// =========================================================
	// ================= ISidedInventory start =================
	// =========================================================
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		// Makes sense, right? side 0 is bottom, side 1 is top.
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
}
