package ultimat3.endgamemod.gui;

import ultimat3.endgamemod.gui.container.ContainerParticleAccelerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiParticleAccelerator extends GuiMachine {

	public GuiParticleAccelerator(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerParticleAccelerator(player, world, x, y, z), "particleAccelerator", player, world, x, y, z);
		// TODO Auto-generated constructor stub
	}

}
