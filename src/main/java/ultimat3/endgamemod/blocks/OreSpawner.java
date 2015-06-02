package ultimat3.endgamemod.blocks;

import java.util.Random;

import ultimat3.endgamemod.EndGame;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * Not gonna lie, basically copied this off the internet.
 * 
 * @author Ebilkill
 */
public class OreSpawner implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateNether(World world, Random random, int x, int y) {
	}

	// Might change this to be metadata bound
	private void generateSurface(World world, Random random, int x, int y) {
		this.addOreSpawn(EndGame.blockScandiumOre, 0, world, random, x, y, 16,
				16, 1, random.nextInt(1), 2, 50);
		this.addOreSpawn(EndGame.blockTitaniumOre, 0, world, random, x, y, 16,
				16, 5, 1, 6, 20);
		this.addOreSpawn(EndGame.blockLithiumOre, 0, world, random, x, y, 16,
				16, 5, 2, 6, 30);
		this.addOreSpawn(EndGame.blockCobaltOre, 0, world, random, x, y, 16,
				16, 5, 2, 6, 30);
	}

	private void generateEnd(World world, Random random, int x, int y) {
	}

	/**
	 * Adds an orespawn to the world. This only works in the overworld at the
	 * moment.
	 * 
	 * @param block
	 *            The Block to spawn
	 * @param world
	 *            The World to spawn in
	 * @param random
	 *            A Random object for retrieving random positions within the
	 *            world to spawn the Block
	 * @param blockXPos
	 *            An int for passing the X-Coordinate for the Generation method
	 * @param blockZPos
	 *            An int for passing the Z-Coordinate for the Generation method
	 * @param maxX
	 *            An int for setting the maximum X-Coordinate values for
	 *            spawning on the X-Axis on a Per-Chunk basis
	 * @param maxZ
	 *            An int for setting the maximum Z-Coordinate values for
	 *            spawning on the Z-Axis on a Per-Chunk basis
	 * @param maxVeinSize
	 *            An int for setting the maximum size of a vein
	 * @param chancesToSpawn
	 *            An int for the Number of chances available for the Block to
	 *            spawn per-chunk
	 * @param minY
	 *            An int for the minimum Y-Coordinate height at which this block
	 *            may spawn
	 * @param maxY
	 *            An int for the maximum Y-Coordinate height at which this block
	 *            may spawn
	 **/
	public void addOreSpawn(Block block, int meta, World world, Random random,
			int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize,
			int chancesToSpawn, int minY, int maxY) {
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
			(new WorldGenMinable(block, meta, maxVeinSize, Blocks.stone))
					.generate(world, random, posX, posY, posZ);
		}
	}
}
