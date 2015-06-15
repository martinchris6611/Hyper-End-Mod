package ultimat3.endgamemod.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockForce extends Ultimat3Block {

	public BlockForce() {
		super("blockForce", Material.glass);
		setBlockUnbreakable();
		
	}
	
	@Override
	public int getRenderBlockPass() {
		return 1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB AABB, List list, Entity entity)
    {
        AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+1, z+1);

        if (!(entity instanceof EntityPlayer) && AABB.intersectsWith(axisalignedbb1));
        {
            list.add(axisalignedbb1);
        }
    }
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if(hitX - (float) x < -0.49f) {
			player.setPosition(player.posX + 3, player.posY, player.posZ);
		} else if (hitX - (float) x > 0.49f) {
			player.setPosition(player.posX - 3, player.posY, player.posZ);
		}
		else if(hitY - (float) y < -0.49f) {
			player.setPosition(player.posX, player.posY + 3, player.posZ);
		} else if (hitY - (float) y > 0.49f) {
			player.setPosition(player.posX, player.posY - 3, player.posZ);
		}
		else if(hitZ - (float) z < -0.49f) {
			player.setPosition(player.posX, player.posY, player.posZ + 3);
		} else if (hitZ - (float) z > 0.49f) {
			player.setPosition(player.posX, player.posY, player.posZ - 3);
		}
		return false;
	}

}
