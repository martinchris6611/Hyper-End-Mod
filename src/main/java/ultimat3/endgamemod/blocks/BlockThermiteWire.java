package ultimat3.endgamemod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.helpers.CuboidIterator;
import ultimat3.endgamemod.init.ModBlocks;
import ultimat3.endgamemod.init.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockThermiteWire extends Ultimat3Block {
	
	private static final String name = "thermiteWire";
	
	private static IIcon iconTransparent;
	private static IIcon iconThermiteWire;
	
	public BlockThermiteWire() {
		super(name, Material.glass);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
		setLightLevel(0.2f);
		setLightOpacity(0);
	}
	
	
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        return null;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || world.getBlock(x, y - 1, z) == Blocks.glowstone;
    }

	@Override
	public Item getItemDropped(int par1, Random par2, int par3) {
		return ModItems.itemThermite;
	}

	@Override
	public Item getItem(World par1, int par2, int par3, int par4) {
		return ModItems.itemThermite;
	}
	
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if(side<=1) return iconThermiteWire;
		return iconTransparent;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		iconTransparent = icon.registerIcon(Reference.RESOURCE_PREFIX + getName() + "Side");
		iconThermiteWire = icon.registerIcon(Reference.RESOURCE_PREFIX + getName() + "Top");
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
		
		// Removes thermiteWire
		world.setBlockToAir(x, y, z);
		Block block;
		// Loops 3x3 under the block
		for (CuboidIterator it = new CuboidIterator(x-1, y-1, z-1, x+1, y-1, z+1); !it.end(); it.next()) {	// the y coordinate can only be -1(down)
			
			block = world.getBlock(it.getX(), it.getY(), it.getZ());
			
			// If block is meltable
			if (block.getBlockHardness(world, it.getX(), it.getY(), it.getZ()) >= 0
					&& block.getBlockHardness(world, it.getX(), it.getY(), it.getZ()) < 50 && block != this) {
				
				// Get meta
				int meta = world.getBlockMetadata(it.getX(), it.getY(), it.getZ());
				// Make sure everything can be broken properly
				block.breakBlock(world, it.getX(), it.getY(), it.getZ(), block, meta);
				// Drop the item
				block.dropBlockAsItem(world, it.getX(), it.getY(), it.getZ(), meta, 0);
				
				if(world.getBlock(it.getX(), it.getY(), it.getZ())!=ModBlocks.blockThermiteFire) {
					// Set block to thermite fire
					world.setBlock(it.getX(), it.getY(), it.getZ(), ModBlocks.blockThermiteFire);
					// Set time for it to melt down
					world.scheduleBlockUpdate(it.getX(), it.getY(), it.getZ(), ModBlocks.blockThermiteFire, 10);
				} else {
					// But if it already exist, just increase it's range
					if(!world.isRemote) world.setBlockMetadataWithNotify(it.getX(), it.getY(), it.getZ(), world.getBlockMetadata(it.getX(), it.getY(), it.getZ())+1, 2);					
				}
			}
		}
		
		// Make sure 3x3x3 neighbors get lit if they are thermite
		for (CuboidIterator it = new CuboidIterator(x-1, y-1, z-1, x+1, y+1, z+1); !it.end(); it.next()) {
			if (world.getBlock(it.getX(), it.getY(), it.getZ()) == ModBlocks.blockThermiteWire)
				fire(world, it.getX(), it.getY(), it.getZ());
		}
	}
}
