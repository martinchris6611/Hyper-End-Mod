package ultimat3.endgamemod.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.gui.ContainerProductionFurnace;
import ultimat3.endgamemod.gui.GuiProductionFurnace;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GuiIds.PRODUCTION_FURNACE.ID()) {
			return new ContainerProductionFurnace(player, world, x, y, z);
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GuiIds.PRODUCTION_FURNACE.ID()) {
			return new GuiProductionFurnace(player, world, x, y, z);
		}
		return null;
	}
	
}
