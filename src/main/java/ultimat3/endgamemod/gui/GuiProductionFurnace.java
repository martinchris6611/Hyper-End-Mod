package ultimat3.endgamemod.gui;

import org.lwjgl.opengl.GL11;

import ultimat3.endgamemod.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiProductionFurnace extends GuiContainer {
	
	private ResourceLocation	guiTex;
	
	public GuiProductionFurnace(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerProductionFurnace(player, world, x, y, z));
		this.guiTex = new ResourceLocation("textures/gui/container/furnace.png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = "container." + Reference.MOD_ID + "productionFurnace";
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		ITextureObject tex = mc.renderEngine.getTexture(guiTex);
		GL11.glColor4f(1, 1, 1, 1);
		mc.renderEngine.bindTexture(guiTex);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
	}
	
}
