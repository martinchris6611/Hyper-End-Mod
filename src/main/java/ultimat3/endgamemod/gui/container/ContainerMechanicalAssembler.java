package ultimat3.endgamemod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMechanicalAssembler;

<<<<<<< HEAD
public class ContainerMechanicalAssembler extends ContainerMachine {
=======
public class ContainerMechanicalAssembler extends Container {

	@SuppressWarnings("unused")
	private World world;
	private TileEntityMechanicalAssembler machine;
	//private short lastCookTime;
	//private short lastBurnTime;
>>>>>>> fa3e81984486903a91271b84c55562d3acb95396

	public ContainerMechanicalAssembler(EntityPlayer player, World world,
			int x, int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (slotID <= 15) {
				
				if (!this.mergeItemStack(itemstack1, 16, this.inventorySlots.size(), true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
				
			} else if(!this.mergeItemStack(itemstack1, 0, 16, false)) {
				return null;
			}
			
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
		}
		
		return itemstack;
	}

	@Override
	protected Slot[] getSlotsForAdding() {
		Slot[] slots = new Slot[17];
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				slots[i*4+j] = new Slot(machine, i*4+j, 11 + j*18, 8 + i*18);
			}
		slots[16] = new Slot(machine, 16, 124, 35);
		return slots;
	}
}
