package ultimat3.endgamemod.multiblock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.init.ModBlocks;

public class ItemSpecialBlock extends ItemBlock {
	
	public ItemSpecialBlock(Block block) {
        super(block);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        Block bk = Block.getBlockFromItem(p_77624_1_.getItem());
       // if (bk == EndGame.hollow)
            //p_77624_3_.add("Make a 3x3x3 structure with a hollow center");
        if (bk == ModBlocks.blockParticleController)
            p_77624_3_.add("This controls your particle accelerator");
    }
}
