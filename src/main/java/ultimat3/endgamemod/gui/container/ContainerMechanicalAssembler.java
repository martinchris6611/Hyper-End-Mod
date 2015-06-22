package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMechanicalAssembler extends ContainerMachine {

	public ContainerMechanicalAssembler(EntityPlayer player, World world,
			int x, int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		Slot[] slots = new Slot[17];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				slots[i * 4 + j] = new Slot(machine, i * 4 + j, 11 + j * 18,
						8 + i * 18);
			}
		slots[16] = new Slot(machine, 16, 124, 35);
		return slots;
	}

	@Override
	public boolean pushStack(ItemStack stack) {
		return mergeItemStack(stack, 0, 16, false);
	}

	@Override
	public boolean canHold(ItemStack stack) {
		return true;
	} 
}
