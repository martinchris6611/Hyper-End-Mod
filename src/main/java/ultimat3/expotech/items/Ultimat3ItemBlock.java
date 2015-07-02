package ultimat3.expotech.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ultimat3.expotech.blocks.MetaBlock;

public class Ultimat3ItemBlock extends ItemBlock {
	
	private String[]	names;
	
	public Ultimat3ItemBlock(Block block) {
		super(block);
		if (block instanceof MetaBlock) this.names = ((MetaBlock) block).names;
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.u3_egm_" + names[stack.getItemDamage()];
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
}
