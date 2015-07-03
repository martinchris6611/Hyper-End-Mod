package ultimat3.expotech.init;

import ultimat3.expotech.rendering.MachineRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ModRendering {
	
	public static ISimpleBlockRenderingHandler machineRender = new MachineRender();	
	
	public static void init() {
		RenderingRegistry.registerBlockHandler(machineRender.getRenderId(), machineRender);
	}
}