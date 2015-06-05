package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import ultimat3.endgamemod.items.Ultimat3Item;
import cpw.mods.fml.common.registry.GameRegistry;
import ultimat3.endgamemod.blocks.Ultimat3BlockOre;
import ultimat3.endgamemod.helpers.RegisterHelper;

public class ModItems {
	
	//All Ingot Items
	public static Item	itemScandiumIngot	= new Ultimat3Item("ingotScandium");
	public static Item	itemLithiumIngot	= new Ultimat3Item("ingotLithium");
	public static Item	itemTitaniumIngot	= new Ultimat3Item("ingotTitanium");
	public static Item	itemCobaltIngot		= new Ultimat3Item("ingotCobalt");
	public static Item	itemAluminumIngot	= new Ultimat3Item("ingotAluminum");
	public static Item	itemMagnesiumIngot	= new Ultimat3Item("ingotMagnesium");
	
	//All Nugget Items
	public static Item itemAluminumNugget = new Ultimat3Item("nuggetAluminum");
	public static Item itemCobaltNugget = new Ultimat3Item("nuggetCobalt");
	public static Item itemLithiumNugget = new Ultimat3Item("nuggetLithium");
	public static Item itemMagnesiumNugget = new Ultimat3Item("nuggetMagnesium");
	public static Item itemScandiumNugget = new Ultimat3Item("nuggetScandium");
	public static Item itemTitaniumNugget = new Ultimat3Item("nuggetTitanium");
	
	public static void registerItems() {
		// RegisterHelper.registerItem(itemScandiumIngot); // Automatically done by Ultimat3Item
	}
}
