package ultimat3.expotech.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SimpleRecipes {
	
	protected static ItemStack getStack(Object obj) {
		if(obj == null) return null;
		ItemStack stack;
		if(obj instanceof ItemStack) {
			stack = (ItemStack) obj;
		} else if(obj instanceof Item) {
			stack = new ItemStack((Item)obj, 1, 0);
		} else if(obj instanceof Block) {
			stack = new ItemStack((Block)obj, 1, 0);
		} else
			stack = null;
		return stack;
	}
}
