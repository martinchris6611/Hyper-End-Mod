package ultimat3.endgamemod.multiblock;

import java.util.List;

import ultimat3.endgamemod.EndGame;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces.End;

public class ItemSpecialBlock extends ItemBlock {
	public ItemSpecialBlock(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        Block bk = Block.getBlockFromItem(p_77624_1_.getItem());
        if (bk == EndGame.hollow)
            p_77624_3_.add("Make a 3x3x3 structure with a hollow center");
        if (bk == EndGame.particleController)
            p_77624_3_.add("Surround this block with stone.");
    }
}
