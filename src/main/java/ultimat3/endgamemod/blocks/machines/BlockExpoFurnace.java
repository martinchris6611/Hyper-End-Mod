package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileExpoFurnace;

public class BlockExpoFurnace extends BlockMachine {

	public BlockExpoFurnace(String name) {
		super(name, Reference.GuiIds.EXPO_FURNACE.ID());
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
			return new TileExpoFurnace();
	}

}
