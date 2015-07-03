package ultimat3.expotech.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import ultimat3.expotech.blocks.BlockDetector;
import ultimat3.expotech.blocks.BlockElectroMagnet;
import ultimat3.expotech.blocks.BlockForce;
import ultimat3.expotech.blocks.BlockMetallicGlass;
import ultimat3.expotech.blocks.BlockMetallicGlassPane;
import ultimat3.expotech.blocks.BlockOre;
import ultimat3.expotech.blocks.BlockParticleTube;
import ultimat3.expotech.blocks.BlockProtonLaser;
import ultimat3.expotech.blocks.BlockSidedTextures;
import ultimat3.expotech.blocks.BlockThermiteFire;
import ultimat3.expotech.blocks.BlockThermiteWire;
import ultimat3.expotech.blocks.MetaBlock;
import ultimat3.expotech.blocks.machines.BlockAirSeparator;
import ultimat3.expotech.blocks.machines.BlockExpoFurnace;
import ultimat3.expotech.blocks.machines.BlockForcefieldController;
import ultimat3.expotech.blocks.machines.BlockMatterConsolidator;
import ultimat3.expotech.blocks.machines.BlockMechanicalAssembler;
import ultimat3.expotech.helpers.RegisterHelper;
import ultimat3.expotech.items.Ultimat3ItemBlock;
import ultimat3.expotech.multiblock.ItemSpecialBlock;
import ultimat3.expotech.multiblock.ParticleController;

public class ModBlocks {	
	
	// The names of the blocks in the langfiles
	public static final String[]	metalBlockNames		= { "blockScandium", "blockTitanium" };
	
	public static final String[]	oreNames			= {	"oreScandium", "oreTitanium" };
	
	public static Block				blockOres			= new BlockOre(oreNames, Material.rock, 5.0F, 7.0F, 2);
	
	public static Block				blockMetals			= new BlockOre(metalBlockNames, Material.iron, 5.0F, 7.0F, 2);
	
	public static Block				blockForcefieldController	=	new BlockForcefieldController("forcefieldController");
	public static Block				blockMechanicalAssembler	=	new BlockMechanicalAssembler("mechanicalAssembler");
	public static Block				blockAirSeparator			=	new BlockAirSeparator("airSeparator");
	public static Block				blockMatterConsolidator		=	new BlockMatterConsolidator("matterConsolidator");
	public static Block				blockExpoFurnace			=	new BlockExpoFurnace("expoFurnace");
	
	// TODO fix details like hardness and materials of some blocks
	// TODO remove unnecessary block classes
	
	public static Block				blockMetallicGlass		= new BlockMetallicGlass("blockMetallicGlass");
	public static Block				blockMetallicGlassPane 	= new BlockMetallicGlassPane("blockMetallicGlassPane");
	public static Block				blockThermiteWire		= new BlockThermiteWire("blockThermiteWire");
	public static Block				blockThermiteFire		= new BlockThermiteFire("thermiteFire");
	public static Block				blockForce				= new BlockForce("blockForce");
	public static Block				blockElectroMagnet		= new BlockElectroMagnet("electroMagnet");
	public static Block				blockProtonLaser		= new BlockProtonLaser("blockProtonLaser");
	public static Block				blockDetector			= new BlockDetector("blockDetector");
	public static Block				blockParticleTube		= new BlockParticleTube("blockParticleTube");
	public static Block				blockParticleController = new ParticleController("particleController");
	public static Block				blockMachineHousing		= new BlockSidedTextures("machineHousing", Material.anvil);	
	
	public static final ItemStack blockScandium = new ItemStack(blockMetals, 1, 0);
	public static final ItemStack blockTitanium = new ItemStack(blockMetals, 1, 1);
	
	public static final ItemStack oreScandium = new ItemStack(blockOres, 1, 0);
	public static final ItemStack oreTitanium = new ItemStack(blockOres, 1, 1);	
	
	//format
	public static void registerBlocks() {
		// Ores
		RegisterHelper.registerBlock(blockOres, Ultimat3ItemBlock.class);
		blockOres.setHarvestLevel("pickaxe", 3, 4); // TODO move this to a better place
		blockOres.setHarvestLevel("pickaxe", 3, 5);
		
		// Metals blocks
		RegisterHelper.registerBlock(blockMetals, Ultimat3ItemBlock.class);
		blockMetals.setHarvestLevel("pickaxe", 3, 4);
		blockMetals.setHarvestLevel("pickaxe", 3, 5);
		
		// Machines 
		
		RegisterHelper.registerBlock(blockForcefieldController);
		RegisterHelper.registerBlock(blockMechanicalAssembler);
		RegisterHelper.registerBlock(blockAirSeparator);
		RegisterHelper.registerBlock(blockMatterConsolidator);
		RegisterHelper.registerBlock(blockExpoFurnace);
		
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
		RegisterHelper.registerBlock(blockMachineHousing);
	}
}
