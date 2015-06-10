package ultimat3.endgamemod.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMachineFuel extends Slot {
	
	private ItemStack	allowedStack;
	
	public SlotMachineFuel(IInventory inventory, int invLocation, ItemStack allowedStack, int xPos, int yPos) {
		super(inventory, invLocation, xPos, yPos);
		this.allowedStack = allowedStack;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.isItemEqual(this.allowedStack);
	}
	
}
