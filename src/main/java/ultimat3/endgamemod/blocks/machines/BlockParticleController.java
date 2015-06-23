package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockParticleController extends BlockMachine{

	public BlockParticleController(String name, int gui_ID) {
		super(name, gui_ID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return false;
	}

}
