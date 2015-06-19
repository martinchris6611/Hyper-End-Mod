package ultimat3.endgamemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ultimat3.endgamemod.gui.container.ContainerHighProductionFurnace;

public class GuiHighProductionFurnace extends GuiMachine {

	public GuiHighProductionFurnace(EntityPlayer player, World world, int x,
			int y, int z) {
		super(new ContainerHighProductionFurnace(player, world, x, y, z),
				"highProductionFurnace", player, world, x, y, z);
	}
}
