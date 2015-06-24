package ultimat3.endgamemod.recipes;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class MatterConsolidatorRecipes extends SimpleRecipes {

	public static ArrayList<ItemStack[]> recipe = new ArrayList<ItemStack[]>();
	public static ArrayList<ItemStack> output = new ArrayList<ItemStack>();
	private static ArrayList<Integer> time = new ArrayList<Integer>();

	public static void addRecipe(Object in1, Object in2, Object in3, Object out) {
		
		recipe.add(new ItemStack[] { getStack(in1), getStack(in2), getStack(in3) });
		output.add(getStack(out));
	}

	public static ItemStack getOutput(Object in1, Object in2, Object in3) {
		ItemStack[] stacks = new ItemStack[] { getStack(in1), getStack(in2),
				getStack(in3) };
		if(stacks[0]==null || stacks[1] == null || stacks[2] == null) return null;
		
		// now we need to compare this array to all input recipes
		for (int i = 0; i < recipe.size(); i++) {
			
			// did the recipe stack at that position already find a matching stack in input
			boolean[] flag = new boolean[] {false, false, false};
			// for every input stack
			for(int j=0; j<3; j++) {
				// did this input stack find a match
				boolean found = false;
				// for every current recipe stack
				for(int k=0; k<3; k++) {
					// if it's a match and hasn't been matched with another stack before
					if(stacks[j].isItemEqual(recipe.get(i)[k]) && flag[k] == false) {
						// flag as matched
						flag[k] = true;
						// the input stack found a match
						found = true;
						break;
					}
				}
				// if it didn't find a match, it's not this recipe
				if(found == false) break;
				// if it did find a match and it's the last one, this is the recipe
				if(j==2) return output.get(i);
			}
		}
		return null;
	}
}
