package ultimat3.endgamemod.rendering;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class MachineRender implements ISimpleBlockRenderingHandler {

	int renderId = -1;
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		if(modelId != renderId) return false;
		int meta = world.getBlockMetadata(x, y, z);
		return true;
		
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		if(renderId == -1)
			renderId = RenderingRegistry.getNextAvailableRenderId();
		return renderId;
	}
	
	public int setRenderId(int id) {
		return renderId = id;
	}

}
