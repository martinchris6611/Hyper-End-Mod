package ultimat3.expotech.helpers;

import static ultimat3.expotech.init.ModBlocks.*;
import static ultimat3.expotech.init.ModItems.*;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Registers our ores and other materials in the ore dictionary. Allows other
 * mods to use our materials, for instance, for Tinkers' Construct to smelt our
 * aluminum and other ores that they support.
 */
public class OreDictionaryHelper {
	
	/**
	 * Does the actual initialization.
	 */
	public static void init() {
		
		// Registers ingots
		OreDictionary.registerOre("ingotScandium", ingotScandium);
		OreDictionary.registerOre("ingotTitanium", ingotTitanium);
		
		// Registers Nuggets
		OreDictionary.registerOre("nuggetScandium", nuggetScandium);
		OreDictionary.registerOre("nuggetTitanium", nuggetTitanium);
		
		// Registers Dusts
		OreDictionary.registerOre("dustScandium", dustScandium);
		OreDictionary.registerOre("dustTitanium", dustTitanium);
		
		// Registers Metal Blocks
		OreDictionary.registerOre("blockScandium", blockScandium);
		OreDictionary.registerOre("blockTitanium", blockTitanium);
		
		// Registers Metal Blocks
		OreDictionary.registerOre("oreScandium", oreScandium);
		OreDictionary.registerOre("oreTitanium", oreTitanium);
	}
}
