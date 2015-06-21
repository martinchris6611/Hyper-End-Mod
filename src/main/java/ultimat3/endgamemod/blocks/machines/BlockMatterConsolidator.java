package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileMatterConsolidator;

public class BlockMatterConsolidator extends BlockMachine {

	public BlockMatterConsolidator(String name) {
		super(name, Reference.GuiIds.MATTER_CONSOLIDATOR.ID());
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileMatterConsolidator();
	}

}
