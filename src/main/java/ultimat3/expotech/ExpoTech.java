package ultimat3.expotech;

import ultimat3.expotech.blocks.OreSpawner;
import ultimat3.expotech.helpers.OreDictionaryHelper;
import ultimat3.expotech.init.ModBlocks;
import ultimat3.expotech.init.ModFluids;
import ultimat3.expotech.init.ModItems;
import ultimat3.expotech.init.ModRecipes;
import ultimat3.expotech.init.ModRendering;
import ultimat3.expotech.init.ModTileEntities;
import ultimat3.expotech.network.GuiHandler;
import ultimat3.expotech.proxies.CommonProxy;
import cofh.thermalexpansion.util.crafting.SmelterManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;

// TODO Add a lot of comments everywhere

/**
 * Main mod file. Initializes the mod, in short. Also adds event handlers and
 * stuff like that.
 */
@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.NAME)
public class ExpoTech {
	
	@Instance(Reference.MOD_ID)
	public static ExpoTech instance; // automatically populated by FML
	
	// it is time
	
	// we need to add the proxies
	@SidedProxy(modId = Reference.MOD_ID, clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	/** The creative tab for the mod */
	public static CreativeTabExpoTech creaTab = new CreativeTabExpoTech(Reference.MOD_ID);
	
	/** Spawns the ores into the world */
	private static OreSpawner oreSpawner = new OreSpawner();

	/** The network handler, used for packets */
	public static SimpleNetworkWrapper network;
	
	public static GuiHandler guiHandler = new GuiHandler();
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Registers the network channel
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.NETWORK_CHANNEL);
		
		// registers content with Forge
		ModRendering.init();
		ModFluids.registerFluids();
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
		
		// add recipes
		ModRecipes.initRecipes();
		
		// add tile entities
		ModTileEntities.init();
		
		// Register GuiHandler
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Add custom item renderers
		proxy.registerItemRenderers();
		ModRecipes.initTERecipes();
		SmelterManager.refreshRecipes();
	}
}
