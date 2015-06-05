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
		
		// Ore Dictionary
		
	}
	// format
	
	// noformat
	private static void initShapelessRecipes() {
		// normal
		
		
		// Ore Dictionary
		
	}
	// format
	
	private static void initSmeltingRecipes() {
		// ores -> ingots. TODO add better XP values, these must be pretty sucky. Just added a random value.
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockAluminumOre), new ItemStack(ModItems.itemAluminumIngot), 2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockCobaltOre), new ItemStack(ModItems.itemCobaltIngot), 2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockLithiumOre), new ItemStack(ModItems.itemLithiumIngot), 2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockMagnesiumOre), new ItemStack(ModItems.itemMagnesiumIngot), 2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockScandiumOre), new ItemStack(ModItems.itemScandiumIngot), 2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockTitaniumOre), new ItemStack(ModItems.itemTitaniumIngot), 2F);
	}
}
