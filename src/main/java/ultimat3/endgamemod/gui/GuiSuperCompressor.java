package ultimat3.endgamemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.endgamemod.gui.container.ContainerSuperCompressor;

public class GuiSuperCompressor extends GuiMachine {

	public GuiSuperCompressor(EntityPlayer player, World world, int x, int y,
			int z) {
		super(new ContainerSuperCompressor(player, world, x, y, z),
				"superCompressor", player, world, x, y, z);
	}
}
