package ultimat3.endgamemod.blocks.machines;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityHighProductionFurnace;
import ultimat3.endgamemod.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHighProductionFurnace extends BlockMachine  {

	public BlockHighProductionFurnace(String name) {
		super(name, Reference.GuiIds.HIGH_PRODUCTION_FURNACE.ID());
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityHighProductionFurnace();
	}
}
