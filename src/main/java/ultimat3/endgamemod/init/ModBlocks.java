package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ultimat3.endgamemod.blocks.BlockMetallicGlass;
import ultimat3.endgamemod.blocks.BlockMetallicGlassPane;
import ultimat3.endgamemod.blocks.BlockOre;
import ultimat3.endgamemod.blocks.MachineBlock;
import ultimat3.endgamemod.blocks.MetaBlock;
import ultimat3.endgamemod.blocks.Ultimat3Block;
import ultimat3.endgamemod.helpers.RegisterHelper;
import ultimat3.endgamemod.items.Ultimat3ItemBlock;

public class ModBlocks {
	// The names of the blocks in the langfiles
	public static final String[]	metalBlockNames		= { "blockAluminum", "blockCobalt", "blockLithium",
			"blockMagnesium", "blockScandium", "blockTitanium", "blockSteel" };
	
	public static final String[]	oreNames			= { "oreAluminum", "oreCobalt", "oreLithium", "oreMagnesium",
			"oreScandium", "oreTitanium"				};
	
	public static final String[]	machineNames		= { "productionFurnace", "highProductionFurnace", "superCompressor", "metallurgyChamber" };
	
	public static final String[]	miscNames			= { "blockReinforcedIron", "blockCompressedSteel", "LCIP", "RCIP", "HCIP" };
	
	/**
	 * Metadata based ores block. Testing atm.
	 */
	public static Block				blockOres			= new BlockOre(oreNames, Material.rock, 5.0F, 7.0F, 2);
	
	/**
	 * And the ingot-blocks
	 */
	public static Block				blockMetals			= new BlockOre(metalBlockNames, Material.iron, 5.0F, 7.0F, 2);
	
	/**
	 * Machine blocks
	 */
	public static Block				blockMachines		= new MachineBlock(machineNames, Material.iron).setHardness(3.0F);
	/**
	 * Meta blocks
	 */
	public static Block				blockMisc			= new MetaBlock(miscNames, Material.iron);
	
	public static Block				blockMetallicGlass	= new BlockMetallicGlass();
	public static Block			blockMetallicGlassPane 	= new BlockMetallicGlassPane();
	/**
	 * Metadata
	 */
	// noforamt
	// Metadata for the blocks
	public static final int
	// metal blocks
	aluminumBlock = 0,
	cobaltBlock = 1,
	lithiumBlock = 2,
	magnesiumBlock = 3,
	scandiumBlock  = 4,
	titaniumBlock = 5,
	steelBlock = 6,
	// ores
	aluminumOre = 0,
	cobaltOre = 1,
	lithiumOre = 2,
	magnesiumOre = 3,
	scandiumOre = 4,
	titaniumOre = 5,
	// machines
	productionFurnace = 0,
	highProductionFurnace = 1,
	superCompressor = 2,
	//
	reinforcedIronBlock = 0,
	blockCompressedSteel = 1,
	LCIP = 2,
	RCIP = 3,
	HCIP = 4;
	
	
	//format
	public static void registerBlocks() {
		// Ores
		RegisterHelper.registerBlock(blockOres, Ultimat3ItemBlock.class);
		blockOres.setHarvestLevel("pickaxe", 3, 4);
		blockOres.setHarvestLevel("pickaxe", 3, 5);
		
		// Metals blocks
		RegisterHelper.registerBlock(blockMetals, Ultimat3ItemBlock.class);
		blockMetals.setHarvestLevel("pickaxe", 3, 4);
		blockMetals.setHarvestLevel("pickaxe", 3, 5);
		
		RegisterHelper.registerBlock(blockMisc, Ultimat3ItemBlock.class);
		
		// Machines 
		RegisterHelper.registerBlock(blockMachines, Ultimat3ItemBlock.class);
		RegisterHelper.registerBlock(blockMetallicGlass);
		RegisterHelper.registerBlock(blockMetallicGlassPane);
	}
}
