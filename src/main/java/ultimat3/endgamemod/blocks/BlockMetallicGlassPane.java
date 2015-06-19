package ultimat3.endgamemod.blocks;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
