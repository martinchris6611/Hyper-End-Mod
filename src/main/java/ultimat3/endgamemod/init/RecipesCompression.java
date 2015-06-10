package ultimat3.endgamemod.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.tileentity.TileEntitySuperCompressor;
import ultimat3.endgamemod.init.recipes.ICompressionRecipe;
import ultimat3.endgamemod.init.recipes.ShapedCompressionRecipe;

import static ultimat3.endgamemod.init.ModItems.*;

public class RecipesCompression {
	/** A list of all the recipes added */
	private List<ICompressionRecipe>	recipes	= new ArrayList<ICompressionRecipe>();
	
	// noformat
	RecipesCompression() {
		this.addRecipe(new ItemStack(Blocks.diamond_block), 
				"XXX", "XXX", "XXX", 'X', Blocks.dirt
		);
		this.addRecipe(new ItemStack(itemMisc, 1, highEntropyAlloy), 
				"XYZ", "XUZ", "XYZ", 'X', new ItemStack(itemIngots, 0, magnesium), 'Y', new ItemStack(itemIngots, 0, titanium), 'Z', new ItemStack(itemIngots, 0, lithium), 'U', new ItemStack(itemIngots, 0, scandium)
		);
	}
	// format
	
	public ShapedCompressionRecipe addRecipe(ItemStack output, Object... inputArray) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		
		if (inputArray[i] instanceof String[]) {
			String[] astring = (String[]) ((String[]) inputArray[i++]);
			
			for (int l = 0; l < astring.length; ++l) {
				String s1 = astring[l];
				++k;
				j = s1.length();
				s = s + s1;
			}
		} else {
			while (inputArray[i] instanceof String) {
				String s2 = (String) inputArray[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}
		
		HashMap<Character, ItemStack> hashmap;
		
		for (hashmap = new HashMap<Character, ItemStack>(); i < inputArray.length; i += 2) {
			Character character = (Character) inputArray[i];
			ItemStack itemstack1 = null;
			
			if (inputArray[i + 1] instanceof Item) {
				itemstack1 = new ItemStack((Item) inputArray[i + 1]);
			} else if (inputArray[i + 1] instanceof Block) {
				itemstack1 = new ItemStack((Block) inputArray[i + 1], 1, 32767);
			} else if (inputArray[i + 1] instanceof ItemStack) {
				itemstack1 = (ItemStack) inputArray[i + 1];
			}
			
			hashmap.put(character, itemstack1);
		}
		
		ItemStack[] aitemstack = new ItemStack[j * k];
		
		for (int i1 = 0; i1 < j * k; ++i1) {
			char c0 = s.charAt(i1);
			
			if (hashmap.containsKey(Character.valueOf(c0))) {
				aitemstack[i1] = ((ItemStack) hashmap.get(Character.valueOf(c0))).copy();
			} else {
				aitemstack[i1] = null;
			}
		}
		
		ShapedCompressionRecipe shapedrecipes = new ShapedCompressionRecipe(j, k, aitemstack, output);
		this.recipes.add(shapedrecipes);
		return shapedrecipes;
	}
	
	public ItemStack findMatchingRecipe(TileEntitySuperCompressor compressor, World world) {
		int recipesIndex;
		
		for (recipesIndex = 0; recipesIndex < this.recipes.size(); ++recipesIndex) {
			ICompressionRecipe irecipe = (ICompressionRecipe) this.recipes.get(recipesIndex);
			
			if (irecipe.matches(compressor, world)) {
				return irecipe.getCraftingResult(compressor);
			}
		}
		
		return null;
	}
	
	/**
	 * returns the List<> of all recipes
	 */
	public List getRecipeList() {
		return this.recipes;
	}
}
