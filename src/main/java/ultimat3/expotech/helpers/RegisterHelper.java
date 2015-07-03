package ultimat3.expotech.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * An easier way of registering with Forge.
 */
public class RegisterHelper {
	/**
	 * Registers the specified block.
	 * 
	 * @param block - block to be registered.
	 */
	public static void registerBlock(Block block) {
		registerBlock(block, ItemBlock.class);
	}
	
	/**
	 * Registers the specified block using the ItemBlock specified.
	 * 
	 * @param block The block to be registered.
	 * @param clazz The ItemBlock class to use for this block.
	 */
	public static void registerBlock(Block block, Class<? extends ItemBlock> clazz) {
		GameRegistry.registerBlock(block, clazz, block.getUnlocalizedName());
	}
	
	
	/**
	 * Registers the specified item.
	 * 
	 * @param item - item to be registered.
	 */
	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
}
