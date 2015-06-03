package ultimat3.endgamemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ultimat3.endgamemod.blocks.OreSpawner;
import ultimat3.endgamemod.blocks.Ultimat3BlockOre;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Main mod file. Initializes the mod, in short. Also adds event handlers and
 * stuff like that.
 * 
 * @author Ebilkill
 */
@Mod(modid = EndGame.MODID, version = EndGame.VERSION, name = "End game mod")
public class EndGame {
	@Instance(EndGame.MODID)
	public static EndGame instance; // automatically populated by FML

	/**
	 * The FML identifier for our mod.
	 */
	public static final String MODID = "u3_egm";

	/**
	 * The version of our mod.
	 */
	public static final String VERSION = "1.0A";

	/**
	 * The creative tab for our mod.
	 */
	public static CreativeTabEndGame creaTab = new CreativeTabEndGame(MODID);

	// Ores. Might make these metadata based later
	public static Block blockScandiumOre;
	public static Block blockLithiumOre;
	public static Block blockTitaniumOre;
	public static Block blockCobaltOre;

	/**
	 * Spawns the ores into the world.
	 */
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

		// and register them
		GameRegistry.registerBlock(blockCobaltOre, "oreCobalt");
		GameRegistry.registerBlock(blockLithiumOre, "oreLithium");
		GameRegistry.registerBlock(blockScandiumOre, "oreScandium");
		GameRegistry.registerBlock(blockTitaniumOre, "oreTitanium");
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
