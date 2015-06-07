package ultimat3.endgamemod.init;

import ultimat3.endgamemod.blocks.tileentity.TileEntityHighProductionFurnace;
import ultimat3.endgamemod.blocks.tileentity.TileEntityProductionFurnace;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {

	public static final String PRODUCTION_FURNACE_ID = "u3_egm_productionFurnace";
	public static final String HIGH_PRODUCTION_FURNACE_ID = "u3_egm_highProductionFurnace";
	
	public static void init() {
		GameRegistry.registerTileEntity(TileEntityProductionFurnace.class, PRODUCTION_FURNACE_ID);
		GameRegistry.registerTileEntity(TileEntityHighProductionFurnace.class, HIGH_PRODUCTION_FURNACE_ID);
	}
	
}
