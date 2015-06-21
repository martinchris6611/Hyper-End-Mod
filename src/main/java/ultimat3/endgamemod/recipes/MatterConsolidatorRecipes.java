package ultimat3.endgamemod.recipes;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MatterConsolidatorRecipes {
	
	private static ArrayList<ItemStack> input = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> output = new ArrayList<ItemStack>();
	private static ArrayList<Integer> time = new ArrayList<Integer>();
	
	private static ItemStack getStackfromObject(Object obj) {
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
	
	public static void addRecipe(Object in1, Object in2, Object in3, int processTime, Object out) {
		input.add(getStackfromObject(in1));
		input.add(getStackfromObject(in2));
		input.add(getStackfromObject(in3));
		time.add(processTime);
		output.add(getStackfromObject(out));
	}
	
	public static boolean recipeExists(Object in1, Object in2, Object in3) {
		return getRecipeID(in1, in2, in3) != -1;
	}
	
	private static int simpleHash(ItemStack stack) {
		return 33 * stack.getItemDamage() + stack.getItem().hashCode() % 2007483647;
	}
	
	private static int getRecipeID(Object in1, Object in2, Object in3) {
		ItemStack st1 = getStackfromObject(in1);
		if(st1 == null) return -1;
		ItemStack st2 = getStackfromObject(in2);
		if(st2 == null) return -1;
		ItemStack st3 = getStackfromObject(in3);
		if(st3 == null) return -1;
		long hash1 = simpleHash(st1) + simpleHash(st2) + simpleHash(st3);
		for(int i=0; i<output.size(); i++) {
			if(simpleHash(input.get(i*3)) + simpleHash(input.get(i*3+1)) + simpleHash(input.get(i*3+2)) == hash1) {
				return i;
			}
		}
		return -1;
	}
	
	public static int getTime(Object in1, Object in2, Object in3) {
		int id = getRecipeID(in1, in2, in3);
		return id == -1 ? 0 : time.get(id);
	}
	
	public static ItemStack getOutput(Object in1, Object in2, Object in3) {
		int id = getRecipeID(in1, in2, in3);
		return id == -1 ? null : output.get(id);
	}
}
