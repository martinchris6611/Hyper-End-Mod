package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerParticleAccelerator extends ContainerMachine {

	public ContainerParticleAccelerator(EntityPlayer player, World world,
			int x, int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		Slot[] slots = new Slot[10];
		
		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				slots[x *3 + y] = new Slot(machine, x + y * 3, 38 + x * 18, 17 + y * 18);
			}
		}
		
		slots[9] = new Slot(machine, 9, 116, 35);
		
		return slots;
	}

	@Override
	public boolean slotAcceptStack(int slotID, ItemStack stack) {
		return true;
	}
	
}
