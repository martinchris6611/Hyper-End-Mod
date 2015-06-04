package ultimat3.endgamemod.helpers;

import ultimat3.endgamemod.init.ModBlocks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Registers our ores and other materials in the ore dictionary. Allows other
 * mods to use our materials, for instance, for Tinkers' Construct to smelt our
 * aluminum.
 * 
 * @author Ebilkill
 */
public class OreDictionaryHelper {

	/**
	 * Does the actual initialization.
	 * 
	 * @author Ebilkill
	 */
	public static void init() {
		OreDictionary.registerOre("oreAluminum", ModBlocks.blockAluminumOre);
		OreDictionary.registerOre("oreCobalt", ModBlocks.blockCobaltOre);
		OreDictionary.registerOre("oreLithium", ModBlocks.blockLithiumOre);
		OreDictionary.registerOre("oreScandium", ModBlocks.blockScandiumOre);
		OreDictionary.registerOre("oreTitanium", ModBlocks.blockTitaniumOre);
	}
}
