package ultimat3.endgamemod.fluids.tanks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileUltimat3Tank extends TileEntity implements IFluidHandler {

	protected FluidTank tank;

	public TileUltimat3Tank(int tankCapacity) {
		tank = new FluidTank(tankCapacity);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if (tank.getFluidAmount() == 0)
			return null;
		if (tank.getFluid().getFluid() != resource.getFluid())
			return null;
		return this.drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return tank.getFluidAmount() == 0
				|| (tank.getFluid().getFluid() == fluid && tank
						.getFluidAmount() < tank.getCapacity());
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return tank.getFluidAmount() > 0;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidStack fluid = null;
		if (tank.getFluid() != null)
			fluid = tank.getFluid().copy();
		return new FluidTankInfo[] { new FluidTankInfo(fluid,
				tank.getCapacity()) };
	}

	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		if (tags.getBoolean("hasFluid")) {
			tank.setFluid(FluidRegistry.getFluidStack(
					tags.getString("fluidName"), tags.getInteger("amount")));
		} else
			tank.setFluid(null);
	}

	@Override
	public void writeToNBT(NBTTagCompound tags) {
		super.writeToNBT(tags);
		FluidStack liquid = tank.getFluid();
		tags.setBoolean("hasFluid", liquid != null);
		if (liquid != null) {
			tags.setString("fluidName", liquid.getFluid().getName());
			tags.setInteger("amount", liquid.amount);
		}
	}
}
