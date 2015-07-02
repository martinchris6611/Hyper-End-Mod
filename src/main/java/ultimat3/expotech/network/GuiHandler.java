package ultimat3.expotech.network;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	private ArrayList<GuiHolder> guiHolder = new ArrayList<GuiHolder>();
	
	public int addGui(Class classGui, Class classContainer) {
		guiHolder.add(new GuiHolder(classGui, classContainer));
		return guiHolder.size() - 1;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID >= guiHolder.size()) return null;
		return guiHolder.get(ID).newContainer(player, world, x, y, z);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID >= guiHolder.size()) return null;
		return guiHolder.get(ID).newGui(player, world, x, y, z);
	}

}
