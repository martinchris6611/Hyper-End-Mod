package ultimat3.expotech.blocks.machines.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ultimat3.expotech.init.ModFluids;
import cofh.api.energy.EnergyStorage;

public class TileAirSeparator extends TileMachine implements
		IFluidHandler {
	private final FluidTank outputTank;

	public TileAirSeparator() {
		super(new ItemStack[1], "airSeparator", new EnergyStorage(400000));
		outputTank = new FluidTank(32000);
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			int amount = outputTank.fill(new FluidStack(ModFluids.nitrogen,
					storage.getEnergyStored()/1000), true);
			storage.modifyEnergyStored(-amount * 1000);

		}
	}

	// / FLUID HANDLING

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (outputTank.getFluidAmount() == 0)
			return null;
		if (outputTank.getFluid().getFluid() != resource.getFluid())
			return null;
		return this.drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return outputTank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
			return false;
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return outputTank.getFluidAmount() > 0;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidStack fstack = null;
		if (outputTank.getFluid() != null)
			fstack = outputTank.getFluid().copy();
		return new FluidTankInfo[] {
				new FluidTankInfo(fstack, outputTank.getCapacity()) };
	}

	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		if (tags.getBoolean("hasOutputFluid")) {
			outputTank.setFluid(FluidRegistry.getFluidStack(
					tags.getString("fluidName"),
					tags.getInteger("outputFluidAmount")));
		} else
			outputTank.setFluid(null);
	}

	@Override
	public void writeToNBT(NBTTagCompound tags) {
		super.writeToNBT(tags);
		FluidStack output = outputTank.getFluid();
		tags.setBoolean("hasOutputFluid", output != null);
		if (output != null) {
			tags.setString("fluidName", output.getFluid().getName());
			tags.setInteger("outputFluidAmount", output.amount);
		}
	}
}
