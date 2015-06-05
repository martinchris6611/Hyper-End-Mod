package ultimat3.endgamemod.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetaBlock extends Ultimat3Block {
	
	public String[] names;
	
	public MetaBlock(String[] names, Material blockMaterial) {
		super(names[0], blockMaterial);
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int meta = 0; meta < names.length; ++meta) {
			list.add(new ItemStack(this, 1, meta));
		}
	}
}
