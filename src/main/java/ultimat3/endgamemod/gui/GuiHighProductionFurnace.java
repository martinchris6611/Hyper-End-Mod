package ultimat3.endgamemod.gui;

import org.lwjgl.opengl.GL11;

import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.tileentity.TileEntityHighProductionFurnace;
import ultimat3.endgamemod.blocks.tileentity.TileEntityProductionFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiHighProductionFurnace extends GuiContainer {
	
	private ResourceLocation				guiTex;
	private TileEntityHighProductionFurnace	furnace;
	
	public GuiHighProductionFurnace(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerHighProductionFurnace(player, world, x, y, z));
		this.furnace = ((TileEntityHighProductionFurnace) world.getTileEntity(x, y, z));
		this.guiTex = new ResourceLocation("textures/gui/container/furnace.png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = I18n.format("container." + Reference.MOD_ID + "_productionFurnace", new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2,
				4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		ITextureObject tex = mc.renderEngine.getTexture(guiTex);
		GL11.glColor4f(1, 1, 1, 1);
		mc.renderEngine.bindTexture(guiTex);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		
		if (this.furnace.isBurning()) {
			int i1 = this.furnace.getFurnaceTimeRemaining(13);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
			i1 = this.furnace.getCookProgress(24);
			this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
		}
	}
	
}
