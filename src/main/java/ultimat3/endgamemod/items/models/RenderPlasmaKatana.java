package ultimat3.endgamemod.items.models;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ultimat3.endgamemod.Reference;

public class RenderPlasmaKatana implements IItemRenderer {
	
	private ModelPlasmaKatana	model;
	private float				brightness;
	
	public RenderPlasmaKatana() {
		this.model = new ModelPlasmaKatana();
		brightness = 0.0F;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if (type == ItemRenderType.INVENTORY) {
			return false;
		}
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
		mc.renderEngine.bindTexture(new ResourceLocation(Reference.RESOURCE_PREFIX + "models/weapons/RobotBlueTM.png"));
		switch (type) {
			case ENTITY:
				GL11.glPopMatrix();
				GL11.glRotatef(0, 0, 1, 0);
				GL11.glPushMatrix();
				// place it correctly
				// GL11.glTranslatef(0.7F, 0.3F, -0.1F);
				
				// Rotates
				GL11.glRotatef(90.0F, 1, 0, 0); // X
				GL11.glRotatef(35F, 0, 1, 0); // Y
				GL11.glRotatef(100.0F, 0, 0, 1); // Z
				model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				break;
			case EQUIPPED:
				GL11.glTranslatef(0.725F, 0.25F, 0.0F); // translate model to fit in the hand of the player
				
				GL11.glRotatef(90.0F, 1.0f, 0.0f, 0.0f); // rotate 0 � on X axis
				GL11.glRotatef(35.0F, 0.0f, 1.0f, 0.0f); // rotate -5 � on Y axis
				GL11.glRotatef(-90F, 0.0f, 0.0f, 1.0f); // rotate -150 � on Z axis
				// the entity argument can/could be passed to as null.
				model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				break;
			case EQUIPPED_FIRST_PERSON:
				// I liek this lighting effect
				brightness += 0.1F;
				float currentBrightness = 0.8F + 0.1F * MathHelper.sin(brightness);
				GL11.glColor4f(currentBrightness, currentBrightness, currentBrightness, 1.0F);
				
				// place it correctly
				GL11.glTranslatef(0.7F, 0.3F, -0.1F);
				
				// Rotates
				GL11.glRotatef(90.0F, 1, 0, 0); // X
				GL11.glRotatef(40.0F, 0, 1, 0); // Y
				GL11.glRotatef(-80.0F, 0, 0, 1); // Z
				
				// Render the model
				model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				break;
			case INVENTORY:
				// Scale the thing
				float scale = 1.2F;
				GL11.glScalef(scale, scale, scale);
				// place it correctly
				GL11.glTranslatef(0.5F, -0.2F, 0.0F);
				
				// Rotates
				GL11.glRotatef(110.0F, 1, 0, 0); // X
				GL11.glRotatef(45.0F, 0, 1, 0); // Y
				GL11.glRotatef(45.0F, 0, 0, 1); // Z
				
				model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				break;
			default:
				break;
		}
		GL11.glPopMatrix();
	}
	
}
