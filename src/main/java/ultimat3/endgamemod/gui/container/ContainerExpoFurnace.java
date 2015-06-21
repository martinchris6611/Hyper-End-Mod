package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerExpoFurnace extends ContainerMachine {

	public ContainerExpoFurnace(EntityPlayer player, World world, int x, int y,
			int z) {
		super(player, world, x, y, z);
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		Slot[] slots = new Slot[7];
		slots[0] = new Slot(machine, 0, 80, 80);
		for(int i=1; i<7; i++) {
			slots[i] = new Slot(machine, i, 20 + 20*i, 100);
		}
		return slots;
	}

	@Override
	public boolean slotAcceptStack(int slotID, ItemStack stack) {
		return true;
	}
}
