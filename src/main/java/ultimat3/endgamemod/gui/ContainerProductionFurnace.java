package ultimat3.endgamemod.gui;

import ultimat3.endgamemod.blocks.tileentity.TileEntityProductionFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.world.World;

public class ContainerProductionFurnace extends Container {
	
	private World						world;
	private TileEntityProductionFurnace	machine;
	
	public ContainerProductionFurnace(EntityPlayer player, World world, int x, int y, int z) {
		this.world = world;
		
		this.machine = ((TileEntityProductionFurnace) world.getTileEntity(x, y, z));
		
		// Furnace slots
		addSlotToContainer(new Slot(machine, 0, 50, 20));
		addSlotToContainer(new Slot(machine, 1, 50, 50));
		addSlotToContainer(new SlotFurnace(player, this.machine, 2, 137, 51));
		
		this.bindPlayerInventory(player.inventory);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		// Main inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 120 + i * 18));
			}
		}
		
		// Hotbar
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 178));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
}
