package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.gui.slot.SlotMachineFuel;

public class ContainerProductionFurnace extends ContainerMachine {

	public ContainerProductionFurnace(EntityPlayer player, World world, int x,
			int y, int z) {
		super(player, world, x, y, z);
	}	
	
	
	@Override
	protected Slot[] getSlotsForAdding() {
		return new Slot[] {
				new Slot(machine, 0, 56, 17),
				new SlotMachineFuel(machine, 1,
						new ItemStack(Blocks.coal_block), 56, 53),
				new Slot(machine, 2, 116, 35) };
	}


	@Override
	public boolean slotAcceptStack(int slotID, ItemStack stack) {
		return true;
	}
}
