package ultimat3.endgamemod.items.models;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ultimat3.endgamemod.Reference;

public class RenderParticlePistol implements IItemRenderer {

	ModelParticlePistol	model;
	
	public RenderParticlePistol() {
		this.model = new ModelParticlePistol();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch (type) {
			case INVENTORY:
				return true;
			default:
				break;
		}
		return false;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Minecraft mc = Minecraft.getMinecraft();
		GL11.glPushMatrix();
		mc.renderEngine.bindTexture(new ResourceLocation(Reference.RESOURCE_PREFIX + "models/weapons/particlepistol.png"));
		switch (type) {
			case ENTITY:
				// Render the model
				model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				break;
			case EQUIPPED:
				// Render the model
				model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				break;
			case EQUIPPED_FIRST_PERSON:
				// Render the model
				model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				break;
			case INVENTORY:
				// render the model
				model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				break;
			default:
				break;
		}
		GL11.glPopMatrix();
	}
}
