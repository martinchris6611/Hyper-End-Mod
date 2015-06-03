package ultimat3.endgamemod;

import ultimat3.endgamemod.blocks.OreSpawner;
import ultimat3.endgamemod.init.ModBlocks;
import ultimat3.endgamemod.init.ModItems;
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
@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.NAME)
public class EndGame {
	
	@Instance(Reference.MODID)
	public static EndGame instance; // automatically populated by FML
	/** The creative tab for the mod */
	public static CreativeTabEndGame creaTab = new CreativeTabEndGame(Reference.MODID);
	/** Spawns the ores into the world */
	private static OreSpawner spawner = new OreSpawner();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		// registers content with Forge
		ModBlocks.registerBlocks();
		ModItems.registerItems();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		// add the world gen
		GameRegistry.registerWorldGenerator(spawner, 1);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
