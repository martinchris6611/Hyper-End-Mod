package ultimat3.endgamemod.proxies;

import ultimat3.endgamemod.init.ModItems;
import ultimat3.endgamemod.items.models.RenderPlasmaKatana;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemRenderers() {
		MinecraftForgeClient.registerItemRenderer(ModItems.swordKatana, new RenderPlasmaKatana());
	}
	
}
