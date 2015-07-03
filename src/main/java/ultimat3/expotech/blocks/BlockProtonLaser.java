package ultimat3.expotech.blocks;

import net.minecraft.block.material.Material;

public class BlockProtonLaser extends Ultimat3Block {

	public BlockProtonLaser(String name) {
		super(name, Material.rock);
		setHarvestLevel("pickaxe", 10);
	}

}
