package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntitySuperCompressor;

public class BlockSuperCompressor extends BlockMachine  {

	public BlockSuperCompressor(String name) {
		super(name, Reference.GuiIds.SUPER_COMPRESSOR.ID());
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
			return new TileEntitySuperCompressor();
	}
}
