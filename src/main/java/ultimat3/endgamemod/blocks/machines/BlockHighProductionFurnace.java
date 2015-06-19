package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityHighProductionFurnace;

public class BlockHighProductionFurnace extends BlockMachine  {

	public BlockHighProductionFurnace(String name) {
		super(name, Reference.GuiIds.HIGH_PRODUCTION_FURNACE.ID());
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityHighProductionFurnace();
	}
}
