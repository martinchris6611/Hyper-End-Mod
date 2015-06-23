package ultimat3.endgamemod.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityForcefieldController;

public class BlockForcefieldController extends BlockMachine {

	public BlockForcefieldController(String name) {
		super(name,  Reference.GuiIds.FORCEFIELD_CONTROLLER.ID());
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityForcefieldController();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int var1) {
		TileEntityForcefieldController tile = (TileEntityForcefieldController) world.getTileEntity(x, y, z);
		tile.eraseField(tile.shape, tile.radius);
		tile.shape = 0;
		tile.radius= 0;
		super.breakBlock(world, x, y, z, block, var1);
	}
	
}
