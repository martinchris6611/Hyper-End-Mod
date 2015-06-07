package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ultimat3.endgamemod.blocks.BlockOre;
import ultimat3.endgamemod.blocks.MachineBlock;
import ultimat3.endgamemod.blocks.MetaBlock;
import ultimat3.endgamemod.blocks.Ultimat3Block;
import ultimat3.endgamemod.helpers.RegisterHelper;
import ultimat3.endgamemod.items.Ultimat3ItemBlock;

public class ModBlocks {
	// The names of the blocks in the langfiles
	public static final String[]	metalBlockNames		= { "blockAluminum", "blockCobalt", "blockLithium",
			"blockMagnesium", "blockScandium", "blockTitanium" };
	
	public static final String[]	oreNames			= { "oreAluminum", "oreCobalt", "oreLithium", "oreMagnesium",
			"oreScandium", "oreTitanium"				};
	
	public static final String[]	machineNames		= { "productionFurnace" };
	
	/**
	 * Metadata based ores block. Testing atm.
	 */
	public static Block				blockOres			= new BlockOre(oreNames, Material.rock, 5.0F, 7.0F, 2);
	
	/**
	 * And the ingot-blocks
	 */
	public static Block				blockMetals			= new BlockOre(metalBlockNames, Material.iron, 5.0F, 7.0F, 2);
	public static Block				blockReinforcedIron	= new Ultimat3Block("reinforcedIron", Material.rock);
	
	public static Block				blockMachines		= new MachineBlock(machineNames, Material.iron).setHardness(3.0F);
	
	public static void registerBlocks() {
		// Ores
		RegisterHelper.registerBlock(blockOres, Ultimat3ItemBlock.class);
		blockOres.setHarvestLevel("pickaxe", 3, 4);
		blockOres.setHarvestLevel("pickaxe", 3, 5);
		
		// Metals blocks
		RegisterHelper.registerBlock(blockMetals, Ultimat3ItemBlock.class);
		blockMetals.setHarvestLevel("pickaxe", 3, 4);
		blockMetals.setHarvestLevel("pickaxe", 3, 5);
		
		RegisterHelper.registerBlock(blockReinforcedIron);
		
		// Machines 
		RegisterHelper.registerBlock(blockMachines, Ultimat3ItemBlock.class);
	}
}
