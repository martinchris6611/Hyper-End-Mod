package ultimat3.expotech.blocks;

import net.minecraft.block.material.Material;

public class BlockElectroMagnet extends BlockSidedTextures {

	public BlockElectroMagnet(String name) {
		super(name, Material.rock);
		setHarvestLevel("pickaxe", 10);
	}

}
