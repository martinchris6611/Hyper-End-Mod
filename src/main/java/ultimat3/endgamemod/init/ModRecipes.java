package ultimat3.endgamemod.init;

import static ultimat3.endgamemod.init.ModBlocks.HCIP;
import static ultimat3.endgamemod.init.ModBlocks.LCIP;
import static ultimat3.endgamemod.init.ModBlocks.RCIP;
import static ultimat3.endgamemod.init.ModBlocks.blockAirExtractor;
import static ultimat3.endgamemod.init.ModBlocks.blockAirSeparator;
import static ultimat3.endgamemod.init.ModBlocks.blockExpoFurnace;
import static ultimat3.endgamemod.init.ModBlocks.blockForcefieldController;
import static ultimat3.endgamemod.init.ModBlocks.blockMachineHousing;
import static ultimat3.endgamemod.init.ModBlocks.blockMatterConsolidator;
import static ultimat3.endgamemod.init.ModBlocks.blockMechanicalAssembler;
import static ultimat3.endgamemod.init.ModBlocks.blockMetallicGlass;
import static ultimat3.endgamemod.init.ModBlocks.blockMetallicGlassPane;
import static ultimat3.endgamemod.init.ModBlocks.blockMetallurgyChamber;
import static ultimat3.endgamemod.init.ModBlocks.blockMetals;
import static ultimat3.endgamemod.init.ModBlocks.blockMisc;
import static ultimat3.endgamemod.init.ModBlocks.blockOres;
import static ultimat3.endgamemod.init.ModBlocks.reinforcedIronBlock;
import static ultimat3.endgamemod.init.ModItems.airFilter;
import static ultimat3.endgamemod.init.ModItems.aluminumAlloy;
import static ultimat3.endgamemod.init.ModItems.armSegment;
import static ultimat3.endgamemod.init.ModItems.carbonSheet;
import static ultimat3.endgamemod.init.ModItems.circuitBoard;
import static ultimat3.endgamemod.init.ModItems.energyCell;
import static ultimat3.endgamemod.init.ModItems.forcefieldEmitter;
import static ultimat3.endgamemod.init.ModItems.ingotNames;
import static ultimat3.endgamemod.init.ModItems.ironCobaltMagnet;
import static ultimat3.endgamemod.init.ModItems.ironDust;
import static ultimat3.endgamemod.init.ModItems.itemCoils;
import static ultimat3.endgamemod.init.ModItems.itemDusts;
import static ultimat3.endgamemod.init.ModItems.itemIngots;
import static ultimat3.endgamemod.init.ModItems.itemMisc;
import static ultimat3.endgamemod.init.ModItems.itemNuggets;
import static ultimat3.endgamemod.init.ModItems.itemThermite;
import static ultimat3.endgamemod.init.ModItems.itemVanillaMetals;
import static ultimat3.endgamemod.init.ModItems.lithium;
import static ultimat3.endgamemod.init.ModItems.magnesium;
import static ultimat3.endgamemod.init.ModItems.nuggetNames;
import static ultimat3.endgamemod.init.ModItems.refinedCarbon;
import static ultimat3.endgamemod.init.ModItems.ringMagnet;
import static ultimat3.endgamemod.init.ModItems.roboticArm;
import static ultimat3.endgamemod.init.ModItems.roboticJoint;
import static ultimat3.endgamemod.init.ModItems.scandium;
import static ultimat3.endgamemod.init.ModItems.squareMagnet;
import static ultimat3.endgamemod.init.ModItems.titanium;
import static ultimat3.endgamemod.init.ModItems.titaniumPlating;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.recipes.ExpoFurnaceRecipes;
import ultimat3.endgamemod.recipes.MatterConsolidatorRecipes;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Initialize all recipes here. Called from the {@link EndGame#init(cpw.mods.fml.common.event.FMLInitializationEvent)}
 * method.
 * 
 * @author Ebilkill
 */
public class ModRecipes {
	
	/**
	 * Compression recipes
	 */
	private static final RecipesCompression	COMPRESSION	= new RecipesCompression();
	private static final FactoryRecipes	METALLURGY	= new FactoryRecipes(false);
	private static int aluminum;
	
	public static RecipesCompression compression() {
		return COMPRESSION;
	}
	
	public static FactoryRecipes metallurgy() {
		return METALLURGY;
	}
	
	public static void initRecipes() {
		// Basic recipes
		initShapedRecipes();
		initShapelessRecipes();
		initSmeltingRecipes();
		
		// Compression inits itself now
		initMetallurgyRecipes();
		initConsolidatorRecipes();
		initExpoRecipes();
	}
	
	private static void initExpoRecipes() {
		ExpoFurnaceRecipes.addRecipe(Blocks.coal_block, new ItemStack(itemMisc, 1, refinedCarbon), 128);
		ExpoFurnaceRecipes.addRecipe(new ItemStack(blockOres, 1, lithium), new ItemStack(itemIngots, 1, lithium), 256);
		ExpoFurnaceRecipes.addRecipe(new ItemStack(blockOres, 1, magnesium), new ItemStack(itemIngots, 1, magnesium), 384);
		ExpoFurnaceRecipes.addRecipe(new ItemStack(blockOres, 1, titanium), new ItemStack(itemIngots, 1, titanium), 512);
		ExpoFurnaceRecipes.addRecipe(new ItemStack(blockOres, 1, scandium), new ItemStack(itemIngots, 1, scandium), 1024);
		
	}

	private static void initConsolidatorRecipes() {
		
		MatterConsolidatorRecipes.addRecipe(
				new ItemStack(itemIngots, 1, titanium),
				new ItemStack(itemIngots, 1, titanium),
				new ItemStack(itemIngots, 1, titanium), new ItemStack(itemMisc, 1, titaniumPlating));
		
		
		MatterConsolidatorRecipes.addRecipe(
				Blocks.redstone_block,
				Blocks.gold_block,
				Blocks.emerald_block, new ItemStack(itemMisc, 1, circuitBoard));
		
		MatterConsolidatorRecipes.addRecipe(
				Blocks.redstone_block,
				new ItemStack(blockMetals, 1, lithium),
				new ItemStack(itemMisc, 1, circuitBoard), new ItemStack(itemMisc, 1, energyCell));
		
		MatterConsolidatorRecipes.addRecipe(
				Blocks.iron_block,
				Blocks.iron_block,
				Blocks.iron_block, new ItemStack(blockMisc, 1, LCIP));
		
		MatterConsolidatorRecipes.addRecipe(
				new ItemStack(blockMisc, 1, LCIP),
				new ItemStack(blockMisc, 1, LCIP),
				new ItemStack(blockMisc, 1, LCIP), new ItemStack(blockMisc, 1, RCIP));
		
		MatterConsolidatorRecipes.addRecipe(
				new ItemStack(blockMisc, 1, RCIP),
				new ItemStack(blockMisc, 1, RCIP),
				new ItemStack(blockMisc, 1, RCIP), new ItemStack(blockMisc, 1, HCIP));
		
		MatterConsolidatorRecipes.addRecipe(
				Blocks.diamond_block,
				Blocks.gold_block,
				Blocks.glowstone, new ItemStack(itemMisc, 1, forcefieldEmitter));
		
		for(int meta = 0; meta < nuggetNames.length; ++meta) {
			MatterConsolidatorRecipes.addRecipe(
					Blocks.glass,
					Blocks.glass,
					new ItemStack(blockMetals, 1, meta), new ItemStack(blockMetallicGlass));
		}
		
		MatterConsolidatorRecipes.addRecipe(
				Blocks.glass,
				Blocks.glass,
				new ItemStack(Blocks.iron_block), new ItemStack(blockMetallicGlass));
		
		MatterConsolidatorRecipes.addRecipe(
				Blocks.glass,
				Blocks.glass,
				new ItemStack(Blocks.gold_block), new ItemStack(blockMetallicGlass));
		
	}

	// noformat
	private static void initShapedRecipes() {
		// normal
		for(int meta = 0; meta < ingotNames.length; ++meta) {
			// Nuggets-> ingots
			GameRegistry.addShapedRecipe(new ItemStack(itemIngots, 1, meta), new Object[] {
				"XXX", "XXX", "XXX", 'X', new ItemStack(itemNuggets, 1, meta)
			});
			
			// Ingots-> blocks
			GameRegistry.addShapedRecipe(new ItemStack(blockMetals, 1, meta), new Object[] {
				"III", "III", "III", 'I', new ItemStack(itemIngots, 1, meta)
			}); // I for ingot!
		}
		
		/*GameRegistry.addShapedRecipe(new ItemStack(blockMisc, 1, compressedSteel), new Object[] {
			"III", "III", "III", 'I', new ItemStack(blockMetals, 1, steel)
		});*/

		GameRegistry.addShapedRecipe(new ItemStack(blockMisc, 1, reinforcedIronBlock), new Object[] {
			"XXX", "XYX", "XXX", 'X', Items.diamond, 'Y', Blocks.iron_block
		});
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, ringMagnet), new Object[] {
			" M ", "M M", " M ", 'M', new ItemStack(itemMisc, 1, ironCobaltMagnet)
		});
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, squareMagnet), new Object[] {
			"MM", "MM", 'M', new ItemStack(itemMisc, 1, ironCobaltMagnet)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, carbonSheet), new Object[] {
			"CCC", 'C', new ItemStack(itemMisc, 1, refinedCarbon)
		});		
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, aluminumAlloy), new Object[] {
			"AA", "AA", 'A', new ItemStack(itemIngots, 1, ModItems.aluminum)
		});
		GameRegistry.addShapedRecipe(new ItemStack(blockMetallicGlassPane, 16), new Object[] {
			"GGG", "GGG", 'G', new ItemStack(blockMetallicGlass)
		});
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, roboticJoint), new Object[] {
			" N ", "NIN", " N ", 'N', new ItemStack(itemNuggets, 1, titanium), 'I', new ItemStack(itemIngots, 1, titanium)
		});
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, armSegment), new Object[] {
			"PJP", 'P', new ItemStack(itemMisc, 1, titaniumPlating), 'J', new ItemStack(itemMisc, 1, roboticJoint)
		});
		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, roboticArm), new Object[] {
			"SCS", "SSS", " S ", 'S', new ItemStack(itemMisc, 1, armSegment), 'C', new ItemStack(itemMisc, 1, circuitBoard)
		});

		GameRegistry.addShapedRecipe(new ItemStack(itemMisc, 1, airFilter), new Object[] {
			"WWW", "WIW", "WWW", 'W', Blocks.wool, 'I', Items.iron_ingot
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(blockMachineHousing), new Object[] {
			"PEP", "PGP", "CPC", 'P', new ItemStack(itemMisc, 1, aluminumAlloy),
			'E', new ItemStack(itemMisc, 1, energyCell),
			'G', Blocks.glass,
			'C', new ItemStack(itemMisc, 1, circuitBoard)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(blockMechanicalAssembler), new Object[] {
			"TCT", "AHA", "TET", 'T', new ItemStack(blockMetals, 1, titanium),
			'C', new ItemStack(itemMisc, 1, circuitBoard),
			'A', new ItemStack(itemMisc, 1, roboticArm),
			'E', new ItemStack(itemMisc, 1, energyCell),
			'H', new ItemStack(blockMachineHousing)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(blockForcefieldController), new Object[] {
			"EEE", "CCC", "GHG", 'E', new ItemStack(itemMisc, 1, forcefieldEmitter),
			'C', new ItemStack(itemMisc, 1, energyCell),
			'G', new ItemStack(blockMetals, 1, titanium),
			'H', new ItemStack(blockMachineHousing)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(blockAirExtractor), new Object[] {
			"SFS", "FHF", "SFS", 'S', new ItemStack(blockMetals, 1, titanium),
			'F', new ItemStack(itemMisc, 1, airFilter),
			'H', new ItemStack(blockMachineHousing)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(blockAirSeparator), new Object[] {
			"EFE", "PHP", "SFS", 'S', new ItemStack(blockMetals, 1, titanium),
			'F', new ItemStack(itemMisc, 1, airFilter),
			'H', new ItemStack(blockAirExtractor),
			'P', new ItemStack(Blocks.piston),
			'E', new ItemStack(itemMisc, 1, energyCell)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(blockMatterConsolidator), new Object[] {
			"SDS", "RNR", "SGS", 'S', new ItemStack(Blocks.iron_block),
			'D', new ItemStack(Blocks.diamond_block),
			'R', new ItemStack(Blocks.redstone_block),
			'N', new ItemStack(Items.nether_star),
			'G', new ItemStack(Blocks.gold_block)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(blockExpoFurnace), new Object[] {
			"SFS", "CHC", "SAS", 'S', new ItemStack(Blocks.iron_block),
			'A', new ItemStack(itemMisc, 1, airFilter),
			'H', new ItemStack(blockMachineHousing),
			'F', new ItemStack(blockMetallurgyChamber),
			'C', new ItemStack(itemCoils, 1, 0)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(blockMetallurgyChamber), new Object[] {
			"HHH", "HFH", "HHH", 'H', new ItemStack(blockMisc, 1, HCIP),
			'F', new ItemStack(Blocks.furnace)
		});
		
		
		
	}
	
	private static void initShapelessRecipes() {
		// normal
		for(int meta = 0; meta < nuggetNames.length; ++meta) {
			// Ingots->nuggets
			GameRegistry.addShapelessRecipe(new ItemStack(itemNuggets, 9, meta), new Object[] {
				new ItemStack(itemIngots, 1, meta)
			});
			
			// Blocks->ingots
			GameRegistry.addShapelessRecipe(new ItemStack(itemIngots, 9, meta), new Object[] {
				new ItemStack(blockMetals, 1, meta)
			});
		}
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemThermite), new Object[] {
				 new ItemStack(itemVanillaMetals, 2, ironDust),
				 new ItemStack(itemDusts, 1, aluminum)
		});
		
		/*GameRegistry.addShapelessRecipe(new ItemStack(blockMetals, 9, steel), new Object[] {
			 new ItemStack(blockMisc, 1, compressedSteel)
		});*/
		
	}
	
	private static void initSmeltingRecipes() {
		// ores -> ingots. TODO add better XP values, these must be pretty sucky. Just added a random value.
		GameRegistry.addSmelting(new ItemStack(blockOres, 1,  ModItems.aluminum), new ItemStack(itemIngots, 1,  ModItems.aluminum), 2F);
		GameRegistry.addSmelting(new ItemStack(blockOres, 1, ModItems.cobalt), new ItemStack(itemIngots, 1, ModItems.cobalt), 2.0F);
		for(int i=0; i<ModItems.dustNames.length; i++) {
			GameRegistry.addSmelting(new ItemStack(ModItems.itemDusts, 1, i), new ItemStack(itemIngots, 1, i), 2F);
		}
	}
	//format
	
	// Recipes for Metallurgy Go here WIP
	private static void initMetallurgyRecipes() {
		metallurgy().addRecipe(new ItemStack(Blocks.coal_block), new ItemStack(itemMisc, 1, refinedCarbon));
		metallurgy().addRecipe(new ItemStack(blockOres, 1, lithium), new ItemStack(itemIngots, 1, lithium));
		metallurgy().addRecipe(new ItemStack(blockOres, 1, magnesium), new ItemStack(itemIngots, 1, magnesium));
		metallurgy().addRecipe(new ItemStack(blockOres, 1, titanium), new ItemStack(itemIngots, 1, titanium));
		metallurgy().addRecipe(new ItemStack(blockOres, 1, scandium), new ItemStack(itemIngots, 1, scandium));
	}
	
	/**
	 * Use this to make new recipe managers for our machines.
	 * 
	 * @author Ebilkill
	 */
	public static class FactoryRecipes {
		private final Map<ItemStack, ItemStack>	recipesMap;
		private final boolean						useInputAmount;
		
		public FactoryRecipes(boolean useInputAmount) {
			this.recipesMap = new HashMap<ItemStack, ItemStack>();
			this.useInputAmount = useInputAmount;
		}
		
		/**
		 * Adds a recipe that turns input into output.
		 * 
		 * @param input The item in the input (metadata sensitive).
		 * @param output The output item.
		 */
		public void addRecipe(ItemStack input, ItemStack output) {
			if (this.useInputAmount)
				this.recipesMap.put(input.copy(), output);
			else
				this.recipesMap.put(new ItemStack(input.getItem(), 1, input.getItemDamage()), output);
		}
		
		/**
		 * Gets the result of this input.
		 * 
		 * @param input The item in the input.
		 * @return The result of this operation, or null if there is no result.
		 */
		@SuppressWarnings("rawtypes")
		public ItemStack getResult(ItemStack input) {
			Iterator iterator = this.recipesMap.entrySet().iterator();
			Entry entry;
			
			do {
				if (!iterator.hasNext()) {
					return null;
				}
				
				entry = (Entry) iterator.next();
			} while (!this.areItemsEqual(input, (ItemStack) entry.getKey()));
			
			return (ItemStack) entry.getValue();
		}
		
		private boolean areItemsEqual(ItemStack input, ItemStack recipeInput) {
			return recipeInput.getItem() == input.getItem()
					&& (recipeInput.getItemDamage() == 32767 || recipeInput.getItemDamage() == input.getItemDamage());
		}
	}
}
