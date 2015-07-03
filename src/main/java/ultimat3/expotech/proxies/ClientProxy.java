package ultimat3.expotech.proxies;

import net.minecraftforge.client.MinecraftForgeClient;
import ultimat3.expotech.init.ModItems;
import ultimat3.expotech.items.models.RenderParticlePistol;
import ultimat3.expotech.items.models.RenderPlasmaKatana;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemRenderers() {
		MinecraftForgeClient.registerItemRenderer(ModItems.swordKatana, new RenderPlasmaKatana());
		MinecraftForgeClient.registerItemRenderer(ModItems.particlePistol, new RenderParticlePistol());
	}
	
}
