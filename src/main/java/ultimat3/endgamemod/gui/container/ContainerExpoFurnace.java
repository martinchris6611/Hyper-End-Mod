package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.init.ModItems;
import ultimat3.endgamemod.recipes.ExpoFurnaceRecipes;

public class ContainerExpoFurnace extends ContainerMachine {

	public ContainerExpoFurnace(EntityPlayer player, World world, int x, int y,
			int z) {
		super(player, world, x, y, z);
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		Slot[] slots = new Slot[13];
		slots[0] = new Slot(machine, 0, 13, 55);
		for(int i=1; i<=6; i++) {
			slots[i] = new Slot(machine, i, 24 + 19*i, 24);
		}
		for(int i=1; i<=6; i++) {
			slots[i+6] = new Slot(machine, i+6, 24 + 19*i, 55);
		}
		return slots;
	}

	@Override
	public boolean pushStack(ItemStack stack) {
		if(stack.getItem().equals(ModItems.itemCoils)) {
			return mergeItemStack(stack, 0, 1, false);
		}
		else if(ExpoFurnaceRecipes.getOutput(stack, 1024) != null) {
			return mergeItemStack(stack, 1, 7, false);
		}
		return true;
	}

	@Override
	public boolean canHold(ItemStack stack) {
		return stack.getItem().equals(ModItems.itemCoils) || ExpoFurnaceRecipes.getOutput(stack, 1024) != null;
	}
}
