package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import ultimat3.endgamemod.items.Ultimat3Item;
import cpw.mods.fml.common.registry.GameRegistry;
import ultimat3.endgamemod.blocks.Ultimat3BlockOre;
import ultimat3.endgamemod.helpers.RegisterHelper;

public class ModItems {
	
	public static Item	itemScandiumIngot	= new Ultimat3Item("ingotScandium");
	public static Item	itemLithiumIngot	= new Ultimat3Item("ingotLithium");
	public static Item	itemTitaniumIngot	= new Ultimat3Item("ingotTitanium");
	public static Item	itemCobaltIngot		= new Ultimat3Item("ingotCobalt");
	public static Item	itemAluminumIngot	= new Ultimat3Item("ingotAluminum");
	public static Item	itemMagnesiumIngot	= new Ultimat3Item("ingotMagnesium");
	
	public static void registerItems() {
		// RegisterHelper.registerItem(itemScandiumIngot); // Automatically done by Ultimat3Item
	}
}
