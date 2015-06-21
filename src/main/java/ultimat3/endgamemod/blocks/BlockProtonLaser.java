package ultimat3.endgamemod.blocks;

import net.minecraft.block.material.Material;

public class BlockProtonLaser extends Ultimat3Block {

	public BlockProtonLaser() {
		super("blockProtonLaser", Material.rock);
		setHarvestLevel("pickaxe", 10);
	}

}
