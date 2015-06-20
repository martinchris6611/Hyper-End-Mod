package ultimat3.endgamemod.init;

import net.minecraftforge.fluids.Fluid;
import ultimat3.endgamemod.fluids.Ultimat3Fluid;


public class ModFluids {

	public static Fluid extractedAir = new Ultimat3Fluid("extractedAir");
	public static Fluid liquidNitrogen = new Ultimat3Fluid("liquidNitrogen");
	public static Fluid liquidOxygen = new Ultimat3Fluid("liquidOxygen");
	public static Fluid liquidHidrogen = new Ultimat3Fluid("liquidHidrogen");
	public static Fluid liquidNeon = new Ultimat3Fluid("liquidNeon");
	public static Fluid liquidXenon = new Ultimat3Fluid("liquidXenon");
	
	//public static Block extractedAirBlock = new Ultimat3FluidBlock("blockExtractedAir", extractedAir, Material.water);

	public static void registerFluids() {
		//RegisterHelper.registerBlock(extractedAirBlock);
	}

}
