package ultimat3.expotech.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.expotech.blocks.machines.tileentity.TileExpoFurnace;
import ultimat3.expotech.gui.GuiExpoFurnace;
import ultimat3.expotech.gui.container.ContainerExpoFurnace;

public class BlockExpoFurnace extends BlockMachine {

	public BlockExpoFurnace(String name) {
		super(name, GuiExpoFurnace.class, ContainerExpoFurnace.class);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
			return new TileExpoFurnace();
	}

}
