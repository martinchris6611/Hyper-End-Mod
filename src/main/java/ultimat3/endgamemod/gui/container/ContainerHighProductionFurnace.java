package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import ultimat3.endgamemod.gui.slot.SlotMachineFuel;

public class ContainerHighProductionFurnace extends ContainerMachine {

	public ContainerHighProductionFurnace(EntityPlayer player, World world,
			int x, int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// Output slots
			if (slotID >= 5 && slotID <= 8) {

				// Add to inv
				if (!this.mergeItemStack(itemstack1, 9, 45, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);

				// Inventory slots
			} else if (!(slotID >= 0 && slotID <= 4)) {

				// If it's smeltable, add it to the input slot
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 4, false)) {
						return null;
					}

					// Fuel? Add to the fuel slot
				} else if (itemstack1.getItem() == Item
						.getItemFromBlock(Blocks.coal_block)) {
					if (!this.mergeItemStack(itemstack1, 4, 5, false)) {
						return null;
					}

					// Main inventory? Add to hotbar
				} else if (slotID >= 9 && slotID < 36) {
					if (!this.mergeItemStack(itemstack1, 36, 45, false)) {
						return null;
					}

					// Hotbar? Add to main inv
				} else if (slotID >= 36 && slotID < 45
						&& !this.mergeItemStack(itemstack1, 9, 36, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 9, 45, false)) {
				return null;
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

	@Override
	protected Slot[] getSlotsForAdding() {
		return new Slot[] {
				new Slot(machine, 0, 76, 17),
				new Slot(machine, 1, 56, 17),
				new Slot(machine, 2, 36, 17),
				new Slot(machine, 3, 16, 17),
				new SlotMachineFuel(machine, 4,
						new ItemStack(Blocks.coal_block), 56, 53),
				new Slot(this.machine, 5, 116, 60),
				new Slot(this.machine, 6, 116, 40),
				new Slot(this.machine, 7, 116, 20),
				new Slot(this.machine, 8, 116, 0) };
	}
}
