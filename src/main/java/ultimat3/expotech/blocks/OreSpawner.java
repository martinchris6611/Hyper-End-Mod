package ultimat3.expotech.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import ultimat3.expotech.init.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class OreSpawner implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == 0)
				generateSurface(world, random, chunkX * 16, chunkZ * 16);
	}
	// Might change this to be metadata bound
	private void generateSurface(World world, Random random, int x, int y) {
		// TODO Needs balancing
		this.addOreSpawn(ModBlocks.blockOres, 0, world, random, x, y, 16, 16, 8, 10, 6, 60); // Aluminum
		this.addOreSpawn(ModBlocks.blockOres, 4, world, random, x, y, 16, 16, 1, 1, 2, 50); // Scandium
		this.addOreSpawn(ModBlocks.blockOres, 5, world, random, x, y, 16, 16, 5, 1 , 6, 20); // Titanium
	}
	
	public void addOreSpawn(Block block, int meta, World world, Random random, int blockXPos, int blockZPos, int maxX,
			int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {
		assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";
		
		int diffBtwnMinMaxY = maxY - minY;
		for (int x = 0; x < chancesToSpawn; x++) {
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block, meta, maxVeinSize, Blocks.stone)).generate(world, random, posX, posY, posZ);
		}
	}
}
