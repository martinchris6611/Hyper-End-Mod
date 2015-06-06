package ultimat3.endgamemod.items;

import java.util.List;

import ultimat3.endgamemod.Reference;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MetaItem extends Ultimat3Item {
	
	public String[]	names;
	
	@SideOnly(Side.CLIENT)
	public IIcon[]	icons;
	
	public MetaItem(String[] names) {
		super(names[0]);
		this.names = names;
		this.setHasSubtypes(true);
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		this.icons = new IIcon[names.length];
		for(int meta = 0; meta < icons.length; ++meta) {
			this.icons[meta] = register.registerIcon(Reference.MOD_ID + ":" + names[meta]);
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item.u3_egm_" + names[stack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int meta = 0; meta < names.length; ++meta) {
			list.add(new ItemStack(this, 1, meta));
		}
	}
	
}
