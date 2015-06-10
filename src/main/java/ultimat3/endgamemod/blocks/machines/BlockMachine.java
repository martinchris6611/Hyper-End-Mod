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
	
	
	private IIcon[] icons = new IIcon[4];
	BlockMachine blockType;
	int guiID;
	
	public BlockMachine(String name) {
		super(name, Material.anvil);
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
		if(side<=1) return icons[side];
		if(side==meta) return icons[3];
		return icons[2];
    }
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
		icons[0] = icon.registerIcon(Reference.MOD_ID + ":" + getName() + "Bottom");
		icons[1] = icon.registerIcon(Reference.MOD_ID + ":" + getName() + "Top");
		icons[3] = icon.registerIcon(Reference.MOD_ID + ":" + getName() + "FrontOn");
		icons[2] = icon.registerIcon(Reference.MOD_ID + ":" + getName() + "Side");
    }
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		int side = 0;
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		switch(l) {
		case 0: side = 2; break;
		case 1: side = 5; break;
		case 2: side = 3; break;
		case 3: side = 4; break;
		}
		world.setBlockMetadataWithNotify(x, y, z, side, 2);
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (player.isSneaking()) {
			if(player.getHeldItem().getItem()==Items.stick) {
				breakBlock(world, x, y, z, world.getBlock(x, y, z), 0);
				world.setBlockToAir(x, y, z);
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(this.blockType)));
			}
			return false;
		}
		if(player.getHeldItem().getItem()==Items.stick) {
			int meta = (world.getBlockMetadata(x, y, z) + 3)%4 + 2;
			world.setBlockMetadataWithNotify(x, y, z, meta, 3);
		}
		else player.openGui(EndGame.instance, this.guiID, world, x, y, z);
		return true;
	}
	
	@Override
	 public void breakBlock(World world, int x, int y, int z, Block block, int var1) {
		super.breakBlock(world, x, y, z, block, var1);
		if(world.isRemote) return;
		ItemStack[] stacks = ((TileEntityMachine) world.getTileEntity(x, y, z)).items;
		for(int i=0; i<stacks.length; i++) {
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, stacks[i]));
		}
	}
}
