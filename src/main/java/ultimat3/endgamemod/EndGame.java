package ultimat3.endgamemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ultimat3.endgamemod.blocks.OreSpawner;
import ultimat3.endgamemod.blocks.Ultimat3BlockOre;

/**
 * Main mod file. Initializes the mod, in short. Also adds event handlers and
 * stuff like that.
 * 
 * @author Ebilkill
 */
@Mod(modid = EndGame.MODID, version = EndGame.VERSION)
public class EndGame {
	@Instance(EndGame.MODID)
	public static EndGame instance; // automatically populated by FML

	public static final String MODID = "U3-EGM";
	public static final String VERSION = "1.0A";

	public static Block blockScandiumOre;
	public static Block blockLithiumOre;
	public static Block blockTitaniumOre;
	public static Block blockCobaltOre;

	private static OreSpawner spawner = new OreSpawner();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Create all blocks
		blockScandiumOre = new Ultimat3BlockOre("oreScandium", Material.rock)
				.setHardness(7F);
		blockLithiumOre = new Ultimat3BlockOre("oreLithium", Material.rock)
				.setHardness(7F);
		blockTitaniumOre = new Ultimat3BlockOre("oreTitanium", Material.rock)
				.setHardness(7F);
		blockCobaltOre = new Ultimat3BlockOre("oreCobalt", Material.rock)
				.setHardness(7F);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Set block harvest levels
		blockScandiumOre.setHarvestLevel("pickaxe", 3);
		blockLithiumOre.setHarvestLevel("pickaxe", 2);
		blockTitaniumOre.setHarvestLevel("pickaxe", 3);
		blockCobaltOre.setHarvestLevel("pickaxe", 2);
		
		// add the world gen
		GameRegistry.registerWorldGenerator(spawner, 1);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
