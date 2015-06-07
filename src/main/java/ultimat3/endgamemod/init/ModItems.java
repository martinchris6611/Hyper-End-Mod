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

public class ModItems {
	
	// Names for the ingots
	public static final String[]	ingotNames		= { "ingotAluminum", "ingotCobalt", "ingotLithium",
			"ingotMagnesium", "ingotScandium", "ingotTitanium" };
	
	// names for the nuggets
	public static final String[]	nuggetNames		= { "nuggetAluminum", "nuggetCobalt", "nuggetLithium",
			"nuggetMagnesium", "nuggetScandium", "nuggetTitanium" };
	
	public static ToolMaterial plasmaShard = EnumHelper.addToolMaterial("plasmaShard", 3, 2000, 14.0f, 100.0f, 1);
	
	// Meta Items for the win
	public static Item				itemIngots		= new MetaItem(ingotNames);
	public static Item				itemNuggets		= new MetaItem(nuggetNames);
	
	// Itemstacks so the metadata doesn't have to be remembered.
	public static ItemStack			aluminumIngot	= new ItemStack(itemIngots, 1, 0);
	public static ItemStack			cobaltIngot		= new ItemStack(itemIngots, 1, 1);
	public static ItemStack			lithiumIngot	= new ItemStack(itemIngots, 1, 2);
	public static ItemStack			magnesiumIngot	= new ItemStack(itemIngots, 1, 3);
	public static ItemStack			scandiumIngot	= new ItemStack(itemIngots, 1, 4);
	public static ItemStack			titaniumIngot	= new ItemStack(itemIngots, 1, 5);
	
	public static ItemStack			aluminumNugget	= new ItemStack(itemNuggets, 1, 0);
	public static ItemStack			cobaltNugget	= new ItemStack(itemNuggets, 1, 1);
	public static ItemStack			lithiumNugget	= new ItemStack(itemNuggets, 1, 2);
	public static ItemStack			magnesiumNugget	= new ItemStack(itemNuggets, 1, 3);
	public static ItemStack			scandiumNugget	= new ItemStack(itemNuggets, 1, 4);
	public static ItemStack			titaniumNugget	= new ItemStack(itemNuggets, 1, 5);
	
	public static Item swordKatana = new ItemModSword("u3_egm_swordKatana", plasmaShard);
	
	public static void registerItems() {
		// RegisterHelper.registerItem(itemScandiumIngot); // Automatically done by Ultimat3Item
		RegisterHelper.registerItem(swordKatana);
	}
}
