package ultimat3.endgamemod.blocks;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.tileentity.TileEntityHighProductionFurnace;
import ultimat3.endgamemod.blocks.tileentity.TileEntityProductionFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MachineBlock extends MetaBlock {
	
	public MachineBlock(String[] names, Material blockMaterial) {
		super(names, blockMaterial);
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (player.isSneaking())
			return false;
		
		if(world.getBlockMetadata(x, y, z) == 0) {
			player.openGui(EndGame.instance, Reference.GuiIds.PRODUCTION_FURNACE.ID(), world, x, y, z);
		}
		else if (world.getBlockMetadata(x, y, z) == 1) {
			player.openGui(EndGame.instance, Reference.GuiIds.HIGH_PRODUCTION_FURNACE.ID(), world, x, y, z);
		}
		
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		if (metadata == 0)
			return new TileEntityProductionFurnace();
		else if (metadata == 1)
			return new TileEntityHighProductionFurnace();
		return super.createTileEntity(world, metadata);
	}
	
}
