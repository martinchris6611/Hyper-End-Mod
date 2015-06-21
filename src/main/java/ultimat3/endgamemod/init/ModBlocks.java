package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ultimat3.endgamemod.blocks.BlockDetector;
import ultimat3.endgamemod.blocks.BlockElectroMagnet;
import ultimat3.endgamemod.blocks.BlockForce;
import ultimat3.endgamemod.blocks.BlockMetallicGlass;
import ultimat3.endgamemod.blocks.BlockMetallicGlassPane;
import ultimat3.endgamemod.blocks.BlockOre;
import ultimat3.endgamemod.blocks.BlockParticleTube;
import ultimat3.endgamemod.blocks.BlockProtonLaser;
import ultimat3.endgamemod.blocks.BlockThermiteFire;
import ultimat3.endgamemod.blocks.BlockThermiteWire;
import ultimat3.endgamemod.blocks.MetaBlock;
import ultimat3.endgamemod.blocks.machines.BlockAirExtractor;
import ultimat3.endgamemod.blocks.machines.BlockAirSeparator;
import ultimat3.endgamemod.blocks.machines.BlockForcefieldController;
import ultimat3.endgamemod.blocks.machines.BlockHighProductionFurnace;
import ultimat3.endgamemod.blocks.machines.BlockMechanicalAssembler;
import ultimat3.endgamemod.blocks.machines.BlockMetallurgyChamber;
import ultimat3.endgamemod.blocks.machines.BlockProductionFurnace;
import ultimat3.endgamemod.blocks.machines.BlockSuperCompressor;
import ultimat3.endgamemod.helpers.RegisterHelper;
import ultimat3.endgamemod.items.Ultimat3ItemBlock;
import ultimat3.endgamemod.multiblock.ItemSpecialBlock;
import ultimat3.endgamemod.multiblock.ParticleController;

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
	public static Block				blockForcefieldController	=	new BlockForcefieldController("forcefieldController");
	public static Block				blockMechanicalAssembler	=	new BlockMechanicalAssembler();
	public static Block				blockAirExtractor			=	new BlockAirExtractor("airExtractor");
	public static Block				blockAirSeparator			=	new BlockAirSeparator("airSeparator");
	/**
	 * Meta blocks
	 */
	public static Block				blockMisc			= new MetaBlock(miscNames, Material.iron).setHardness(5.0f).setResistance(8.0f);
	
	public static Block				blockMetallicGlass		= new BlockMetallicGlass();
	public static Block				blockMetallicGlassPane 	= new BlockMetallicGlassPane();
	public static Block				blockThermiteWire		= new BlockThermiteWire();
	public static Block				blockThermiteFire		= new BlockThermiteFire();
	public static Block				blockForce				= new BlockForce();
	public static Block				blockElectroMagnet		= new BlockElectroMagnet();
	public static Block				blockProtonLaser		= new BlockProtonLaser();
	public static Block				blockDetector			= new BlockDetector();
	public static Block				blockParticleTube		= new BlockParticleTube();
	public static Block				blockParticleController = new ParticleController();
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
		RegisterHelper.registerBlock(blockForcefieldController);
		RegisterHelper.registerBlock(blockMechanicalAssembler);
		RegisterHelper.registerBlock(blockAirExtractor);
		RegisterHelper.registerBlock(blockAirSeparator);
		
		// Others
		
		RegisterHelper.registerBlock(blockMetallicGlass);
		RegisterHelper.registerBlock(blockMetallicGlassPane);
		RegisterHelper.registerBlock(blockThermiteWire);
		RegisterHelper.registerBlock(blockThermiteFire);
		RegisterHelper.registerBlock(blockForce);
		RegisterHelper.registerBlock(blockElectroMagnet);
		RegisterHelper.registerBlock(blockProtonLaser);
		RegisterHelper.registerBlock(blockDetector);
		RegisterHelper.registerBlock(blockParticleTube);
		RegisterHelper.registerBlock(blockParticleController, ItemSpecialBlock.class);
	}
}
