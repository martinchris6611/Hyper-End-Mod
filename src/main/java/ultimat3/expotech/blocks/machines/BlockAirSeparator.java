package ultimat3.expotech.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.expotech.blocks.Ultimat3Block;
import ultimat3.expotech.blocks.machines.tileentity.TileAirSeparator;

public class BlockAirSeparator extends Ultimat3Block implements ITileEntityProvider {

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
		return createNewTileEntity(world, metadata);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileAirSeparator();
	}
	
    @Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int par1, int par2) {
        super.onBlockEventReceived(world, x, y, z, par1, par2);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(par1, par2) : false;
    }
    
    public void breakBlock(World world, int x, int y, int z, Block block, int var1) {
		super.breakBlock(world, x, y, z, block, var1);
		world.removeTileEntity(x, y, z);
    }
}
