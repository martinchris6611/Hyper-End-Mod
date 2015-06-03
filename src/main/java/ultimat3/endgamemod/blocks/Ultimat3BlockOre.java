package ultimat3.endgamemod.blocks;

import net.minecraft.block.material.Material;

/**
 * Yep, the ores. Very simple class.
 * 
 * @author Ebilkill
 */
public class Ultimat3BlockOre extends Ultimat3Block {

	public Ultimat3BlockOre(String name, Material material, float hardness, float resistance, int harvestLevel) {
		
		super(name, material);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel("pickaxe", harvestLevel);
	}
}