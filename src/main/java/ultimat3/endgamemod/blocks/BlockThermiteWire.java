package ultimat3.endgamemod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.helpers.Neighbor;
import ultimat3.endgamemod.init.ModBlocks;
import ultimat3.endgamemod.init.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockThermiteWire extends BlockRedstoneWire {
	private static final String name = "thermiteWire";

	public BlockThermiteWire() {
		super();
		this.setBlockName(Reference.MOD_ID + "_" + name);
		this.setBlockTextureName(Reference.MOD_ID + ":" + name);
		this.setCreativeTab(EndGame.creaTab);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess par1, int par2, int par3, int par4) {
		return 8417376; // color, hex rgb in decimal
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister IIR) {
		// textureName="thermite";
		super.registerBlockIcons(IIR);
	}

	@Override
	public Item getItemDropped(int par1, Random par2, int par3) {
		return ModItems.itemThermite;
	}

	@Override
	public Item getItem(World par1, int par2, int par3, int par4) {
		return ModItems.itemThermite;
	}

	// On right clicked
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		
		Item item = player.getHeldItem().getItem();
		
		// If the item sets thermite on fire
		if (item == Items.lava_bucket || item == Items.flint_and_steel
				|| item == Item.getItemFromBlock(Blocks.torch)
				|| item == Item.getItemFromBlock(Blocks.redstone_torch)) {
			
			// Set it on fire
			fire(world, x, y, z);
		}
		return false;
	}

	public void fire(World world, int x, int y, int z) {
		
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		// Make sure 3x3x3 neighbors get lit if they are thermite
		for (Neighbor n = new Neighbor(x, y, z); !n.end(); n.next()) {
			if (world.getBlock(n.x, n.y, n.z) == ModBlocks.blockThermiteWire &&
					world.getBlockMetadata(n.x, n.y, n.z) != 1) {
				fire(world, n.x, n.y, n.z);
			}
		}
		// Removes thermiteWire
		world.setBlockToAir(x, y, z);
		Block block;
		// Loops 3x3 under the block
		for (Neighbor n = new Neighbor(x, y, z, -1, -1, -1, 1, -1, 1); !n.end(); n.next()) {	// the y coordinate can only be -1(down)
			
			block = world.getBlock(n.x, n.y, n.z);
			
			// If block is meltable
			if (block.getBlockHardness(world, n.x, n.y, n.z) >= 0
					&& block.getBlockHardness(world, n.x, n.y, n.z) < 50) {
				
				// Get meta
				int meta = world.getBlockMetadata(n.x, n.y, n.z);
				// Make sure everything can be broken properly
				block.breakBlock(world, n.x, n.y, n.z, block, meta);
				// Drop the item
				block.dropBlockAsItem(world, n.x, n.y, n.z, meta, 0);
				
				if(world.getBlock(n.x, n.y, n.z)!=ModBlocks.blockThermiteFire) {
					// Set block to thermite fire
					world.setBlock(n.x, n.y, n.z, ModBlocks.blockThermiteFire);
					// Set time for it to melt down
					world.scheduleBlockUpdate(n.x, n.y, n.z, ModBlocks.blockThermiteFire, 10);
				} else {
					// But if it already exist, just increase it's range
					if(!world.isRemote) world.setBlockMetadataWithNotify(n.x, n.y, n.z, world.getBlockMetadata(n.x, n.y, n.z)+1, 2);					
				}
			}
		}

	}
}
