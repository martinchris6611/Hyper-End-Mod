package ultimat3.endgamemod.helpers;

import ultimat3.endgamemod.init.ModBlocks;
import ultimat3.endgamemod.init.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Registers our ores and other materials in the ore dictionary. Allows other
 * mods to use our materials, for instance, for Tinkers' Construct to smelt our
 * aluminum and other ores that they support.
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
		// Registers basic ores
		// OreDictionary.registerOre("oreAluminum", ModBlocks.blockAluminumOre);
		// OreDictionary.registerOre("oreCobalt", ModBlocks.blockCobaltOre);
		// OreDictionary.registerOre("oreLithium", ModBlocks.blockLithiumOre);
		// OreDictionary.registerOre("oreMagnesium", ModBlocks.blockMagnesiumOre);
		// OreDictionary.registerOre("oreScandium", ModBlocks.blockScandiumOre);
		// OreDictionary.registerOre("oreTitanium", ModBlocks.blockTitaniumOre);
		
		// Registers ingots
		OreDictionary.registerOre("ingotAluminum", ModItems.aluminumIngot);
		OreDictionary.registerOre("ingotCobalt", ModItems.cobaltIngot);
		OreDictionary.registerOre("ingotLithium", ModItems.lithiumIngot);
		OreDictionary.registerOre("ingotMagnesium", ModItems.magnesiumIngot);
		OreDictionary.registerOre("ingotScandium", ModItems.scandiumIngot);
		OreDictionary.registerOre("ingotTitanium", ModItems.titaniumIngot);
		
		// Registers Nuggets
		OreDictionary.registerOre("nuggetAluminum", ModItems.aluminumNugget);
		OreDictionary.registerOre("nuggetCobalt", ModItems.cobaltNugget);
		OreDictionary.registerOre("nuggetLithium", ModItems.lithiumNugget);
		OreDictionary.registerOre("nuggetMagnesium", ModItems.magnesiumNugget);
		OreDictionary.registerOre("nuggetScandium", ModItems.scandiumNugget);
		OreDictionary.registerOre("nuggetTitanium", ModItems.titaniumNugget);
		
		// Registers Ore Blocks
		// OreDictionary.registerOre("blockAluminum", ModBlocks.blockAluminumBlock);
		// OreDictionary.registerOre("blockCobalt", ModBlocks.blockCobaltBlock);
		// OreDictionary.registerOre("blockLithium", ModBlocks.blockLithiumBlock);
		// OreDictionary.registerOre("blockMagnesium", ModBlocks.blockMagnesiumBlock);
		// OreDictionary.registerOre("blockScandium", ModBlocks.blockScandiumBlock);
		// OreDictionary.registerOre("blockTitanium", ModBlocks.blockTitaniumBlock);
	}
}
