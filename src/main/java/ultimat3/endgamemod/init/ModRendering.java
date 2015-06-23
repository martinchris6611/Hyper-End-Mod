package ultimat3.endgamemod.init;

import ultimat3.endgamemod.rendering.MachineRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ModRendering {
	
	public static ISimpleBlockRenderingHandler machineRender = new MachineRender();	
	
	public static void init() {
		RenderingRegistry.registerBlockHandler(machineRender.getRenderId(), machineRender);
	}
}
