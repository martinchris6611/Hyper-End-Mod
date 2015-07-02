package ultimat3.expotech.multiblock;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultimat3.expotech.blocks.machines.BlockMachine;
import ultimat3.expotech.gui.GuiMachine;
import ultimat3.expotech.gui.container.ContainerMachine;

public abstract class BlockMultiBlock extends BlockMachine {

	public BlockMultiBlock(String name, Class<? extends GuiMachine> classGui,
			Class<? extends ContainerMachine> classContainer) {
		super(name, classGui, classContainer);
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
