package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.gui.slot.SlotWhitelist;
import ultimat3.endgamemod.init.ModItems;

public class ContainerForcefieldController extends ContainerMachine {

	public ContainerForcefieldController(EntityPlayer player, World world,
			int x, int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		return new Slot[] {

				new SlotWhitelist(machine, 0, 38, 45, new ItemStack(
						ModItems.itemFFModifiers, 1, ModItems.sizeUpgrade)),

				new SlotWhitelist(machine, 1, 121, 45, new ItemStack(
						ModItems.itemFFModifiers, 1, ModItems.shapeCube),
						new ItemStack(ModItems.itemFFModifiers, 1,
								ModItems.shapeSphere), new ItemStack(
								ModItems.itemFFModifiers, 1,
								ModItems.shapeOctahedron)) };
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
		return stack.getItem().equals(ModItems.itemFFModifiers);
	}
}
