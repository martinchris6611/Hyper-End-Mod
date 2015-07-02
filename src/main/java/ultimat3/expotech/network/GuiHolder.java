package ultimat3.expotech.network;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.expotech.gui.GuiMachine;

public class GuiHolder {
	Class<? extends GuiMachine> classGui;
	Class<? extends GuiContainer> classContainer;

	public GuiHolder(Class<? extends GuiMachine> classGui,
			Class<? extends GuiContainer> classContainer) {
		this.classGui = classGui;
		this.classContainer = classContainer;
	}

	public GuiMachine newGui(EntityPlayer player, World world, int x, int y,
			int z) {
		try {
			return classGui.getDeclaredConstructor(EntityPlayer.class,
					World.class, int.class, int.class, int.class)
					.newInstance(player, world, x, y, z);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public GuiContainer newContainer(EntityPlayer player, World world, int x,
			int y, int z) {
		try {
			return classContainer.getDeclaredConstructor(EntityPlayer.class,
					World.class, int.class, int.class, int.class)
					.newInstance(player, world, x, y, z);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
