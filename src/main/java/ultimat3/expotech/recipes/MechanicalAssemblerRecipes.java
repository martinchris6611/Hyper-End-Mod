package ultimat3.expotech.recipes;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class MechanicalAssemblerRecipes extends SimpleRecipes {

	public static ArrayList<ItemStack[][]> recipe = new ArrayList<ItemStack[][]>();
	public static ArrayList<ItemStack> output = new ArrayList<ItemStack>();

	private static void addRecipe(ItemStack[][] stacks, ItemStack out) {
		recipe.add(stacks);
		output.add(getStack(out));
	}
	
	public static void addRecipe(Object result, Object...objects) {
		
		String[] strings = new String[4];
		int cur = 0;
		
		HashMap<Character, ItemStack> map = new HashMap<Character, ItemStack>();
		
		for(int i=0; i<objects.length; i++) {
			if(objects[i] instanceof String) {
				if(cur == 4) return;
				strings[cur] = (String) objects[i];
				if(strings[cur].length() > 4) return;
				while(strings[cur].length() < 4) strings[cur] += " ";
				cur++;
			}
			else if(objects[i] instanceof Character) {
				if(objects.length-1==i) return;
				map.put((Character) objects[i], getStack(objects[i+1]));
				i++;				
			}
			else return;
		}
		
		while(cur < 4) {
			strings[cur] = "    ";
			cur++;
		}
		
		ItemStack stacks[][] = new ItemStack[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				stacks[i][j] = map.get(strings[i].charAt(j));
			}
		}		
		compress(stacks);		
		addRecipe(stacks, getStack(result));
	}

	private static void compress(ItemStack[][] stacks) {
		boolean flag = false;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(stacks[i][j]!=null) flag = true;
			}
		}
		if(flag == false) return;
		while(stacks[0][0] == null && stacks[0][1] == null && stacks[0][2] == null && stacks[0][3] == null) {
			for(int i=0; i<3; i++) {
				for(int j=0; j<4; j++) {
					stacks[i][j] = stacks[i+1][j];
				}
			}
			for(int i=0; i<4; i++) stacks[4][i]=null;
		}
		while(stacks[0][0] == null && stacks[1][0] == null && stacks[2][0] == null && stacks[3][0] == null) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<3; j++) {
					stacks[i][j] = stacks[i][j+1];
				}
			}
			for(int i=0; i<4; i++) stacks[i][4]=null;
		}
	}

	public static ItemStack getOutput(ItemStack[] stacks) {
		ItemStack[][] stackMatrix = new ItemStack[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				stackMatrix[i][j] = stacks[i*4+j];
			}
		}
		compress(stackMatrix);
		return getOutput(stackMatrix);
	}

	private static ItemStack getOutput(ItemStack[][] stacks) {
		for(int i=0; i<recipe.size(); i++) {
			boolean flag = false;
			for(int j=0; j<4; j++) {
				for(int k=0; k<4; k++) {
					if(!compareStack(stacks[j][k], recipe.get(i)[j][k])) flag = true;
				}
			}
			if(!flag) return output.get(i);
		}
		return null;
	}

	private static boolean compareStack(ItemStack stack1,
			ItemStack stack2) {		
		if(stack1 == null) return stack2 == null;
		return stack1.isItemEqual(stack2);
	}
}
