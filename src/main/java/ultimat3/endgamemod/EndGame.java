package ultimat3.endgamemod;

import ultimat3.endgamemod.blocks.OreSpawner;
import ultimat3.endgamemod.helpers.OreDictionaryHelper;
import ultimat3.endgamemod.init.ModBlocks;
import ultimat3.endgamemod.init.ModItems;
import ultimat3.endgamemod.init.ModRecipes;
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
@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.NAME)
public class EndGame {
	
	@Instance(Reference.MOD_ID)
	public static EndGame instance; // automatically populated by FML
	
	/** The creative tab for the mod */
	public static CreativeTabEndGame creaTab = new CreativeTabEndGame(Reference.MOD_ID);
	
	/** Spawns the ores into the world */
	private static OreSpawner oreSpawner = new OreSpawner();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		// registers content with Forge
		ModBlocks.registerBlocks();
		ModItems.registerItems();
		
		// add the Ore Dictionary entries
		OreDictionaryHelper.init();
	}

	/**
	 * Called upon the initialization phase of FML. 
	 * @param event
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// add the world gen
		GameRegistry.registerWorldGenerator(oreSpawner, 0);
		
		ModRecipes.initRecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
