package ultimat3.expotech.fluids;

import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Ultimat3Fluid extends Fluid {

	public Ultimat3Fluid(String fluidName) {
		super(fluidName);
		FluidRegistry.registerFluid(this);
	}
	
    @Override
	public IIcon getStillIcon()
    {
        return this.getBlock().getIcon(0, 0);
    }

    @Override
	public IIcon getFlowingIcon()
    {
        return this.getBlock().getIcon(1, 0);
    }

}
