package ultimat3.endgamemod.items;

import net.minecraft.item.ItemSword;

public class ItemModSword extends ItemSword {

		public ItemModSword(String unlocalizedName, ToolMaterial material) {
			super(material);
			this.setUnlocalizedName(unlocalizedName);
			this.setTextureName("swordKatana");
		}
}
