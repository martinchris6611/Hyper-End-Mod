package ultimat3.expotech.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.expotech.blocks.machines.tileentity.TileForcefieldController;
import ultimat3.expotech.gui.GuiForcefieldController;
import ultimat3.expotech.gui.container.ContainerForcefieldController;

public class BlockForcefieldController extends BlockMachine {

	public BlockForcefieldController(String name) {
		super(name,  GuiForcefieldController.class, ContainerForcefieldController.class);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
			return new TileForcefieldController();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int var1) {
		TileForcefieldController tile = (TileForcefieldController) world.getTileEntity(x, y, z);
		//tile.eraseField(tile.shape, tile.radius);
		tile.shape = 0;
		tile.radius= 0;
		super.breakBlock(world, x, y, z, block, var1);
	}
	
}
