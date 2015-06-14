package ultimat3.endgamemod.blocks;

import java.util.Random;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockMetallicGlassPane extends BlockPane {
	public BlockMetallicGlassPane() {
		super(Reference.RESOURCE_PREFIX + "blockMetallicGlass",Reference.RESOURCE_PREFIX + "blockMetallicGlassPane", Material.glass, false);
		setHarvestLevel("pickaxe", 2);
		setCreativeTab(EndGame.creaTab);
		setBlockName(Reference.MOD_ID + "_blockMetallicGlassPane");
		setBlockTextureName(Reference.RESOURCE_PREFIX + "blockMetallicGlassPane");
		setBlockName(Reference.MOD_ID + "_blockMetallicGlassPane");
		
	}
	@Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
}
