package ultimat3.endgamemod.multiblock;


import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;

public class ParticleController extends BlockMultiBlock {
	  public ParticleController() {
	        super( "particleController", Reference.GuiIds.PARTICLE_ACCELERATOR.ID());
	    }

	    @Override
	    public TileEntity createNewTileEntity(World world, int metadata) {
	        return new ParticleAcceleratorMultiblock();
	    }

		@Override
		public boolean hasTileEntity(int metadata) {
			return true;
		}
}
