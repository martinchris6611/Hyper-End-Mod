package ultimat3.endgamemod.multiblock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import ultimat3.endgamemod.CreativeTabEndGame;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMachine;
import net.minecraft.world.World;

public class BlockMultiBlock extends BlockContainer {
	//public static CreativeTabEndGame creaTab = new CreativeTabEndGame(Reference.MOD_ID);
	
	protected IIcon[]	icons	= new IIcon[4];
	protected int		guiID;
	protected boolean	formed	= false;
	
	public BlockMultiBlock(int gui_ID) {
        super(Material.anvil);
        guiID = gui_ID;
        this.setCreativeTab(EndGame.creaTab);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileMultiBlock) {
            TileMultiBlock multiBlock = (TileMultiBlock) tile;
            if (multiBlock.hasMaster()) {
                if (multiBlock.isMaster()) {
                    if (!multiBlock.checkMultiBlockForm())
                        multiBlock.resetStructure();
                } else {
                    if (!multiBlock.checkForMaster()) {
                        TileMultiBlock master = (TileMultiBlock) world.getTileEntity(multiBlock.getMasterX(), multiBlock.getMasterY(), multiBlock.getMasterZ());
                        master.resetStructure();
                        formed = false;
                    }
                }
            }
        }
        super.onNeighborBlockChange(world, x, y, z, block);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side <= 1)
			return icons[side];
		if (side == meta || (side == 3 && meta == 0) )
			return icons[2];
		return icons[3];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		icons[0] = icon.registerIcon(Reference.RESOURCE_PREFIX + getTextureName() + "Bottom");
		icons[1] = icon.registerIcon(Reference.RESOURCE_PREFIX + getTextureName() + "Top");
		icons[2] = icon.registerIcon(Reference.RESOURCE_PREFIX + getTextureName() + "FrontOn");
		icons[3] = icon.registerIcon(Reference.RESOURCE_PREFIX + getTextureName() + "Side");
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
		
		if (!world.isRemote) {
			// If it's a stick, turn this machine
			TileEntity tile = world.getTileEntity(x,  y,  z);
			TileMultiBlock multiBlock = (TileMultiBlock) tile;
			if(multiBlock.isFormed()) {
					player.openGui(EndGame.instance, this.guiID, world, x, y, z);
			}
		}
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
