package ultimat3.endgamemod.init;

import net.minecraft.item.crafting.RecipesCrafting;
import net.minecraft.item.crafting.ShapedRecipes;
import cpw.mods.fml.common.registry.GameRegistry;
import ultimat3.endgamemod.EndGame;

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
}
