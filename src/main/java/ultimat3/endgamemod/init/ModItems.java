package ultimat3.endgamemod.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import ultimat3.endgamemod.helpers.RegisterHelper;
import ultimat3.endgamemod.items.ItemCarbonCutter;
import ultimat3.endgamemod.items.ItemModSword;
import ultimat3.endgamemod.items.ItemParticlePistol;
import ultimat3.endgamemod.items.ItemThermite;
import ultimat3.endgamemod.items.MetaItem;

public class ModItems { // TODO test the new recipes and lang

	// Names for the ingots
	public static final String[]	ingotNames			= { "ingotAluminum", "ingotCobalt", "ingotLithium",
			"ingotMagnesium", "ingotScandium", "ingotTitanium", "ingotSteel" };
	
	// names for the nuggets
	public static final String[]	nuggetNames			= { "nuggetAluminum", "nuggetCobalt", "nuggetLithium",
			"nuggetMagnesium", "nuggetScandium", "nuggetTitanium", "nuggetSteel" };
	
	// names for the dusts
	public static final String[]	dustNames			= { "dustAluminum", "dustCobalt", "dustLithium",
			"dustMagnesium", "dustScandium", "dustTitanium", "dustSteel" };
	
	// names for miscellaneous items used only for crafting
	public static final String[]	miscNames			= { "diamondCoating", "placeholder", "highEntropyMetalAlloy",
			"ironCobaltMagnet", "carbonSheet", "refinedCarbon", "ringMagnet", "squareMagnet", "ionSource",
			"circuitBoard", "aluminumAlloy", "aluminumTubing", "steelPlating", "titaniumPlating" };
	
	public static final String[]	FFModifierNames		= { "sizeUpgrade", "shapeSphere", "shapeCube",
			"shapeOctahedron"							};
	
	// subject to change, but it makes no sense to be able to double every ore except vanilla ones
	public static final String[]	vanillaMetalNames	= { "nuggetIron", "dustIron", "dustGold" };
	
	public static ToolMaterial		plasmaShard			= EnumHelper.addToolMaterial("plasmaShard", 3, 2000, 14.0f,
																100.0f, 1);
	
	// Meta Items for the win
	public static Item				itemIngots			= new MetaItem(ingotNames);
	public static Item				itemNuggets			= new MetaItem(nuggetNames);
	public static Item				itemDusts			= new MetaItem(dustNames);
	public static Item				itemMisc			= new MetaItem(miscNames);
	public static Item				itemVanillaMetals	= new MetaItem(vanillaMetalNames);
	public static Item				itemFFModifiers		= new MetaItem(FFModifierNames);
	
	public static Item				itemCarbonCutter	= new ItemCarbonCutter();
	public static Item				itemThermite		= new ItemThermite();
	
	// noformat
	// Stores the metadata of different MetaItems
	public static final int	
		// All metal forms share the same meta order (alphabetical)
		aluminum	    = 0,
		cobalt			= 1,
		lithium			= 2,
		magnesium		= 3,
		scandium		= 4,
		titanium		= 5,
		steel			= 6,
		// Forms of vanilla metals
		ironNugget		= 0,
		ironDust		= 1,
		goldDust		= 2,
		// Miscellaneous items used for crafting
		diamondCoating	= 0,
		placeholder		= 1,
		highEntropyAlloy= 2,
		ironCobaltMagnet= 3,
		carbonSheet		= 4,
		refinedCarbon	= 5,
		ringMagnet		= 6,
		squareMagnet	= 7,
		ionSource		= 8,
		circuitBoard	= 9,
		aluminumAlloy	= 10,
		aluminumTubing	= 11,
		steelPlating	= 12,
		titaniumPlating	= 13,
		// Force Field Modifiers
		sizeUpgrade = 0,
		shapeSphere = 1,
		shapeCube = 2,
		shapeOctahedron = 3;
		// format	
	
	public static Item				swordKatana			= new ItemModSword("swordKatana", plasmaShard);
	
	public static Item				particlePistol		= new ItemParticlePistol("particlePistol");
	
	public static void registerItems() {
		// RegisterHelper.registerItem(itemScandiumIngot); // Automatically done by Ultimat3Item
		RegisterHelper.registerItem(swordKatana);
	}
}
