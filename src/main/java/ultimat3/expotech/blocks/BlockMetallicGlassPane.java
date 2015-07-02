package ultimat3.expotech.blocks;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import ultimat3.expotech.ExpoTech;
import ultimat3.expotech.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMetallicGlassPane extends BlockPane {
	public BlockMetallicGlassPane(String name) {
		super(Reference.RESOURCE_PREFIX + "blockMetallicGlass",Reference.RESOURCE_PREFIX + name, Material.glass, false);
		setHarvestLevel("pickaxe", 2);
		setCreativeTab(ExpoTech.creaTab);
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
