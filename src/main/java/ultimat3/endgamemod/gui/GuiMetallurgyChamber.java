package ultimat3.endgamemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.endgamemod.gui.container.ContainerMetallurgyChamber;

public class GuiMetallurgyChamber extends GuiMachine {

	public GuiMetallurgyChamber(EntityPlayer player, World world, int x, int y,
			int z) {
		super(new ContainerMetallurgyChamber(player, world, x, y, z),
				"metallurgyChamber", player, world, x, y, z);
	}
}
