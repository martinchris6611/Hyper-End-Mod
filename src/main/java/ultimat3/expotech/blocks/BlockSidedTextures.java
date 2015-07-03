package ultimat3.expotech.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ultimat3.expotech.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSidedTextures extends Ultimat3Block {

	IIcon[] icons = new IIcon[15];
	
	public BlockSidedTextures(String name, Material blockMaterial) {
		super(name, blockMaterial);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if(meta%4==0) meta+=3;
		else meta--;
		if (side == 0)
			return icons[0];
		else if (side == 1) {
			switch (meta) {
			case 0:
				return icons[1];
			case 1:
				return icons[2];
			case 2:
				return icons[3];
			case 3:
				return icons[4];
			case 4:
				return icons[9];
			case 5:
				return icons[10];
			case 6:
				return icons[11];
			case 7:
				return icons[12];
			}
		}
		boolean flag = meta <= 3;
		meta %= 4;
		side -= 2;
		if (meta == 0) {
			switch (side) {
			case 0: return flag ? icons[5] : icons[13];
			case 1:	return icons[6];
			case 2:	return icons[8];
			case 3:	return icons[7];
			}
		}
		if (meta == 1) {
			switch (side) {
			case 3: return flag ? icons[5] : icons[13];
			case 0:	return icons[8];
			case 1:	return icons[7];
			case 2:	return icons[6];
			}
		}
		if (meta == 2) {
			switch (side) {
			case 1: return flag ? icons[5] : icons[13];
			case 2:	return icons[7];
			case 3:	return icons[8];
			case 0:	return icons[6];
			}
		}
		if (meta == 3) {
			switch (side) {
			case 2: return flag ? icons[5] : icons[13];
			case 3:	return icons[6];
			case 0:	return icons[7];
			case 1:	return icons[8];
			}
		}
		return icons[14];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		icons[0] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "Bottom");
		icons[1] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "Top1");
		icons[2] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "Top2");
		icons[3] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "Top3");
		icons[4] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "Top4");
		icons[5] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "Front");
		icons[6] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "Back");
		icons[7] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "Left");
		icons[8] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "Right");
		icons[9] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "TopOff1");
		icons[10] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "TopOff2");
		icons[11] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "TopOff3");
		icons[12] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "TopOff4");
		icons[13] = icon.registerIcon(Reference.RESOURCE_PREFIX + getName()
				+ "FrontOff");
		icons[14] = icon.registerIcon("iron_block");
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack stack) {
		int side = 0;
		int l = MathHelper
				.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, (l+1)%4 + 4, 2);
	}

}
