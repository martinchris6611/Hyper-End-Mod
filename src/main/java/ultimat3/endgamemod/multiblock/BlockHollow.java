package ultimat3.endgamemod.multiblock;


import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.Reference;

public class BlockHollow extends BlockMultiBlock {
	public BlockHollow() {
       super("hollow", Reference.GuiIds.PARTICLE_ACCELERATOR.ID());
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileHollowMultiBlock(metadata, metadata, null, textureName, null);
    }

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
}
