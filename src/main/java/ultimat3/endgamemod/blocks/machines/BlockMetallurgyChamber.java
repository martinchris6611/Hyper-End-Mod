package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMetallurgyChamber;

public class BlockMetallurgyChamber extends BlockMachine  {

	public BlockMetallurgyChamber(String name) {
		super(name, Reference.GuiIds.METALLURGY_CHAMBER.ID());
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
			return new TileEntityMetallurgyChamber();
	}
}
