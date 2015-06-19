package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityProductionFurnace;

public class BlockProductionFurnace extends BlockMachine  {

	public BlockProductionFurnace(String name) {
		super(name, Reference.GuiIds.PRODUCTION_FURNACE.ID());
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityProductionFurnace();
	}
}
