package ultimat3.expotech.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.expotech.gui.container.ContainerParticleAccelerator;

public class GuiParticleAccelerator extends GuiMachine {

	public GuiParticleAccelerator(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerParticleAccelerator(player, world, x, y, z), "particleAccelerator", player, world, x, y, z);
	}

}
