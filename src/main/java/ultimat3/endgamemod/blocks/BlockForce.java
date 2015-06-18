package ultimat3.endgamemod.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockForce extends Ultimat3Block {

	public BlockForce() {
		super("blockForce", Material.glass);
		setBlockUnbreakable();
		
	}

	public static List<UUID> LastClickName = new ArrayList<UUID>();
	public static List<Long> LastClickTime = new ArrayList<Long>();
	
	@Override
	public int getRenderBlockPass() {
		return 1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if(player.isSneaking()) return false;
		for(int i=0;; i++) {
			if(LastClickName.get(i).equals(player.getUniqueID())) {
				if(world.getWorldTime() - LastClickTime.get(i) < 1000 && world.getWorldTime() - LastClickTime.get(i) >= 0 ) {
					break;
				} else LastClickTime.set(i, world.getWorldTime());
			}
			if(i == LastClickName.size() - 1) return false;
		}
		if(hitX == 0.0f) {
			if(world.getBlock(x + 3, y + 1, z) == Blocks.air)
			player.setPosition(x + 3, y + 1, z);
		} else if (hitX == 1.0f) {
			if(world.getBlock(x - 3, y + 1, z) == Blocks.air)
			player.setPosition(x - 3, y + 1, z);
		}
		else if(hitY == 0.0f) {
			if(world.getBlock(x, y + 1, z) == Blocks.air && world.getBlock(x, y + 2, z) == Blocks.air && world.getBlock(x, y + 3, z) == Blocks.air)
			player.setPosition(x, y + 2, z);
		} 	else if(hitY == 1.0f) {
			if(world.getBlock(x, y - 1, z) == Blocks.air && world.getBlock(x, y - 2, z) == Blocks.air && world.getBlock(x, y - 3, z) == Blocks.air)
			player.setPosition(x, y - 2, z);
		}
		else if(hitZ == 0.0f) {
			if(world.getBlock(x, y, z + 3) == Blocks.air)
			player.setPosition(x, y, z + 3);
		} else if (hitZ == 1.0f) {
			if(world.getBlock(x, y, z - 3) == Blocks.air)
			player.setPosition(x, y, z - 3);
		}
		return false;
	}

}
