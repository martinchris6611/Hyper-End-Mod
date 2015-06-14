package ultimat3.endgamemod.items;

import java.util.List;

import ultimat3.endgamemod.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static ultimat3.endgamemod.Reference.RESOURCE_PREFIX;

public class ItemParticlePistol extends Ultimat3Item {

	public ItemParticlePistol(String name) {
		super(name);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		ItemStack ret = stack.copy();
		
		world.playSoundEffect(player.posX, player.posY, player.posZ, RESOURCE_PREFIX + "pew", 1, 1);
		
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
	}
	
}
