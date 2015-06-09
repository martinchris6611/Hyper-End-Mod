package ultimat3.endgamemod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.tileentity.TileEntityHighProductionFurnace;
import ultimat3.endgamemod.blocks.tileentity.TileEntityProductionFurnace;
import ultimat3.endgamemod.blocks.tileentity.TileEntitySuperCompressor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class MachineBlock extends MetaBlock {
	
	
	private IIcon othericon;
	
	public MachineBlock(String[] names, Material blockMaterial) {
		super(names, blockMaterial);
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
    	if(side==2) return othericon;
    	else return blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        this.blockIcon = icon.registerIcon("furnace_side");
        this.othericon = icon.registerIcon("furnace_front_off");
    }
    
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		// Sneaking allows placing blocks
		if (player.isSneaking())
			return false;
		
		// Cache the metadata
		int metadata = world.getBlockMetadata(x, y, z);
		
		// open the correct
		if (metadata == 0) {
			player.openGui(EndGame.instance, Reference.GuiIds.PRODUCTION_FURNACE.ID(), world, x, y, z);
		} else if (metadata == 1) {
			player.openGui(EndGame.instance, Reference.GuiIds.HIGH_PRODUCTION_FURNACE.ID(), world, x, y, z);
		} else if (metadata == 2) {
			player.openGui(EndGame.instance, Reference.GuiIds.SUPER_COMPRESSOR.ID(), world, x, y, z);
		}
		
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		if (metadata == 0)
			return new TileEntityProductionFurnace();
		else if (metadata == 1)
			return new TileEntityHighProductionFurnace();
		else if (metadata == 2) 
			return new TileEntitySuperCompressor();
		return null;
	}
	
}
