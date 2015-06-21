package ultimat3.endgamemod.blocks.machines.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ultimat3.endgamemod.init.ModFluids;
import cofh.api.energy.EnergyStorage;

public class TileAirSeparator extends TileEntityMachine implements
		IFluidHandler {

	private final FluidTank inputTank;
	private final FluidTank outputTank;

	public TileAirSeparator() {
		super(new ItemStack[1], "airSeparator", new EnergyStorage(400000));
		inputTank = new FluidTank(32000);
		outputTank = new FluidTank(32000);
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (inputTank.getFluidAmount() >= 2
					&& storage.getEnergyStored() >= 100
					&& outputTank.getCapacity() > outputTank.getFluidAmount()) {
				
				
				FluidStack amount = inputTank.drain(
						Math.min(Math.min(inputTank.getFluidAmount() & (~1),
								storage.getEnergyStored() / 50),
								(outputTank.getCapacity() - outputTank
										.getFluidAmount()) * 2), true);
				
				
				storage.modifyEnergyStored(-amount.amount * 50);
				outputTank.fill(new FluidStack(ModFluids.nitrogen, amount.amount/2), true);
			}
		}
	}

	// / FLUID HANDLING

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource.getFluid().equals(ModFluids.extractedAir)
				&& from.equals(ForgeDirection.DOWN)) {
			return inputTank.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if (from.equals(ForgeDirection.DOWN))
			return null;
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
		if (!from.equals(ForgeDirection.DOWN))
			return false;
		return inputTank.getFluidAmount() == 0
				|| (inputTank.getFluid().getFluid() == fluid && inputTank
						.getFluidAmount() < inputTank.getCapacity());
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if (from.equals(ForgeDirection.DOWN))
			return false;
		return outputTank.getFluidAmount() > 0;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidStack f1, f2;
		f1 = f2 = null;
		if (inputTank.getFluid() != null)
			f1 = inputTank.getFluid().copy();
		if (outputTank.getFluid() != null)
			f2 = outputTank.getFluid().copy();
		return new FluidTankInfo[] {
				new FluidTankInfo(f1, inputTank.getCapacity()),
				new FluidTankInfo(f2, outputTank.getCapacity()) };
	}

	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		if (tags.getBoolean("hasInputFluid")) {
			inputTank.setFluid(new FluidStack(ModFluids.extractedAir, tags
					.getInteger("inputFluidAmount")));
		} else
			inputTank.setFluid(null);
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
		FluidStack input = inputTank.getFluid();
		FluidStack output = outputTank.getFluid();
		tags.setBoolean("hasInputFluid", input != null);
		tags.setBoolean("hasOutputFluid", output != null);
		if (input != null) {
			tags.setInteger("inputFluidAmount", input.amount);
		}
		if (output != null) {
			tags.setString("fluidName", output.getFluid().getName());
			tags.setInteger("outputFluidAmount", output.amount);
		}
	}
}
