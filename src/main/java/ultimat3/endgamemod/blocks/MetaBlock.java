package ultimat3.endgamemod.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import ultimat3.endgamemod.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MetaBlock extends Ultimat3Block {
	
	public String[]	names;
	@SideOnly(Side.CLIENT)
	public IIcon[]	icons;
	
	public MetaBlock(String[] names, Material blockMaterial) {
		super(names[0], blockMaterial);
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons[meta];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		this.icons = new IIcon[names.length];
		for (int meta = 0; meta < names.length; ++meta) {
			this.icons[meta] = register.registerIcon(Reference.MOD_ID + ":" + this.names[meta]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int meta = 0; meta < names.length; ++meta) {
			list.add(new ItemStack(this, 1, meta));
		}
	}
}
