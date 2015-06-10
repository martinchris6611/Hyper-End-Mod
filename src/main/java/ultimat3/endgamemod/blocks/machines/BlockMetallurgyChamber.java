package ultimat3.endgamemod.blocks.machines;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMachine;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMetallurgyChamber;
import ultimat3.endgamemod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMetallurgyChamber extends BlockMachine  {

	public BlockMetallurgyChamber(String name) {
		super(name);
		this.guiID = Reference.GuiIds.METALLURGY_CHAMBER.ID();
		this.blockType = (BlockMachine) ModBlocks.blockMetallurgyChamber;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityMetallurgyChamber();
	}
}
