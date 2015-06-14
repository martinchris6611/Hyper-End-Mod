package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityForcefieldController;
import ultimat3.endgamemod.init.ModBlocks;

public class BlockForcefieldController extends BlockMachine {

	public BlockForcefieldController(String name) {
		super(name);
		this.guiID = Reference.GuiIds.FORCEFIELD_CONTROLLER.ID();
		this.blockType = (BlockMachine) this;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityForcefieldController();
	}
	
}
