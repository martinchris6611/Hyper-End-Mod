package ultimat3.endgamemod.fluids.tanks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.Ultimat3Block;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockUltimat3Tank extends Ultimat3Block {

	public BlockUltimat3Tank(String name) {
		super(name, Material.glass);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileUltimat3Tank(64000);
	}
}