package ultimat3.expotech.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.expotech.gui.container.ContainerForcefieldController;

public class GuiForcefieldController extends GuiMachine {
	public GuiForcefieldController(EntityPlayer player, World world, int x,
			int y, int z) {
		super(new ContainerForcefieldController(player, world, x, y, z),
				"forceFieldController", player, world, x, y, z);
	}
}