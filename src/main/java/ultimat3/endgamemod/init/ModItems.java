package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import ultimat3.endgamemod.helpers.RegisterHelper;
import ultimat3.endgamemod.items.ItemModSword;
import ultimat3.endgamemod.items.MetaItem;
import ultimat3.endgamemod.items.Ultimat3Item;

public class ModItems {  // TODO test the new recipes and lang
	
	// Names for the ingots
	public static final String[]	ingotNames		= { "ingotAluminum", "ingotCobalt", "ingotLithium",
			"ingotMagnesium", "ingotScandium", "ingotTitanium", "ingotSteel" };
	
	// names for the nuggets
	public static final String[]	nuggetNames		= { "nuggetAluminum", "nuggetCobalt", "nuggetLithium",
			"nuggetMagnesium", "nuggetScandium", "nuggetTitanium", "nuggetSteel" };
	
	// names for miscellaneous items used only for crafting
	public static final String[]	miscNames		= { "diamondCoating", "thermite", "highEntropyMetalAlloy", 
		"ironCobaltMagnet", "carbonSheet", "refinedCarbon", "ringMagnet", "squareMagnet", "ionSource",  
		"fusionPlasma", "aluminumAlloy", "aluminumTubing", "steelPlating", "titaniumPlating",
		"LCIP", "RCIP", "HCIP" };
	
	public static ToolMaterial plasmaShard = EnumHelper.addToolMaterial("plasmaShard", 3, 2000, 14.0f, 100.0f, 1);
	
	// Meta Items for the win
	public static Item				itemIngots		= new MetaItem(ingotNames);
	public static Item				itemNuggets		= new MetaItem(nuggetNames);
	public static Item				itemMisc		= new MetaItem(miscNames);
	
	// noformat
	// Stores the metadata of different MetaItems
	public static final int	
	// Ingots
	aluminumIngot   = 0,
	cobaltIngot		= 1,
	lithiumIngot	= 2,
	magnesiumIngot	= 3,
	scandiumIngot	= 4,
	titaniumIngot	= 5,
	steelIngot		= 6,
	// Nuggets
	aluminumNugget	= 0,
	cobaltNugget	= 1,
	lithiumNugget	= 2,
	magnesiumNugget	= 3,
	scandiumNugget	= 4,
	titaniumNugget	= 5,
	steelNugget		= 6,
	// Miscellaneous items used for crafting
	diamondCoating	= 0,
	thermite		= 1,
	highEntropyAlloy= 2,
	ironCobaltMagnet= 3,
	carbonSheet		= 4,
	refinedCarbon	= 5,
	ringMagnet		= 6,
	squareMagnet	= 7,
	ionSource		= 8,
	fusionPlasma	= 9,
	aluminumAlloy	= 10,
	aluminumTubing	= 11,
	steelPlating	= 12,
	titaniumPlating	= 13,
	LCIP			= 14, // TODO make the name LCIP and the description Low Compressed Iron Plate
	RCIP			= 15,
	HCIP			= 16;
	// format	
	
	public static Item swordKatana = new ItemModSword("swordKatana", plasmaShard);
	
	public static void registerItems() {
		// RegisterHelper.registerItem(itemScandiumIngot); // Automatically done by Ultimat3Item
		RegisterHelper.registerItem(swordKatana);
	}
}
