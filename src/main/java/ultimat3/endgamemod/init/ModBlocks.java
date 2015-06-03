package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ultimat3.endgamemod.blocks.Ultimat3BlockOre;
import ultimat3.endgamemod.helpers.RegisterHelper;

public class ModBlocks 
{
	// Ores - Might make these metadata based later
	public static Block blockScandiumOre = new Ultimat3BlockOre("oreScandium", Material.rock, 5.0F, 7.0F, 3);
	public static Block blockLithiumOre = new Ultimat3BlockOre("oreLithium", Material.rock, 5.0F, 7.0F, 2);
	public static Block blockTitaniumOre = new Ultimat3BlockOre("oreTitanium", Material.rock, 5.0F, 7.0F, 3);
	public static Block blockCobaltOre = new Ultimat3BlockOre("oreCobalt", Material.rock, 5.0F, 7.0F, 2);
	
	public static void registerBlocks()
	{
		// Ores
		RegisterHelper.registerBlock(blockScandiumOre);
		RegisterHelper.registerBlock(blockLithiumOre);
		RegisterHelper.registerBlock(blockTitaniumOre);
		RegisterHelper.registerBlock(blockCobaltOre);
	}
}
