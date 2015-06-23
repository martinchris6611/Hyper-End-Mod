package ultimat3.endgamemod.multiblock;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.machines.BlockMachine;

public abstract class BlockMultiBlock extends BlockMachine {
	//public static CreativeTabEndGame creaTab = new CreativeTabEndGame(Reference.MOD_ID);
	
	public BlockMultiBlock(String name, int gui_ID) {
		super(name, gui_ID);
	}

	protected boolean	formed	= false;

	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileMultiBlock) {
            TileMultiBlock multiBlock = (TileMultiBlock) tile;
            if (multiBlock.hasMaster()) {
                if (multiBlock.isMaster()) {
                    if (!multiBlock.checkMultiBlockForm())
                        multiBlock.resetStructure();
                } else {
                    if (!multiBlock.checkForMaster()) {
                        TileMultiBlock master = (TileMultiBlock) world.getTileEntity(multiBlock.getMasterX(), multiBlock.getMasterY(), multiBlock.getMasterZ());
                        master.resetStructure();
                        formed = false;
                    }
                }
            }
        }
        super.onNeighborBlockChange(world, x, y, z, block);
    }

    @Override
    public abstract boolean hasTileEntity(int metadata);
}
