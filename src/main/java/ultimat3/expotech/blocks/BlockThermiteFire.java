package ultimat3.expotech.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockThermiteFire extends Ultimat3Block {

	public BlockThermiteFire(String name) {
		super(name, Material.rock);
		this.setTickRandomly(true);
		setLightLevel(1);
		setLightOpacity(0);
	}
	
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        return null;
    }
	
	@Override
    public void updateTick(World world, int x, int y, int z, Random r) {
		int meta = world.getBlockMetadata(x, y, z);
		this.breakBlock(world, x, y, z, this, meta);
		world.setBlockToAir(x, y, z);
		// If its done burning
		if(meta == 0)
			return;
		y--;
		Block block = world.getBlock(x, y, z);
		// If its not meltable
		if (block.getBlockHardness(world, x, y, z) < 0
			|| block.getBlockHardness(world, x, y, z) > 50)
			return;
		// Make sure everything can be broken properly
		block.breakBlock(world, x, y, z, block, 0);
		// Drop the item
		block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		// Set block to thermite fire
		world.setBlock(x, y, z, this);
		// Set its meta
		world.setBlockMetadataWithNotify(x, y, z, meta-1, 2);
		// Set time for it to melt down
		world.scheduleBlockUpdate(x, y, z, this, 10);
	}
	
	public int tickRate() {
		return 10;
	}
	
	@Override
    public int quantityDropped(Random r)
    {
        return 0;
    }
}
