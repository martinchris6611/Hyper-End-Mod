package ultimat3.endgamemod.blocks.machines.tileentity;

import ultimat3.endgamemod.init.ModTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import cofh.api.energy.EnergyStorage;

public class TileEntityMechanicalAssembler extends TileEntityProcessingMachine {

	public TileEntityMechanicalAssembler() {
		super(100, 200, new ItemStack[17], "container." + ModTileEntities.MECHANICAL_ASSEMBLER_ID, new EnergyStorage(400000));
		//TEMP
		outputSlots[0]=16;
	}

	@Override
	public boolean canProcess() {
		if(items[0] != null && items[0].isItemEqual(new ItemStack(Blocks.dirt))) return true;
		return false;
	}

	@Override
	public void processItem() {
		this.items[0]=null;
		this.items[outputSlots[0]]=new ItemStack(Blocks.diamond_block);
	}
	

}
