package ultimat3.endgamemod.blocks.machines;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntitySuperCompressor;
import ultimat3.endgamemod.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSuperCompressor extends BlockMachine  {

	public BlockSuperCompressor(String name) {
		super(name);
		this.guiID = Reference.GuiIds.SUPER_COMPRESSOR.ID();
		this.blockType = (BlockMachine) ModBlocks.blockSuperCompressor;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntitySuperCompressor();
	}
}
