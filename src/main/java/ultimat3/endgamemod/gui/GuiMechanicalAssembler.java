package ultimat3.endgamemod.gui;

<<<<<<< HEAD
import org.lwjgl.opengl.GL11;

import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityForcefieldController;
import ultimat3.endgamemod.gui.container.ContainerForcefieldController;
import ultimat3.endgamemod.gui.container.ContainerMechanicalAssembler;
=======
>>>>>>> fa3e81984486903a91271b84c55562d3acb95396
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

<<<<<<< HEAD
public class GuiMechanicalAssembler extends GuiMachine {

	public GuiMechanicalAssembler(EntityPlayer player,
			World world, int x, int y, int z) {
		super(new ContainerMechanicalAssembler(player, world, x, y, z), "mechanicalAssembler", player, world, x, y, z);
	}
	
=======
import org.lwjgl.opengl.GL11;

import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMechanicalAssembler;
import ultimat3.endgamemod.gui.container.ContainerMechanicalAssembler;

public class GuiMechanicalAssembler extends GuiContainer {

	private ResourceLocation			guiTex;
	@SuppressWarnings("unused")
	private TileEntityMechanicalAssembler	furnace;
	
	public GuiMechanicalAssembler(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerMechanicalAssembler(player, world, x, y, z));
		this.furnace = ((TileEntityMechanicalAssembler) world.getTileEntity(x, y, z));
		this.guiTex = new ResourceLocation(Reference.RESOURCE_PREFIX + "textures/gui/container/mechanicalAssembler.png");
	}
	
	@SuppressWarnings("unused")
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		ITextureObject tex = mc.renderEngine.getTexture(guiTex); // Is this needed? Not doing anything at the moment.
		GL11.glColor4f(1, 1, 1, 1);
		mc.renderEngine.bindTexture(guiTex);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
>>>>>>> fa3e81984486903a91271b84c55562d3acb95396
	
}
