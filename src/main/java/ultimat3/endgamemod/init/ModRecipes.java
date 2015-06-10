package ultimat3.endgamemod.init;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ultimat3.endgamemod.EndGame;
import cpw.mods.fml.common.registry.GameRegistry;

import static ultimat3.endgamemod.init.ModItems.*;
import static ultimat3.endgamemod.init.ModBlocks.*;

/**
 * Initialize all recipes here. Called from the {@link EndGame#init(cpw.mods.fml.common.event.FMLInitializationEvent)}
 * method.
 * 
 * @author Ebilkill
 */
public class ModRecipes {
	
	/**
	 * Compression recipes
	 */
	private static final RecipesCompression	COMPRESSION	= new RecipesCompression();
	private static final FactoryRecipes	METALLURGY	= new FactoryRecipes(false);
	
	public static RecipesCompression compression() {
		return COMPRESSION;
	}
	
	public static FactoryRecipes metallurgy() {
		return METALLURGY;
	}
	
	public static void initRecipes() {
		// Basic recipes
		initShapedRecipes();
		initShapelessRecipes();
		initSmeltingRecipes();
		
		// Compression inits itself now
		initMetallurgyRecipes();
	}
	
	// noformat
	private static void initShapedRecipes() {
		// normal
		for(int meta = 0; meta < ingotNames.length; ++meta) {
			// Nuggets-> ingots
			GameRegistry.addShapedRecipe(new ItemStack(itemIngots, 1, meta), new Object[] {
				"XXX", "XXX", "XXX", 'X', new ItemStack(itemNuggets, 1, meta)
			});
			
			// Ingots-> blocks
			GameRegistry.addShapedRecipe(new ItemStack(blockMetals, 1, meta), new Object[] {
				"III", "III", "III", 'I', new ItemStack(itemIngots, 1, meta)
			}); // I for ingot!
		}

		GameRegistry.addShapedRecipe(new ItemStack(blockMisc, 1, reinforcedIronBlock), new Object[] {
			"XXX", "XYX", "XXX", 'X', Items.diamond, 'Y', Blocks.iron_block
		});
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, ringMagnet), new Object[] {
			" M ", "M M", " M ", 'M', new ItemStack(itemMisc, 1, ironCobaltMagnet)
		});
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, squareMagnet), new Object[] {
			"MM", "MM", 'M', new ItemStack(itemMisc, 1, ironCobaltMagnet)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, carbonSheet), new Object[] {
			"CCC", 'C', new ItemStack(itemMisc, 1, refinedCarbon)
		});		
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, aluminumAlloy), new Object[] {
			"AA", "AA", 'A', new ItemStack(itemIngots, 1, aluminum)
		});
		GameRegistry.addShapedRecipe(new ItemStack(blockMetallicGlassPane, 16), new Object[] {
			"GGG", "GGG", 'G', new ItemStack(blockMetallicGlass)
		});
		// Ore Dictionary
		
	}
	
	private static void initShapelessRecipes() {
		// normal
		for(int meta = 0; meta < nuggetNames.length; ++meta) {
			// Ingots->nuggets
			GameRegistry.addShapelessRecipe(new ItemStack(itemNuggets, 9, meta), new Object[] {
				new ItemStack(itemIngots, 1, meta)
			});
			
			// Blocks->ingots
			GameRegistry.addShapelessRecipe(new ItemStack(itemIngots, 9, meta), new Object[] {
				new ItemStack(blockMetals, 1, meta)
			});
			
			GameRegistry.addShapelessRecipe(new ItemStack(itemVanillaMetals, 2, ironDust), new ItemStack(itemDusts, 1, aluminum), new Object[] {
					 new ItemStack(itemMisc, 1, thermite)
			});
			
			GameRegistry.addShapelessRecipe(new ItemStack(blockMetals, 9, steel), new Object[] {
				 new ItemStack(blockMisc, 1, compressedSteel)
			});
		}
		
		// Ore Dictionary
		
	}
	
	private static void initSmeltingRecipes() {
		// ores -> ingots. TODO add better XP values, these must be pretty sucky. Just added a random value.
		GameRegistry.addSmelting(new ItemStack(blockOres, 1, aluminum), new ItemStack(itemIngots, 1, aluminum), 2F);
		GameRegistry.addSmelting(new ItemStack(blockOres, 1, cobalt), new ItemStack(itemIngots, 1, cobalt), 2.0F);
		for(int i=0; i<ModItems.dustNames.length; i++) {
			GameRegistry.addSmelting(new ItemStack(itemDusts, 1, i), new ItemStack(itemIngots, 1, i), 2F);
		}
	}
	//format
	
	// Recipes for Metallurgy Go here WIP
	private static void initMetallurgyRecipes() {
		metallurgy().addRecipe(new ItemStack(Blocks.dirt), new ItemStack(Items.diamond));
	}
	
	/**
	 * Use this to make new recipe managers for our machines.
	 * 
	 * @author Ebilkill
	 */
	public static class FactoryRecipes {
		private Map<ItemStack, ItemStack>	recipesMap;
		private boolean						useInputAmount;
		
		public FactoryRecipes(boolean useInputAmount) {
			this.recipesMap = new HashMap<ItemStack, ItemStack>();
			this.useInputAmount = useInputAmount;
		}
		
		/**
		 * Adds a recipe that turns input into output.
		 * 
		 * @param input The item in the input (metadata sensitive).
		 * @param output The output item.
		 */
		public void addRecipe(ItemStack input, ItemStack output) {
			if (this.useInputAmount)
				this.recipesMap.put(input.copy(), output);
			else
				this.recipesMap.put(new ItemStack(input.getItem(), 1, input.getItemDamage()), output);
		}
		
		/**
		 * Gets the result of this input.
		 * 
		 * @param input The item in the input.
		 * @return The result of this operation, or null if there is no result.
		 */
		public ItemStack getResult(ItemStack input) {
			Iterator iterator = this.recipesMap.entrySet().iterator();
			Entry entry;
			
			do {
				if (!iterator.hasNext()) {
					return null;
				}
				
				entry = (Entry) iterator.next();
			} while (!this.areItemsEqual(input, (ItemStack) entry.getKey()));
			
			return (ItemStack) entry.getValue();
		}
		
		private boolean areItemsEqual(ItemStack input, ItemStack recipeInput) {
			return recipeInput.getItem() == input.getItem()
					&& (recipeInput.getItemDamage() == 32767 || recipeInput.getItemDamage() == input.getItemDamage());
		}
	}
}
