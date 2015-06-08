package ultimat3.endgamemod;

public class Reference 
{
    /** The FML identifier for our mod. */
	public static final String MOD_ID = "u3_egm";
	
	/** The version of the mod. */
	public static final String VERSION = "1.0";
	
	/** The name of the mod */
	public static final String NAME = "End game mod";
	
	/** Proxy locations */
	public static final String COMMON_PROXY = "ultimat3.endgamemod.proxies.CommonProxy";
	public static final String CLIENT_PROXY = "ultimat3.endgamemod.proxies.ClientProxy";

	/** The network channel name */
	public static final String	NETWORK_CHANNEL	= MOD_ID;
	
	/**
	 * Add all Gui Ids in here.
	 * 
	 * @author Ebilkill
	 */
	public enum GuiIds {
		PRODUCTION_FURNACE(0), HIGH_PRODUCTION_FURNACE(1), SUPER_COMPRESSOR(2);
		
		private final int ID;
		
		private GuiIds(int ID) {
			this.ID = ID;
		}
		
		public int ID() {
			return this.ID;
		}
	}
}
