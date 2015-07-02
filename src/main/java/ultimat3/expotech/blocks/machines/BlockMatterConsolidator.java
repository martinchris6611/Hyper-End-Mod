package ultimat3.expotech.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.expotech.blocks.machines.tileentity.TileMatterConsolidator;
import ultimat3.expotech.gui.GuiMatterConsolidator;
import ultimat3.expotech.gui.container.ContainerMatterConsolidator;

public class BlockMatterConsolidator extends BlockMachine {

	public BlockMatterConsolidator(String name) {
		super(name, GuiMatterConsolidator.class, ContainerMatterConsolidator.class);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
			return new TileMatterConsolidator();
	}

}
