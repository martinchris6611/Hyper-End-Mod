package ultimat3.endgamemod.blocks;

import net.minecraft.block.material.Material;

public class BlockElectroMagnet extends Ultimat3Block {

	public BlockElectroMagnet() {
		super("blockElectroMagnet", Material.rock);
		setHarvestLevel("pickaxe", 10);
	}

}
