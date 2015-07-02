package ultimat3.expotech.recipes;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ExpoFurnaceRecipes extends SimpleRecipes {
	
	private static ArrayList<ItemStack> recipe = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> output = new ArrayList<ItemStack>();
	private static ArrayList<Integer> coils = new ArrayList<Integer>();
	
	public static void addRecipe(Object in, Object out, int coilNum) {
		recipe.add(getStack(in));
		output.add(getStack(out));
		coils.add(coilNum);
	}
	
	public static ItemStack getOutput(Object in, int coilNum) {
		ItemStack stack = getStack(in).copy();
		if(stack == null) return null;
		stack.stackSize = 1;
		for(int i=0; i<recipe.size(); i++) {
			if(recipe.get(i).isItemEqual(stack)) {
				if(coils.get(i) <= coilNum) return output.get(i);
			}
		}
		return FurnaceRecipes.smelting().getSmeltingResult(stack);
	}
}
