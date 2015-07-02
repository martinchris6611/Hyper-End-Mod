package ultimat3.expotech.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.expotech.gui.container.ContainerExpoFurnace;

public class GuiExpoFurnace  extends GuiMachine {

	public GuiExpoFurnace(EntityPlayer player, World world, int x,
			int y, int z) {
		super(new ContainerExpoFurnace(player, world, x, y, z),
				"expoFurnace", player, world, x, y, z);
	}
}
