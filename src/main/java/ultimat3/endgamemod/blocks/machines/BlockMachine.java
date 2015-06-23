package ultimat3.endgamemod.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.blocks.BlockSidedTextures;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMachine;

public abstract class BlockMachine extends BlockSidedTextures implements ITileEntityProvider {

	protected int guiID;

	public BlockMachine(String name, int gui_ID) {
		super(name, Material.anvil);
		guiID = gui_ID;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	private static int rotate(int x) {
		return (x + 3) % 4;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		// Get the current item
		ItemStack currentItem = player.getHeldItem();

		// If the player is sneaking
		if (player.isSneaking()) {
			// Can't get the item here; this method doesn't even get called when
			// an item is in the player's hand :o
			return false;
		}

		// If it's a stick, turn this machine
		if (currentItem != null && currentItem.getItem() == Items.stick) {
			int meta = (world.getBlockMetadata(x, y, z) + 3) % 4;
			world.setBlockMetadataWithNotify(x, y, z, meta, 3);

			// else, open this machine's gui
		} else
			player.openGui(EndGame.instance, this.guiID, world, x, y, z);
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block,
			int var1) {
		// Drop the items on the server
		if (!world.isRemote) {
			ItemStack[] stacks = ((TileEntityMachine) world.getTileEntity(x, y,
					z)).items;
			for (int i = 0; i < stacks.length; i++) {
				// make sure the item exists before dropping it
				if (stacks[i] != null)
					world.spawnEntityInWorld(new EntityItem(world, x, y, z,
							stacks[i]));
			}
		}
		// And do whatever normally happens when breaking a block
		super.breakBlock(world, x, y, z, block, var1);
		world.removeTileEntity(x, y, z);
	}
	
    @Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int par1, int par2)
    {
        super.onBlockEventReceived(world, x, y, z, par1, par2);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(par1, par2) : false;
    }

	@Override
	public abstract TileEntity createNewTileEntity(World world, int meta);
	
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return createNewTileEntity(world, meta);
	}
}
