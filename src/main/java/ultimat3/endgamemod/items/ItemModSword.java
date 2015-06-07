package ultimat3.endgamemod.items;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import net.minecraft.item.ItemSword;

public class ItemModSword extends ItemSword {

		private final String name;
		public static final String WEAPON_LOC = "";
		
		public ItemModSword(String name, ToolMaterial material) {
			super(material);
			this.name = name;
			this.setUnlocalizedName(Reference.MOD_ID + "_" + name);
			this.setTextureName(Reference.MOD_ID + "_" + name);
			this.setCreativeTab(EndGame.creaTab);
		}
}
