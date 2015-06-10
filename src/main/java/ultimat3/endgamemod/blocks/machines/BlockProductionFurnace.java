package ultimat3.endgamemod.blocks.machines;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityProductionFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockProductionFurnace extends BlockMachine  {

	public BlockProductionFurnace(String name) {
		super(name);
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (player.isSneaking())
			return false;
			player.openGui(EndGame.instance, Reference.GuiIds.PRODUCTION_FURNACE.ID(), world, x, y, z);
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityProductionFurnace();
	}
}
