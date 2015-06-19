package ultimat3.endgamemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.endgamemod.gui.container.ContainerMechanicalAssembler;

public class GuiMechanicalAssembler extends GuiMachine {

	public GuiMechanicalAssembler(EntityPlayer player, World world, int x,
			int y, int z) {
		super(new ContainerMechanicalAssembler(player, world, x, y, z),
				"mechanicalAssembler", player, world, x, y, z);
	}
}
