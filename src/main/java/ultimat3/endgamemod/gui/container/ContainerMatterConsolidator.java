package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMatterConsolidator extends ContainerMachine {

	public ContainerMatterConsolidator(EntityPlayer player, World world, int x,
			int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		return new Slot[]{
				new Slot(machine, 0, 36, 33),
				new Slot(machine, 1, 80, 24),
				new Slot(machine, 2, 124, 33),
				new Slot(machine, 3, 80, 60)
		};
	}

	@Override
	public boolean pushStack(ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canHold(ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}
}
