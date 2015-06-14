package ultimat3.endgamemod.blocks.machines.tileentity;

import ultimat3.endgamemod.init.ModTileEntities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TileEntityForcefieldController extends TileEntityMachine implements IInventory {
	
	public TileEntityForcefieldController() {
		super(new ItemStack[12], "container." + ModTileEntities.FORCEFIELD_CONTROLLER_ID);
	}


}
