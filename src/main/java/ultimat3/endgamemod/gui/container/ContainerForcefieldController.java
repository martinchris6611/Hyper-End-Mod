package ultimat3.endgamemod.gui.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityForcefieldController;
import ultimat3.endgamemod.gui.slot.SlotMachineFuel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class ContainerForcefieldController extends Container {
	
	private World							world;
	private TileEntityForcefieldController	machine;
	private short							lastCookTime;
	private short							lastBurnTime;
	
	private void addCraftingSlots() {
		// Nice grid pattern
		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				addSlotToContainer(new Slot(machine, x + y * 3, 38 + x * 18, 17 + y * 18));
			}
		}
	}
	
	public ContainerForcefieldController(EntityPlayer player, World world, int x, int y, int z) {
		this.world = world;
		
		this.machine = ((TileEntityForcefieldController) world.getTileEntity(x, y, z));
		
		// Furnace slots
		addCraftingSlots();
		
		// player slots
		this.bindPlayerInventory(player.inventory);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		// Main inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		// Hotbar
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
