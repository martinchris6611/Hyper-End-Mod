package ultimat3.expotech.items;

import net.minecraft.item.Item;
import ultimat3.expotech.ExpoTech;
import ultimat3.expotech.Reference;
import ultimat3.expotech.blocks.Ultimat3Block;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Superclass this for new item classes (for the Ultimat3 modding team).<br />
 * Can also be instantiated by itself for very basic items.
 */
public class Ultimat3Item extends Item {
	/** @see #getName() */
	private final String	name;
	
	/**
	 * Simple constructor. Only requires an internal name for the item. Sets the
	 * unlocalized name equal to ModId_name.
	 * 
	 * @param name The internal name of the item.
	 */
	public Ultimat3Item(String name) {
		this.name = name;
		GameRegistry.registerItem(this, name, Reference.MOD_ID);
		this.setUnlocalizedName(Reference.MOD_ID + "_" + name);
		this.setTextureName(Reference.RESOURCE_PREFIX + name);
		this.setCreativeTab(ExpoTech.creaTab);
	}
	
	/**
	 * 1.8 stuff. Same as in {@link Ultimat3Block#getName()}
	 * 
	 * @return The in-code name of this item.
	 */
	public String getName() {
		return this.name;
	}
}
