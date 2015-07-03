package ultimat3.expotech.init;

import cpw.mods.fml.common.registry.GameRegistry;
import ultimat3.expotech.blocks.machines.tileentity.TileExpoFurnace;
import ultimat3.expotech.blocks.machines.tileentity.TileForcefieldController;
import ultimat3.expotech.blocks.machines.tileentity.TileMatterConsolidator;
import ultimat3.expotech.blocks.machines.tileentity.TileMechanicalAssembler;
import ultimat3.expotech.multiblock.ParticleAcceleratorMultiblock;

public class ModTileEntities {
	
	public static final String	FORCEFIELD_CONTROLLER_ID	= "u3_expot_forcefieldController";
	public static final String	MECHANICAL_ASSEMBLER_ID		= "u3_expot_mechanicalAssembler";
	public static final String	PARTICLE_ACCELERATOR_ID		= "u3_expot_particleAccelerator";
	public static final String	MATTER_CONSOLIDATOR_ID		= "u3_expot_matterConsolidator";
	public static final String	EXPO_FURNACE_ID				= "u3_expot_expoFurnace";
	
	public static void init() {
		GameRegistry.registerTileEntity(TileForcefieldController.class, FORCEFIELD_CONTROLLER_ID);
		GameRegistry.registerTileEntity(TileMechanicalAssembler.class, MECHANICAL_ASSEMBLER_ID);
		GameRegistry.registerTileEntity(ParticleAcceleratorMultiblock.class, PARTICLE_ACCELERATOR_ID);
		GameRegistry.registerTileEntity(TileMatterConsolidator.class, MATTER_CONSOLIDATOR_ID);
		GameRegistry.registerTileEntity(TileExpoFurnace.class, EXPO_FURNACE_ID);
	}
	
}
