package ultimat3.endgamemod.blocks.machines;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntitySuperCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSuperCompressor extends BlockMachine  {

	public BlockSuperCompressor(String name) {
		super(name);
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (player.isSneaking())
			return false;
			player.openGui(EndGame.instance, Reference.GuiIds.SUPER_COMPRESSOR.ID(), world, x, y, z);
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntitySuperCompressor();
	}
}
