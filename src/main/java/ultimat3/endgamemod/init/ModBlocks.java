package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ultimat3.endgamemod.blocks.*;
import ultimat3.endgamemod.blocks.machines.*;
import ultimat3.endgamemod.helpers.RegisterHelper;
import ultimat3.endgamemod.items.Ultimat3ItemBlock;

public class ModBlocks {
	// The names of the blocks in the langfiles
	public static final String[]	metalBlockNames		= { "blockAluminum", "blockCobalt", "blockLithium",
			"blockMagnesium", "blockScandium", "blockTitanium", "blockSteel" };
	
	public static final String[]	oreNames			= { "oreAluminum", "oreCobalt", "oreLithium", "oreMagnesium",
			"oreScandium", "oreTitanium"				};
	
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
	public static Block				blockHighProductionFurnace	=	new BlockHighProductionFurnace("highProductionFurnace");
	public static Block				blockMetallurgyChamber		=	new BlockMetallurgyChamber("metallurgyChamber");
	public static Block				blockProductionFurnace		=	new BlockProductionFurnace("productionFurnace");
	public static Block				blockSuperCompressor		=	new BlockSuperCompressor("superCompressor");
	/**
	 * Meta blocks
	 */
	public static Block				blockMisc			= new MetaBlock(miscNames, Material.iron).setHardness(5.0f).setResistance(8.0f);
	
	public static Block				blockMetallicGlass	= new BlockMetallicGlass();
	public static Block			blockMetallicGlassPane 	= new BlockMetallicGlassPane();
	/**
	 * Metadata
	 */
	// noforamt
	// Metadata for the blocks
	public static final int
	// metal blocks and ores
	aluminumBlock 	=	 0,
	cobaltBlock		=	 1,
	lithiumBlock	=	 2,
	magnesiumBlock	=	 3,
	scandiumBlock 	=	 4,
	titaniumBlock 	=	 5,
	steelBlock 		=	 6,
	// misc
	reinforcedIronBlock = 0,
	compressedSteel = 1,
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

		RegisterHelper.registerBlock(blockHighProductionFurnace);
		RegisterHelper.registerBlock(blockMetallurgyChamber);
		RegisterHelper.registerBlock(blockProductionFurnace);
		RegisterHelper.registerBlock(blockSuperCompressor);
		RegisterHelper.registerBlock(blockMetallicGlass);
		RegisterHelper.registerBlock(blockMetallicGlassPane);
	}
}
