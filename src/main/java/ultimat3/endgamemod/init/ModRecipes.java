package ultimat3.endgamemod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ultimat3.endgamemod.EndGame;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Initialize all recipes here. Called from the {@link EndGame#init(cpw.mods.fml.common.event.FMLInitializationEvent)}
 * method.
 * 
 * @author Ebilkill
 */
public class ModRecipes {
	
	public static void initRecipes() {
		initShapedRecipes();
		initShapelessRecipes();
		initSmeltingRecipes();
	}
	
	// noformat
	private static void initShapedRecipes() {
		// normal
		for(int meta = 0; meta < ModItems.ingotNames.length; ++meta) {
			// Nuggets-> ingots
			GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemIngots, 1, meta), new Object[] {
				"XXX", "XXX", "XXX", 'X', new ItemStack(ModItems.itemNuggets, 1, meta)
			});
			
			// Ingots-> blocks
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockMetals, 1, meta), new Object[] {
				"III", "III", "III", 'I', new ItemStack(ModItems.itemIngots, 1, meta)
			}); // I for ingot!
		}
		
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockReinforcedIron), new Object[] {"XXX", "XYX", "XXX", 'X', Items.diamond, 'Y', Blocks.iron_block});
		// Ore Dictionary
		
	}
	
	private static void initShapelessRecipes() {
		// normal
		for(int meta = 0; meta < ModItems.nuggetNames.length; ++meta) {
			// Ingots->nuggets
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemNuggets, 9, meta), new Object[] {
				new ItemStack(ModItems.itemIngots, 1, meta)
			});
			
			// Blocks->ingots
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemIngots, 9, meta), new Object[] {
				new ItemStack(ModBlocks.blockMetals, 1, meta)
			});
		}
		
		// Ore Dictionary
		
	}
	
	private static void initSmeltingRecipes() {
		// ores -> ingots. TODO add better XP values, these must be pretty sucky. Just added a random value.
		
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockOres, 1, 0), new ItemStack(ModItems.itemIngots, 1, 0), 2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockOres, 1, 1), new ItemStack(ModItems.itemIngots, 1, 1), 2.0F);
	}
	// format
}
