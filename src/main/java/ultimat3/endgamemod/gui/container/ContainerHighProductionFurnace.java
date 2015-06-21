package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.gui.slot.SlotMachineFuel;

public class ContainerHighProductionFurnace extends ContainerMachine {

	public ContainerHighProductionFurnace(EntityPlayer player, World world,
			int x, int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		return new Slot[] {
				new Slot(machine, 0, 76, 17),
				new Slot(machine, 1, 56, 17),
				new Slot(machine, 2, 36, 17),
				new Slot(machine, 3, 16, 17),
				new SlotMachineFuel(machine, 4,
						new ItemStack(Blocks.coal_block), 56, 53),
				new Slot(this.machine, 5, 116, 60),
				new Slot(this.machine, 6, 116, 40),
				new Slot(this.machine, 7, 116, 20),
				new Slot(this.machine, 8, 116, 0) };
	}

	@Override
	public boolean slotAcceptStack(int slotID, ItemStack stack) {
		return true;
	}
}
