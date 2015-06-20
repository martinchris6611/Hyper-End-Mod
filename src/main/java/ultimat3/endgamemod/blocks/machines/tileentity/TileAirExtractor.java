package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraftforge.fluids.FluidStack;
import ultimat3.endgamemod.init.ModFluids;


public class TileAirExtractor extends TileFluidEnergy  {

	@Override
	public void process() {
		if(storage.getEnergyStored()>=1000) {
			int amount = storage.getEnergyStored() / 1000;
			amount = tank.fill(new FluidStack(ModFluids.extractedAir, amount), true);
			storage.modifyEnergyStored(-1000*(storage.getEnergyStored()/1000 - amount));
		}
	}
	

}
