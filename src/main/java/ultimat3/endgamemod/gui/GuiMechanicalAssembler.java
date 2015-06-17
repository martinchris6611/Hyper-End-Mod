package ultimat3.endgamemod.gui;

import ultimat3.endgamemod.gui.container.ContainerMechanicalAssembler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class GuiMechanicalAssembler extends GuiContainer {

	public GuiMechanicalAssembler(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerMechanicalAssembler(player, world, x, y, z));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
		// TODO Auto-generated method stub
		
	}

}
