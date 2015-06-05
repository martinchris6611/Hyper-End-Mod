package ultimat3.endgamemod.init;

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
		// example: the object[] is the recipe; the example recipe would be the same as the iron pickaxe
		/*GameRegistry.addShapedRecipe(new ItemStack(outputType, amount, meta), new Object[] {
			"XXX", " Y ", " Y ", 'X', ironIngot, 'Y', stick
		});*/

		// Recipes for nuggets -> ingots
		for(int meta = 0; meta < ModItems.ingotNames.length; ++meta) {
			GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemIngots, 1, meta), new Object[] {
				"XXX", "XXX", "XXX", 'X', new ItemStack(ModItems.itemNuggets, 1, meta)
			});
		}
		
		// Ore Dictionary
		
//		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockAluminumBlock), new Object[] { "XXX", "XXX", "XXX", 'X', ModItems.itemAluminumIngot});
//		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockCobaltBlock), new Object[] { "XXX", "XXX", "XXX", 'X', ModItems.itemCobaltIngot});
//		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockLithiumBlock), new Object[] { "XXX", "XXX", "XXX", 'X', ModItems.itemLithiumIngot});
//		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockMagnesiumBlock), new Object[] { "XXX", "XXX", "XXX", 'X', ModItems.itemMagnesiumIngot});
//		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockScandiumBlock), new Object[] { "XXX", "XXX", "XXX", 'X', ModItems.itemScandiumIngot});
//		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockTitaniumBlock), new Object[] { "XXX", "XXX", "XXX", 'X', ModItems.itemTitaniumIngot});
	}
	// format
	
	// noformat
	private static void initShapelessRecipes() {
		// normal
		
		
		// Ore Dictionary
		for(int meta = 0; meta < ModItems.nuggetNames.length; ++meta) {
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemNuggets, 9, meta), new Object[] {
				new ItemStack(ModItems.itemIngots, 1, meta)
			});
		}
		
//		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemAluminumIngot, 9), new Object[] { ModBlocks.blockAluminumBlock});
//		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemCobaltIngot, 9), new Object[] { ModBlocks.blockCobaltBlock});
//		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemLithiumIngot, 9), new Object[] { ModBlocks.blockLithiumBlock});
//		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemMagnesiumIngot, 9), new Object[] { ModBlocks.blockMagnesiumBlock});
//		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemScandiumIngot, 9), new Object[] { ModBlocks.blockScandiumBlock});
//		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemTitaniumIngot, 9), new Object[] { ModBlocks.blockTitaniumBlock});
	}
	// format
	
	private static void initSmeltingRecipes() {
		// ores -> ingots. TODO add better XP values, these must be pretty sucky. Just added a random value.
		
		//ALSO will need to make some of these smelt not in a normal furnace but in the Metallurgy Chamber
//		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockAluminumOre), new ItemStack(ModItems.itemAluminumIngot), 2F);
//		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockCobaltOre), new ItemStack(ModItems.itemCobaltIngot), 2F);
//		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockLithiumOre), new ItemStack(ModItems.itemLithiumIngot), 2F);
//		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockMagnesiumOre), new ItemStack(ModItems.itemMagnesiumIngot), 2F);
//		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockScandiumOre), new ItemStack(ModItems.itemScandiumIngot), 2F);
//		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockTitaniumOre), new ItemStack(ModItems.itemTitaniumIngot), 2F);
	}
}
