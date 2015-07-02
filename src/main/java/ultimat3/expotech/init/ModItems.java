package ultimat3.expotech.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import ultimat3.expotech.items.ItemCarbonCutter;
import ultimat3.expotech.items.ItemModSword;
import ultimat3.expotech.items.ItemParticlePistol;
import ultimat3.expotech.items.ItemThermite;
import ultimat3.expotech.items.MetaItem;

public class ModItems {

	// Names for the ingots
	public static final String[] ingotNames = { "ingotScandium", "ingotTitanium" };

	// names for the nuggets
	public static final String[] nuggetNames = { "nuggetAluminum", "nuggetScandium", "nuggetTitanium" };

	// names for the dusts
	public static final String[] dustNames = { "dustAluminum", "dustScandium", "dustTitanium" };

	// names for miscellaneous items used only for crafting
	// TODO organize whatever this should be
	public static final String[] miscNames = { "diamondCore", "emeraldCore", "enderiumCore", "exponentialCore",
			"advancedCoil", "roboticArm", "airVent", "heatCoilMk1", "heatCoilMk2", "heatCoilMk3", "LCIP", "RCIP",
			"HCIP", "thermite", "magnet" };

	public static ToolMaterial plasmaShard = EnumHelper.addToolMaterial("plasmaShard", 3, 2000, 14.0f, 100.0f, 1);

	public static Item itemIngots = new MetaItem(ingotNames);
	public static Item itemNuggets = new MetaItem(nuggetNames);
	public static Item itemDusts = new MetaItem(dustNames);
	public static Item itemMisc = new MetaItem(miscNames);

	public static Item itemCarbonCutter = new ItemCarbonCutter();
	public static Item itemThermite = new ItemThermite();

	public static Item swordKatana = new ItemModSword("swordKatana", plasmaShard);
	public static Item particlePistol = new ItemParticlePistol("particlePistol");

	public static final ItemStack diamondCore		= new ItemStack(itemMisc, 1, 0);
	public static final ItemStack emeraldCore 	= new ItemStack(itemMisc, 1, 1);
	public static final ItemStack enderiumCore 	= new ItemStack(itemMisc, 1, 2);
	public static final ItemStack exponentialCore = new ItemStack(itemMisc, 1, 3);
	public static final ItemStack advancedCoil 	= new ItemStack(itemMisc, 1, 4);
	public static final ItemStack roboticArm  	= new ItemStack(itemMisc, 1, 5);
	public static final ItemStack airVent		 	= new ItemStack(itemMisc, 1, 6);
	public static final ItemStack heatCoilMk1		= new ItemStack(itemMisc, 1, 7);
	public static final ItemStack heatCoilMk2		= new ItemStack(itemMisc, 1, 8);
	public static final ItemStack heatCoilMk3		= new ItemStack(itemMisc, 1, 9);
	public static final ItemStack LCIP			= new ItemStack(itemMisc, 1, 10);
	public static final ItemStack RCIP			= new ItemStack(itemMisc, 1, 11);
	public static final ItemStack HCIP			= new ItemStack(itemMisc, 1, 12);
	public static final ItemStack thermite		= new ItemStack(itemMisc, 1, 13); 
	public static final ItemStack magnet			= new ItemStack(itemMisc, 1, 14);	

	public static final ItemStack ingotScandium	= new ItemStack(itemIngots, 1, 0);
	public static final ItemStack ingotTitanium	= new ItemStack(itemIngots, 1, 1);	

	public static final ItemStack nuggetScandium	= new ItemStack(itemNuggets, 1, 0);
	public static final ItemStack nuggetTitanium	= new ItemStack(itemNuggets, 1, 1);

	public static final ItemStack dustScandium	= new ItemStack(itemDusts, 1, 0);
	public static final ItemStack dustTitanium	= new ItemStack(itemDusts, 1, 1);
	
	public static void registerItems() {

	}
}
