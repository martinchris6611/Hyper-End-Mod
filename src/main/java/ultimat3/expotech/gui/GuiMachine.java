package ultimat3.expotech.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import ultimat3.expotech.Reference;
import ultimat3.expotech.blocks.machines.tileentity.TileMachine;
import ultimat3.expotech.gui.container.ContainerMachine;

abstract public class GuiMachine extends GuiContainer {
	
	protected ResourceLocation				guiTex;
	protected TileMachine				machine;
	
	public GuiMachine(ContainerMachine container, String name, EntityPlayer player, World world, int x, int y, int z) {
		super(container);
		this.machine = ((TileMachine) world.getTileEntity(x, y, z));
		this.guiTex = new ResourceLocation(Reference.MOD_ID + ":textures/gui/container/" + name + ".png");
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		ITextureObject tex = mc.renderEngine.getTexture(guiTex);
		GL11.glColor4f(1, 1, 1, 1);
		mc.renderEngine.bindTexture(guiTex);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
	
}
