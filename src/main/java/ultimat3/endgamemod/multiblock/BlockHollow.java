package ultimat3.endgamemod.multiblock;


import ultimat3.endgamemod.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHollow extends BlockMultiBlock {
	public BlockHollow() {
        super( Reference.GuiIds.PARTICLE_ACCELERATOR.ID());
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileHollowMultiBlock(metadata, metadata, null, textureName, null);
    }
}
