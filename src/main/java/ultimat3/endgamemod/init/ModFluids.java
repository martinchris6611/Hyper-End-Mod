package ultimat3.endgamemod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import ultimat3.endgamemod.fluids.Ultimat3Fluid;
import ultimat3.endgamemod.fluids.blocks.Ultimat3FluidBlock;
import ultimat3.endgamemod.helpers.RegisterHelper;


public class ModFluids {

	public static Fluid extractedAir = new Ultimat3Fluid("extractedAir");
	public static Fluid nitrogen = new Ultimat3Fluid("nitrogen");
	public static Fluid oxygen = new Ultimat3Fluid("oxygen");
	public static Fluid xenon = new Ultimat3Fluid("xenon");

	public static Block extractedAirBlock = new Ultimat3FluidBlock("extractedAir", extractedAir, Material.water);
	public static Block nitrogenBlock = new Ultimat3FluidBlock("nitrogen", nitrogen, Material.water);

	public static void registerFluids() {
		assignFluid(extractedAir, extractedAirBlock);
		assignFluid(nitrogen, nitrogenBlock);
	}
	
	private static void assignFluid(Fluid fluid, Block block) {
		RegisterHelper.registerBlock(block);
		fluid.setBlock(block);
		fluid.setIcons(block.getIcon(0, 0), block.getIcon(1, 0));
	}

}
