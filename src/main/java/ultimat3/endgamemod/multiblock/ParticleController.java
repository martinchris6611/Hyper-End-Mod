package ultimat3.endgamemod.multiblock;


import ultimat3.endgamemod.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ParticleController extends BlockMultiBlock {
	  public ParticleController() {
	        super(Reference.GuiIds.PARTICLE_ACCELERATOR.ID(), "particleController");
	    }

	    @Override
	    public TileEntity createNewTileEntity(World world, int metadata) {
	        return new ParticleAcceleratorMultiblock();
	    }
}
