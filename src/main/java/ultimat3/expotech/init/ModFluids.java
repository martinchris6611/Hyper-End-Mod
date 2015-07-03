package ultimat3.expotech.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import ultimat3.expotech.fluids.Ultimat3Fluid;
import ultimat3.expotech.fluids.blocks.Ultimat3FluidBlock;
import ultimat3.expotech.helpers.RegisterHelper;


public class ModFluids {

	public static Fluid extractedAir = new Ultimat3Fluid("extractedAir");
	public static Fluid nitrogen = new Ultimat3Fluid("nitrogen");
	public static Fluid oxygen = new Ultimat3Fluid("oxygen");
	public static Fluid xenon = new Ultimat3Fluid("xenon");

	public static Block extractedAirBlock = new Ultimat3FluidBlock("extractedAir", extractedAir, Material.water);
	public static Block nitrogenBlock = new Ultimat3FluidBlock("nitrogen", nitrogen, Material.water);
	public static Block oxygenBlock = new Ultimat3FluidBlock("oxygen", oxygen, Material.water);
	public static Block xenonBlock = new Ultimat3FluidBlock("xenon", xenon, Material.water);

	// TODO fix missing textures, 404 designer not found
	
	public static void registerFluids() {
		assignFluid(extractedAir, extractedAirBlock);
		assignFluid(nitrogen, nitrogenBlock);
		assignFluid(oxygen, oxygenBlock);
		assignFluid(xenon, xenonBlock);
	}
	
	private static void assignFluid(Fluid fluid, Block block) {
		RegisterHelper.registerBlock(block);
		fluid.setBlock(block);
		fluid.setIcons(block.getIcon(0, 0), block.getIcon(1, 0));
	}

}
