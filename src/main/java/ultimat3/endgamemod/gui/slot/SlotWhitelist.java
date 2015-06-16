package ultimat3.endgamemod.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWhitelist extends Slot {
	
	private ItemStack[] whitelist;
	
	public SlotWhitelist(IInventory inventory, int invLocation, int xPos, int yPos, ItemStack... items) {
		super(inventory, invLocation, xPos, yPos);
		this.whitelist = items;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		for(ItemStack item : whitelist) {
			if(item.isItemEqual(stack) && item.getItemDamage() == stack.getItemDamage()) return true;
		}
		return false;
	}
	
}