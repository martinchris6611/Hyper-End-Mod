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
		OreDictionary.registerOre("oreAluminum", ModBlocks.blockAluminumOre);
		OreDictionary.registerOre("oreCobalt", ModBlocks.blockCobaltOre);
		OreDictionary.registerOre("oreLithium", ModBlocks.blockLithiumOre);
		OreDictionary.registerOre("oreMagnesium", ModBlocks.blockMagnesiumOre);
		OreDictionary.registerOre("oreScandium", ModBlocks.blockScandiumOre);
		OreDictionary.registerOre("oreTitanium", ModBlocks.blockTitaniumOre);
		
		// Registers ingots
		OreDictionary.registerOre("ingotAluminum", ModItems.itemAluminumIngot);
		OreDictionary.registerOre("ingotCobalt", ModItems.itemCobaltIngot);
		OreDictionary.registerOre("ingotLithium", ModItems.itemLithiumIngot);
		OreDictionary.registerOre("ingotMagnesium", ModItems.itemMagnesiumIngot);
		OreDictionary.registerOre("ingotScandium", ModItems.itemScandiumIngot);
		OreDictionary.registerOre("ingotTitanium", ModItems.itemTitaniumIngot);
		
		// Registers Nuggets
		OreDictionary.registerOre("nuggetAluminum", ModItems.itemAluminumNugget);
		OreDictionary.registerOre("nuggetCobalt", ModItems.itemCobaltNugget);
		OreDictionary.registerOre("nuggetLithium", ModItems.itemLithiumNugget);
		OreDictionary.registerOre("nuggetMagnesium",  ModItems.itemMagnesiumNugget);
		OreDictionary.registerOre("nuggetScandium", ModItems.itemScandiumNugget);
		OreDictionary.registerOre("nuggetTitanium", ModItems.itemTitaniumNugget);
		
		// Registers Ore Blocks
		OreDictionary.registerOre("blockAluminum",  ModBlocks.blockAluminumBlock);
		OreDictionary.registerOre("blockCobalt",  ModBlocks.blockCobaltBlock);
		OreDictionary.registerOre("blockLithium",  ModBlocks.blockLithiumBlock);
		OreDictionary.registerOre("blockMagnesium",  ModBlocks.blockMagnesiumBlock);
		OreDictionary.registerOre("blockScandium",  ModBlocks.blockScandiumBlock);
		OreDictionary.registerOre("blockTitanium",  ModBlocks.blockTitaniumBlock);
	}
}
