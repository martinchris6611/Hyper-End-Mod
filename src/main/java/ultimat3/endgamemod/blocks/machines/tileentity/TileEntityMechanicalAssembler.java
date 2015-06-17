package ultimat3.endgamemod.blocks.machines.tileentity;

import ultimat3.endgamemod.init.ModTileEntities;
import net.minecraft.item.ItemStack;
import cofh.api.energy.EnergyStorage;

public class TileEntityMechanicalAssembler extends TileEntityProcessingMachine {

	public TileEntityMechanicalAssembler() {
		super(100, 200, new ItemStack[17], "container." + ModTileEntities.MECHANICAL_ASSEMBLER_ID, new EnergyStorage(400000));
	}

	@Override
	public boolean canProcess() {
		return false;
	}

	@Override
	public void processItem() {		
	}
	

}
