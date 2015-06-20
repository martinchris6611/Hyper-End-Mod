package ultimat3.endgamemod.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.Ultimat3Block;
import ultimat3.endgamemod.blocks.machines.tileentity.TileAirSeparator;

public class BlockAirSeparator extends Ultimat3Block {

	public BlockAirSeparator(String name) {
		super(name, Material.anvil);
		setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileAirSeparator();
	}
}
