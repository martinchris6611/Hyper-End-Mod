package ultimat3.endgamemod.helpers;

import static ultimat3.endgamemod.init.ModBlocks.blockMetals;
import static ultimat3.endgamemod.init.ModBlocks.blockOres;
import static ultimat3.endgamemod.init.ModItems.aluminum;
import static ultimat3.endgamemod.init.ModItems.cobalt;
import static ultimat3.endgamemod.init.ModItems.goldDust;
import static ultimat3.endgamemod.init.ModItems.ironDust;
import static ultimat3.endgamemod.init.ModItems.ironNugget;
import static ultimat3.endgamemod.init.ModItems.itemDusts;
import static ultimat3.endgamemod.init.ModItems.itemIngots;
import static ultimat3.endgamemod.init.ModItems.itemNuggets;
import static ultimat3.endgamemod.init.ModItems.itemVanillaMetals;
import static ultimat3.endgamemod.init.ModItems.lithium;
import static ultimat3.endgamemod.init.ModItems.magnesium;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

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
		//OreDictionary.registerOre("ingotScandium", new ItemStack(itemIngots, 1, scandium));
		//OreDictionary.registerOre("ingotTitanium",new ItemStack(itemIngots, 1, titanium));
		//OreDictionary.registerOre("ingotSteel",new ItemStack(itemIngots, 1, steel));
		
		// Registers Nuggets
		OreDictionary.registerOre("nuggetAluminum", new ItemStack(itemNuggets, 1, aluminum));
		OreDictionary.registerOre("nuggetCobalt",new ItemStack(itemNuggets, 1, cobalt));
		OreDictionary.registerOre("nuggetLithium",new ItemStack(itemNuggets, 1, lithium));
		OreDictionary.registerOre("nuggetMagnesium",new ItemStack(itemNuggets, 1, magnesium));
		//OreDictionary.registerOre("nuggetScandium", new ItemStack(itemNuggets, 1, scandium));
		//OreDictionary.registerOre("nuggetTitanium", new ItemStack(itemNuggets, 1, titanium));
		//OreDictionary.registerOre("nuggetSteel", new ItemStack(itemNuggets, 1, steel));
		
		// Registers Dusts
		OreDictionary.registerOre("dustAluminum", new ItemStack(itemDusts, 1, aluminum));
		OreDictionary.registerOre("dustCobalt",new ItemStack(itemDusts, 1, cobalt));
		OreDictionary.registerOre("dustLithium",new ItemStack(itemDusts, 1, lithium));
		OreDictionary.registerOre("dustMagnesium",new ItemStack(itemDusts, 1, magnesium));
		//OreDictionary.registerOre("dustScandium", new ItemStack(itemDusts, 1, scandium));
		//OreDictionary.registerOre("dustTitanium", new ItemStack(itemDusts, 1, titanium));
		//OreDictionary.registerOre("dustSteel", new ItemStack(itemDusts, 1, steel));
		
		// Register Vanilla metal forms
		OreDictionary.registerOre("nuggetIron", new ItemStack(itemVanillaMetals, 1, ironNugget));
		OreDictionary.registerOre("dustIron", new ItemStack(itemVanillaMetals, 1, ironDust));
		OreDictionary.registerOre("dustGold", new ItemStack(itemDusts, 1, goldDust));
		
		
		// Registers Metal Blocks
		OreDictionary.registerOre("blockAluminum", new ItemStack(blockMetals, 1, aluminum));
		OreDictionary.registerOre("blockCobalt", new ItemStack(blockMetals, 1, cobalt));
		OreDictionary.registerOre("blockLithium", new ItemStack(blockMetals, 1, lithium));
		OreDictionary.registerOre("blockMagnesium", new ItemStack(blockMetals, 1, magnesium));
		//OreDictionary.registerOre("blockScandium", new ItemStack(blockMetals, 1, scandium));
		//OreDictionary.registerOre("blockTitanium", new ItemStack(blockMetals, 1, titanium));
		//OreDictionary.registerOre("blockSteel", new ItemStack(blockMetals, 1, steel));
		
		// Registers Metal Blocks
		OreDictionary.registerOre("oreAluminum", new ItemStack(blockOres, 1, aluminum));
		OreDictionary.registerOre("oreCobalt", new ItemStack(blockOres, 1, cobalt));
		OreDictionary.registerOre("oreLithium", new ItemStack(blockOres, 1, lithium));
		OreDictionary.registerOre("oreMagnesium", new ItemStack(blockOres, 1, magnesium));
		//OreDictionary.registerOre("oreScandium", new ItemStack(blockOres, 1, scandium));
		//OreDictionary.registerOre("oreTitanium", new ItemStack(blockOres, 1, titanium));
		//OreDictionary.registerOre("oreSteel", new ItemStack(blockOres, 1, steel));
	}
}
