package ultimat3.endgamemod.blocks.machines;

import net.minecraft.block.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.Ultimat3Block;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityHighProductionFurnace;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMachine;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMetallurgyChamber;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityProductionFurnace;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntitySuperCompressor;
import ultimat3.endgamemod.helpers.LogHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BlockMachine extends Ultimat3Block {
	
	protected IIcon[]			icons	= new IIcon[4];
	protected BlockMachine	blockType;
	protected int			guiID;
	
	public BlockMachine(String name) {
		super(name, Material.anvil);
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side <= 1)
			return icons[side];
		if (side == meta || (side == 2 && meta == 0) )
			return icons[2];
		return icons[3];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		icons[0] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName() + "Bottom");
		icons[1] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName() + "Top");
		icons[2] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName() + "FrontOn");
		icons[3] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName() + "Side");
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		int side = 0;
		int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		switch (l) {
			case 0:
				side = 2;
				break;
			case 1:
				side = 5;
				break;
			case 2:
				side = 3;
				break;
			case 3:
				side = 4;
				break;
		}
		world.setBlockMetadataWithNotify(x, y, z, side, 2);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		// Get the current item
		ItemStack currentItem = player.getHeldItem();
		
		// If the player is sneaking
		if (player.isSneaking()) {
			// Can't get the item here; this method doesn't even get called when an item is in the player's hand :o
			return false;
		}
		
		// If it's a stick, turn this machine
		if (currentItem != null && currentItem.getItem() == Items.stick) {
			int meta = (world.getBlockMetadata(x, y, z) + 3) % 4 + 2;
			world.setBlockMetadataWithNotify(x, y, z, meta, 3);
			
			// else, open this machine's gui
		} else
			player.openGui(EndGame.instance, this.guiID, world, x, y, z);
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int var1) {
		// Drop the items on the server
		if (!world.isRemote) {
			ItemStack[] stacks = ((TileEntityMachine) world.getTileEntity(x, y, z)).items;
			for (int i = 0; i < stacks.length; i++) {
				// make sure the item exists before dropping it
				if (stacks[i] != null)
					world.spawnEntityInWorld(new EntityItem(world, x, y, z, stacks[i]));
			}
		}
		// And do whatever normally happens when breaking a block
		super.breakBlock(world, x, y, z, block, var1);
	}
}
