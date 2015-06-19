package ultimat3.endgamemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.endgamemod.gui.container.ContainerProductionFurnace;

public class GuiProductionFurnace extends GuiMachine {

	public GuiProductionFurnace(EntityPlayer player, World world, int x, int y,
			int z) {
		super(new ContainerProductionFurnace(player, world, x, y, z),
				"productionFurnace", player, world, x, y, z);
	}
}
