package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMatterConsolidator extends ContainerMachine {

	public ContainerMatterConsolidator(EntityPlayer player, World world, int x,
			int y, int z) {
		super(player, world, x, y, z);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		return new Slot[]{
				new Slot(machine, 0, 124, 35),
				new Slot(machine, 1, 104, 35),
				new Slot(machine, 2, 124, 55),
				new Slot(machine, 3, 104, 55)
		};
	}

	@Override
	public boolean slotAcceptStack(int slotID, ItemStack stack) {
		return true;
	}
}
