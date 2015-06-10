package ultimat3.endgamemod.blocks.machines;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityProductionFurnace;
import ultimat3.endgamemod.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockProductionFurnace extends BlockMachine  {

	public BlockProductionFurnace(String name) {
		super(name);
		this.guiID = Reference.GuiIds.PRODUCTION_FURNACE.ID();
		this.blockType = (BlockMachine) ModBlocks.blockProductionFurnace;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityProductionFurnace();
	}
}
