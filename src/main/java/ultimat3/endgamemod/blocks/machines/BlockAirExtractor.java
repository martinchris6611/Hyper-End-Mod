package ultimat3.endgamemod.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.Ultimat3Block;
import ultimat3.endgamemod.blocks.machines.tileentity.TileAirExtractor;

public class BlockAirExtractor extends Ultimat3Block {

	public BlockAirExtractor(String name) {
		super(name, Material.anvil);
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileAirExtractor();
	}

}
