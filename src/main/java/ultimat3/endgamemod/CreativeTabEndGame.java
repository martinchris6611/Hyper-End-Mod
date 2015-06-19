package ultimat3.endgamemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ultimat3.endgamemod.init.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabEndGame extends CreativeTabs {

	public CreativeTabEndGame(String lable) {
		super(lable);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return ModItems.swordKatana;
	}

}
