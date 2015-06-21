package ultimat3.endgamemod.blocks;

import net.minecraft.block.material.Material;

public class BlockDetector extends Ultimat3Block {

	public BlockDetector() {
		super("blockDetector", Material.rock);
		setHarvestLevel("pickaxe", 10);
	}

}
