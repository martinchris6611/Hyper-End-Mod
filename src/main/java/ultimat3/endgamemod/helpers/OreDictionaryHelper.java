package ultimat3.endgamemod.helpers;

import ultimat3.endgamemod.init.ModBlocks;
import ultimat3.endgamemod.init.ModItems;
import ultimat3.endgamemod.items.MetaItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static ultimat3.endgamemod.init.ModItems.*;

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
		
		// Registers ingots
		OreDictionary.registerOre("ingotAluminum", new ItemStack(itemIngots, 1, aluminum));
		OreDictionary.registerOre("ingotCobalt", new ItemStack(itemIngots, 1, cobalt));
		OreDictionary.registerOre("ingotLithium", new ItemStack(itemIngots, 1, lithium));
		OreDictionary.registerOre("ingotMagnesium", new ItemStack(itemIngots, 1, magnesium));
		OreDictionary.registerOre("ingotScandium", new ItemStack(itemIngots, 1, scandium));
		OreDictionary.registerOre("ingotTitanium",new ItemStack(itemIngots, 1, titanium));
		OreDictionary.registerOre("ingotSteel",new ItemStack(itemIngots, 1, steel));
		
		// Registers Nuggets
		OreDictionary.registerOre("nuggetAluminum", new ItemStack(itemNuggets, 1, aluminum));
		OreDictionary.registerOre("nuggetCobalt",new ItemStack(itemNuggets, 1, cobalt));
		OreDictionary.registerOre("nuggetLithium",new ItemStack(itemNuggets, 1, lithium));
		OreDictionary.registerOre("nuggetMagnesium",new ItemStack(itemNuggets, 1, magnesium));
		OreDictionary.registerOre("nuggetScandium", new ItemStack(itemNuggets, 1, scandium));
		OreDictionary.registerOre("nuggetTitanium", new ItemStack(itemNuggets, 1, titanium));
		OreDictionary.registerOre("nuggetSteel", new ItemStack(itemNuggets, 1, steel));
		
		// Registers Dusts
		OreDictionary.registerOre("dustAluminum", new ItemStack(itemDusts, 1, aluminum));
		OreDictionary.registerOre("dustCobalt",new ItemStack(itemDusts, 1, cobalt));
		OreDictionary.registerOre("dustLithium",new ItemStack(itemDusts, 1, lithium));
		OreDictionary.registerOre("dustMagnesium",new ItemStack(itemDusts, 1, magnesium));
		OreDictionary.registerOre("dustScandium", new ItemStack(itemDusts, 1, scandium));
		OreDictionary.registerOre("dustTitanium", new ItemStack(itemDusts, 1, titanium));
		OreDictionary.registerOre("dustSteel", new ItemStack(itemDusts, 1, steelDust));
		
		// Registers Metal Blocks
		// OreDictionary.registerOre("blockAluminum", ModBlocks.blockAluminumBlock);
		// OreDictionary.registerOre("blockCobalt", ModBlocks.blockCobaltBlock);
		// OreDictionary.registerOre("blockLithium", ModBlocks.blockLithiumBlock);
		// OreDictionary.registerOre("blockMagnesium", ModBlocks.blockMagnesiumBlock);
		// OreDictionary.registerOre("blockScandium", ModBlocks.blockScandiumBlock);
		// OreDictionary.registerOre("blockTitanium", ModBlocks.blockTitaniumBlock);
		// OreDictionary.registerOre("blockSteel", ModBlocks.blockSteelBlock);
		
		// Registers basic ores
		// OreDictionary.registerOre("oreAluminum", ModBlocks.blockAluminumOre);
		// OreDictionary.registerOre("oreCobalt", ModBlocks.blockCobaltOre);
		// OreDictionary.registerOre("oreLithium", ModBlocks.blockLithiumOre);
		// OreDictionary.registerOre("oreMagnesium", ModBlocks.blockMagnesiumOre);
		// OreDictionary.registerOre("oreScandium", ModBlocks.blockScandiumOre);
		// OreDictionary.registerOre("oreTitanium", ModBlocks.blockTitaniumOre);
	}
}
