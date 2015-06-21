package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.machines.tileentity.TileMatterConsolidator;

public class BlockMatterConsolidator extends BlockMachine {

	public BlockMatterConsolidator(String name, int gui_ID) {
		super(name, gui_ID);
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileMatterConsolidator();
	}

}
