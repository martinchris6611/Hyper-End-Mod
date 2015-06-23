package ultimat3.endgamemod.blocks;

import net.minecraft.block.material.Material;

public class BlockElectroMagnet extends BlockSidedTextures {

	public BlockElectroMagnet() {
		super("electroMagnet", Material.rock);
		setHarvestLevel("pickaxe", 10);
	}

}
