package ultimat3.endgamemod.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class MetaItem extends Ultimat3Item {

	public String[] names;
	
	public MetaItem(String[] names) {
		super(names[0]);
		this.names = names;
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item.u3_egm_" + names[stack.getItemDamage()];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int meta = 0; meta < names.length; ++meta) {
			list.add(new ItemStack(this, 1, meta));
		}
	}
	
}