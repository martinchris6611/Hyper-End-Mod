package ultimat3.endgamemod.items;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.blocks.Ultimat3Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class Ultimat3Item extends Item {
	/** @see #getName() */
	private final String name;

	public Ultimat3Item(String name) {
		this.name = name;
		GameRegistry.registerItem(this, name, EndGame.MODID);
		this.setUnlocalizedName(EndGame.MODID + "_" + name);
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
