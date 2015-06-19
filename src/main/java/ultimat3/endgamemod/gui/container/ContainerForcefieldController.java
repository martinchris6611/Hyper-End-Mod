package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityForcefieldController;
import ultimat3.endgamemod.gui.slot.SlotWhitelist;
import ultimat3.endgamemod.init.ModItems;

public class ContainerForcefieldController extends Container {

	@SuppressWarnings("unused")
	private World world;
	private TileEntityForcefieldController machine;
	//private short lastCookTime;
	//private short lastBurnTime;

	public ContainerForcefieldController(EntityPlayer player, World world,
			int x, int y, int z) {
		this.world = world;

		this.machine = ((TileEntityForcefieldController) world.getTileEntity(x,
				y, z));

		addSlotToContainer(new SlotWhitelist(machine, 0, 38, 45, new ItemStack(
				ModItems.itemFFModifiers, 1, ModItems.sizeUpgrade)));
		addSlotToContainer(new SlotWhitelist(
				machine,
				1,
				121,
				45,
				new ItemStack(ModItems.itemFFModifiers, 1, ModItems.shapeCube),
				new ItemStack(ModItems.itemFFModifiers, 1, ModItems.shapeSphere),
				new ItemStack(ModItems.itemFFModifiers, 1,
						ModItems.shapeOctahedron)));

		// player slots
		this.bindPlayerInventory(player.inventory);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		// Main inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		// Hotbar
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			// 2 slots
			if (slotID <= 1) {
				
				// Add to inv
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
				
				// Rest is inventory
			} else {
				
				// Add to the apropriate slot if possible
				if (itemstack1.getItem() == ModItems.itemFFModifiers) {
					if(itemstack1.getItemDamage() == 0) {
						if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
					}
						else {
							if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
								return null;
							}
						}
				}
				// Main inventory? Add to hotbar
				else if (slotID >= 2 && slotID < 29) {
					if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
						return null;
					}
				}
				
				// Hotbar? Add to main inv
				else if (slotID >= 29 && slotID < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
					return null;
				}
			}
			
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			
			slot.onPickupFromSlot(player, itemstack1);
		}
		
		return itemstack;
	}
}
