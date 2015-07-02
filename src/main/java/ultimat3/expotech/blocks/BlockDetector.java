package ultimat3.expotech.blocks;

import net.minecraft.block.material.Material;

public class BlockDetector extends Ultimat3Block {

	public BlockDetector(String name) {
		super(name, Material.rock);
		setHarvestLevel("pickaxe", 10);
	}

}
