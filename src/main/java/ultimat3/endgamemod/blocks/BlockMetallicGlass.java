package ultimat3.endgamemod.blocks;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;

public class BlockMetallicGlass extends Ultimat3Block {

	public BlockMetallicGlass() {
		super("blockMetallicGlass", Material.glass);
		setHarvestLevel("pickaxe", 2);
	}
	@Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}