package ultimat3.expotech;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ultimat3.expotech.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabExpoTech extends CreativeTabs {

	public CreativeTabExpoTech(String lable) {
		super(lable);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(ModBlocks.blockExpoFurnace);
	}

}
