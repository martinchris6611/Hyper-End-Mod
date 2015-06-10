package ultimat3.endgamemod.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.blocks.Ultimat3Block;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityHighProductionFurnace;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityMetallurgyChamber;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntityProductionFurnace;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntitySuperCompressor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockMachine extends Ultimat3Block {
	
	
	private IIcon[] icons = new IIcon[4];
	
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
		if(side==meta) return icons[2];
		return icons[3];
    }
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
		icons[0] = icon.registerIcon(Reference.MOD_ID + ":" + getName() + "Bottom");
		icons[1] = icon.registerIcon(Reference.MOD_ID + ":" + getName() + "Top");
		icons[2] = icon.registerIcon(Reference.MOD_ID + ":" + getName() + "FrontOn");
		icons[3] = icon.registerIcon(Reference.MOD_ID + ":" + getName() + "Side");
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
}
