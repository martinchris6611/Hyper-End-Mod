package ultimat3.expotech.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.expotech.blocks.machines.tileentity.TileMechanicalAssembler;
import ultimat3.expotech.gui.GuiMechanicalAssembler;
import ultimat3.expotech.gui.container.ContainerMechanicalAssembler;

public class BlockMechanicalAssembler extends BlockMachine {

	public BlockMechanicalAssembler(String name) {
		super(name, GuiMechanicalAssembler.class, ContainerMechanicalAssembler.class);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileMechanicalAssembler();
	}

}
