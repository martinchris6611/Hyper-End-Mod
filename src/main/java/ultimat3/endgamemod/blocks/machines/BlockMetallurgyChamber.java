package ultimat3.endgamemod.blocks.machines;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMetallurgyChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMetallurgyChamber extends BlockMachine  {

	public BlockMetallurgyChamber(String name) {
		super(name);
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (player.isSneaking())
			return false;
			player.openGui(EndGame.instance, Reference.GuiIds.METALLURGY_CHAMBER.ID(), world, x, y, z);
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityMetallurgyChamber();
	}
}
