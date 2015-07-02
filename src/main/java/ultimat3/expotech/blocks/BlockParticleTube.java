package ultimat3.expotech.blocks;

import net.minecraft.block.material.Material;

public class BlockParticleTube extends Ultimat3Block {

	public BlockParticleTube(String name) {
		super(name, Material.rock);
		setHarvestLevel("pickaxe", 10);
	}

}
