package ultimat3.endgamemod.blocks.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMechanicalAssembler;

public class BlockMechanicalAssembler extends BlockMachine {

	public BlockMechanicalAssembler() {
		super("mechanicalAssembler", Reference.GuiIds.MECHANICAL_ASSEMBLER.ID());
	}
	
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityMechanicalAssembler();
	}

}
