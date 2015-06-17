package ultimat3.endgamemod.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityForcefieldController;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMachine;
import ultimat3.endgamemod.init.ModBlocks;

public class BlockForcefieldController extends BlockMachine {

	public BlockForcefieldController(String name) {
		super(name,  Reference.GuiIds.FORCEFIELD_CONTROLLER.ID());
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
			return new TileEntityForcefieldController();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[0];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		icons[0] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName());
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int var1) {
		TileEntityForcefieldController tile = (TileEntityForcefieldController) world.getTileEntity(x, y, z);
		tile.eraseField(tile.shape, tile.radius);
		tile.shape = 0;
		tile.radius= 0;
		super.breakBlock(world, x, y, z, block, var1);
	}
	
}
