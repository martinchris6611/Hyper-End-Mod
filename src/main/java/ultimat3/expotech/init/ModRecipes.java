package ultimat3.expotech.init;

import static ultimat3.expotech.init.ModBlocks.*;
import static ultimat3.expotech.init.ModItems.*;

import java.util.ArrayList;

import cofh.thermalexpansion.util.crafting.SmelterManager;
import cofh.thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ultimat3.expotech.ExpoTech;
import ultimat3.expotech.recipes.MatterConsolidatorRecipes;
import ultimat3.expotech.recipes.MechanicalAssemblerRecipes;

/**
 * Initialize all recipes here. Called from the
 * {@link ExpoTech#init(cpw.mods.fml.common.event.FMLInitializationEvent)}
 * method.
 */
public class ModRecipes {

	public static void initRecipes() {
		// Basic recipes
		initShapedRecipes();
		initShapelessRecipes();
		initSmeltingRecipes();
		// Machine recipes
		initConsolidatorRecipes();
		initExpoRecipes();
		initAssemblerRecipes();
	}

	public static void initTERecipes() {
		String[] names = OreDictionary.getOreNames();
		if (names == null)
			return;
		for (String name : names) {
			if (name.startsWith("ore")) {

				ArrayList<ItemStack> ores = OreDictionary.getOres(name);
				if (ores == null || ores.isEmpty())
					return;
				ArrayList<ItemStack> ingots = OreDictionary.getOres("ingot" + name.substring(3, name.length()));
				if (ingots == null || ingots.isEmpty())
					return;
				ItemStack ingot = ingots.get(0);
				if (ingot == null)
					return;

				for (ItemStack ore : ores) {
					SmelterManager.addRecipe(8000, new ItemStack(itemThermite), ore, ingot, true);
				}

			}
		}
	}

	private static void initAssemblerRecipes() {
		MechanicalAssemblerRecipes.addRecipe(Blocks.diamond_block, "DDDD", "D  D", "D  D", "DDDD", 'D', Blocks.dirt);
	}

	private static void initExpoRecipes() {

	}

	private static void initConsolidatorRecipes() {

		MatterConsolidatorRecipes.addRecipe(Blocks.iron_block, Blocks.iron_block, Blocks.iron_block, LCIP);

		MatterConsolidatorRecipes.addRecipe(LCIP, LCIP, LCIP, RCIP);

		MatterConsolidatorRecipes.addRecipe(RCIP, RCIP, RCIP, HCIP);

		// TODO Somehow make every metal in the game compatible with this

		for (int meta = 0; meta < metalBlockNames.length; ++meta) {
			MatterConsolidatorRecipes.addRecipe(Blocks.glass, Blocks.glass, new ItemStack(blockMetals, 1, meta),
					new ItemStack(blockMetallicGlass));
		}

		MatterConsolidatorRecipes.addRecipe(Blocks.glass, Blocks.glass, Blocks.iron_block, blockMetallicGlass);

		MatterConsolidatorRecipes.addRecipe(Blocks.glass, Blocks.glass, Blocks.gold_block, blockMetallicGlass);

	}

	// noformat
	private static void initShapedRecipes() {

	}

	private static void initShapelessRecipes() {
		// normal
		for (int meta = 0; meta < nuggetNames.length; ++meta) {
			// Ingots->nuggets
			GameRegistry.addShapelessRecipe(new ItemStack(itemNuggets, 9, meta),
					new Object[] { new ItemStack(itemIngots, 1, meta) });

			// Blocks->ingots
			GameRegistry.addShapelessRecipe(new ItemStack(itemIngots, 9, meta),
					new Object[] { new ItemStack(blockMetals, 1, meta) });
		}

		GameRegistry.addShapelessRecipe(new ItemStack(itemThermite),
				new Object[] { TFItems.dustIron, TFItems.dustTin, TFItems.dustTin  });

	}

	private static void initSmeltingRecipes() {
		for (int i = 0; i < ModItems.dustNames.length; i++) {
			GameRegistry.addSmelting(new ItemStack(ModItems.itemDusts, 1, i), new ItemStack(itemIngots, 1, i), 1F);
		}
	}
}
