package ultimat3.endgamemod.helpers;

import ultimat3.endgamemod.init.ModBlocks;
import ultimat3.endgamemod.init.ModItems;
import ultimat3.endgamemod.items.MetaItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
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
		OreDictionary.registerOre("ingotAluminum", new ItemStack(ModItems.itemIngots, 1, ModItems.aluminumIngot));
		OreDictionary.registerOre("ingotCobalt", new ItemStack(ModItems.itemIngots, 1, ModItems.cobaltIngot));
		OreDictionary.registerOre("ingotLithium", new ItemStack(ModItems.itemIngots, 1, ModItems.lithiumIngot));
		OreDictionary.registerOre("ingotMagnesium", new ItemStack(ModItems.itemIngots, 1, ModItems.magnesiumIngot));
		OreDictionary.registerOre("ingotScandium", new ItemStack(ModItems.itemIngots, 1, ModItems.scandiumIngot));
		OreDictionary.registerOre("ingotTitanium",new ItemStack(ModItems.itemIngots, 1, ModItems.titaniumIngot));
		OreDictionary.registerOre("ingotSteel",new ItemStack(ModItems.itemIngots, 1, ModItems.steelIngot));
		
		// Registers Nuggets
		OreDictionary.registerOre("nuggetAluminum", new ItemStack(ModItems.itemNuggets, 1, ModItems.aluminumNugget));
		OreDictionary.registerOre("nuggetCobalt",new ItemStack(ModItems.itemNuggets, 1, ModItems.cobaltNugget));
		OreDictionary.registerOre("nuggetLithium",new ItemStack(ModItems.itemNuggets, 1, ModItems.lithiumNugget));
		OreDictionary.registerOre("nuggetMagnesium",new ItemStack(ModItems.itemNuggets, 1, ModItems.magnesiumNugget));
		OreDictionary.registerOre("nuggetScandium", new ItemStack(ModItems.itemNuggets, 1, ModItems.scandiumNugget));
		OreDictionary.registerOre("nuggetTitanium", new ItemStack(ModItems.itemNuggets, 1, ModItems.titaniumNugget));
		OreDictionary.registerOre("nuggetSteel", new ItemStack(ModItems.itemNuggets, 1, ModItems.steelNugget));
		
		// Registers Ore Blocks
		// OreDictionary.registerOre("blockAluminum", ModBlocks.blockAluminumBlock);
		// OreDictionary.registerOre("blockCobalt", ModBlocks.blockCobaltBlock);
		// OreDictionary.registerOre("blockLithium", ModBlocks.blockLithiumBlock);
		// OreDictionary.registerOre("blockMagnesium", ModBlocks.blockMagnesiumBlock);
		// OreDictionary.registerOre("blockScandium", ModBlocks.blockScandiumBlock);
		// OreDictionary.registerOre("blockTitanium", ModBlocks.blockTitaniumBlock);
		// OreDictionary.registerOre("blockSteel", ModBlocks.blockSteelBlock);
	}
}
