package ultimat3.expotech.blocks;

import net.minecraft.block.material.Material;

public class BlockOre extends MetaBlock {
	
	public BlockOre(String[] names, Material material, float hardness, float resistance, int harvestLevel) {
		super(names, material);
		this.names = names;
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel("pickaxe", harvestLevel);
	}

	// can be used to add different hardness/resistance to a metadata based block
	/*@Override
	public float getBlockHardness(World p_149712_1_, int p_149712_2_, int p_149712_3_, int p_149712_4_) {
		return super.getBlockHardness(p_149712_1_, p_149712_2_, p_149712_3_, p_149712_4_);
	}*/
}
