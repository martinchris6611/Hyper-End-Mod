package ultimat3.endgamemod.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIEndModConfig implements IConfigureNEI  {

	@Override
	public String getName() {
		return "ExpoTech";
	}

	@Override
	public String getVersion() {
		return "${version}";
	}

	@Override
	public void loadConfig() {
		
	}
	
	private static void register(RecipeHandlerBase handler) {
		API.registerRecipeHandler(handler);
		API.registerUsageHandler(handler);
	}

}
