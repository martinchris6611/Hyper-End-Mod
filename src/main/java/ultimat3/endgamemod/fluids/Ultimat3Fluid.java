package ultimat3.endgamemod.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import ultimat3.endgamemod.Reference;

public class Ultimat3Fluid extends Fluid {

	public Ultimat3Fluid(String fluidName) {
		super(fluidName);
		setUnlocalizedName(Reference.MOD_ID + "_" + fluidName);
		FluidRegistry.registerFluid(this);
	}

}
