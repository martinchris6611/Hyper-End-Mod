package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ultimat3.endgamemod.blocks.Ultimat3BlockOre;
import ultimat3.endgamemod.helpers.RegisterHelper;

public class ModBlocks {
	// Ores - Might make these metadata based later
	public static Block	blockAluminumOre	= new Ultimat3BlockOre("oreAluminum", Material.rock, 5.0F, 7.0F, 2);
	public static Block	blockCobaltOre		= new Ultimat3BlockOre("oreCobalt", Material.rock, 5.0F, 7.0F, 2);
	public static Block	blockLithiumOre		= new Ultimat3BlockOre("oreLithium", Material.rock, 5.0F, 7.0F, 2);
	public static Block	blockMagnesiumOre	= new Ultimat3BlockOre("oreMagnesium", Material.rock, 5.0F, 7.0F, 2);
	public static Block	blockScandiumOre	= new Ultimat3BlockOre("oreScandium", Material.rock, 5.0F, 7.0F, 3);
	public static Block	blockTitaniumOre	= new Ultimat3BlockOre("oreTitanium", Material.rock, 5.0F, 7.0F, 3);
	
	//Block version of Ores, that is 9 ingots makes one block
	public static Block blockAluminumBlock	= new Ultimat3BlockOre("blockAluminum", Material.rock, 5.0F, 7.0F, 2);
	public static Block blockCobaltBlock	= new Ultimat3BlockOre("blockCobalt", Material.rock, 5.0F, 7.0F, 2);
	public static Block blockLithiumBlock	= new Ultimat3BlockOre("blockLithium", Material.rock, 5.0F, 7.0F, 2);
	public static Block blockMagnesiumBlock	= new Ultimat3BlockOre("blockMagnesium", Material.rock, 5.0F, 7.0F, 2);
	public static Block blockScandiumBlock	= new Ultimat3BlockOre("blockScandium", Material.rock, 5.0F, 7.0F, 3);
	public static Block blockTitaniumBlock	= new Ultimat3BlockOre("blockTitanium", Material.rock, 5.0F, 7.0F, 3);
	
	public static void registerBlocks() {
		// Ores
		RegisterHelper.registerBlock(blockAluminumOre);
		RegisterHelper.registerBlock(blockCobaltOre);
		RegisterHelper.registerBlock(blockLithiumOre);
		RegisterHelper.registerBlock(blockMagnesiumOre);
		RegisterHelper.registerBlock(blockScandiumOre);
		RegisterHelper.registerBlock(blockTitaniumOre);
		
		//Blocks of Ores
		RegisterHelper.registerBlock(blockAluminumBlock);
		RegisterHelper.registerBlock(blockCobaltBlock);
		RegisterHelper.registerBlock(blockLithiumBlock);
		RegisterHelper.registerBlock(blockMagnesiumBlock);
		RegisterHelper.registerBlock(blockScandiumBlock);;
		RegisterHelper.registerBlock(blockTitaniumBlock);
	}
}
