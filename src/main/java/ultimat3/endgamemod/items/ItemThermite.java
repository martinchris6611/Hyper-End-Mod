package ultimat3.endgamemod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.init.ModBlocks;

public class ItemThermite extends Ultimat3Item {
    public ItemThermite() {
    	super("thermite");
    }
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (world.getBlock(x, y, z) != Blocks.snow_layer) {
        	switch(side) {
        	case 0: y--; break;
        	case 1: y++; break;
        	case 2: z--; break;
        	case 3: z++; break;
        	case 4:	x--; break;
        	case 5: x++; break;
        	}
            if (!world.isAirBlock(x, y, z)) {
                return false;
            }
        }
        if (!player.canPlayerEdit(x, y, z, side, stack)) {
            return false;
        }
        else if (ModBlocks.blockThermiteWire.canPlaceBlockAt(world, x, y, z)) {
                --stack.stackSize;
                world.setBlock(x, y, z, ModBlocks.blockThermiteWire);
            }

            return true;
        
    }
}