package ultimat3.expotech.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.expotech.gui.slot.SlotWhitelist;
import ultimat3.expotech.init.ModItems;

public class ContainerForcefieldController extends ContainerMachine {

	public ContainerForcefieldController(EntityPlayer player, World world,
			int x, int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		/*return new Slot[] {

				new SlotWhitelist(machine, 0, 38, 45, new ItemStack(
						ModItems.itemFFModifiers, 1, ModItems.sizeUpgrade)),

				new SlotWhitelist(machine, 1, 121, 45, new ItemStack(
						ModItems.itemFFModifiers, 1, ModItems.shapeCube),
						new ItemStack(ModItems.itemFFModifiers, 1,
								ModItems.shapeSphere), new ItemStack(
								ModItems.itemFFModifiers, 1,
								ModItems.shapeOctahedron)) };*/
		return new Slot[0];
	}

	@Override
	public boolean pushStack(ItemStack stack) {
		if(stack.getItemDamage() == 0) {
			return mergeItemStack(stack, 0, 1, false);
		}
		else return mergeItemStack(stack, 1, 2, false);
	}

	@Override
	public boolean canHold(ItemStack stack) {
		return false;//stack.getItem().equals(ModItems.itemFFModifiers);
	}
}
