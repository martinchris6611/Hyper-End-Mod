package ultimat3.endgamemod.items;

import ultimat3.endgamemod.blocks.BlockOre;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Ultimat3ItemBlock extends ItemBlock {
	
	private String[]	names;
	
	public Ultimat3ItemBlock(Block block) {
		super(block);
		if (block instanceof BlockOre) this.names = ((BlockOre) block).names;
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
