package ultimat3.expotech.multiblock;


import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.expotech.gui.GuiParticleAccelerator;
import ultimat3.expotech.gui.container.ContainerParticleAccelerator;

public class ParticleController extends BlockMultiBlock {
	  public ParticleController(String name) {
	        super(name, GuiParticleAccelerator.class, ContainerParticleAccelerator.class);
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
