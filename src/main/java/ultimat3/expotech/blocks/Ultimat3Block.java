package ultimat3.expotech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import ultimat3.expotech.ExpoTech;
import ultimat3.expotech.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Use this class to create new blocks (for the Ultimat3 modding team).
 */
public class Ultimat3Block extends Block {
	
	/** @see #getName() */
	private final String	name;
	
	/**
	 * Creates a new instance of an Ultimat3Block. Automatically registers this
	 * block in the {@link GameRegistry} and sets the unlocalized name.
	 * 
	 * @param name
	 *            The name of the block. This is not the display name, but
	 *            rather the name in the code.
	 * @param blockMaterial
	 *            The {@link Material} of the block.
	 */
	public Ultimat3Block(String name, Material blockMaterial) {
		this(name, ItemBlock.class, blockMaterial);
	}
	
	/**
	 * Creates a new instance of an Ultimat3Block. Automatically registers this
	 * block in the {@link GameRegistry} and sets the unlocalized name. Can
	 * receive a custom {@link ItemBlock} as well.
	 * 
	 * @param name The name of the block. This is not the display name, but rather the name in the code.
	 * @param clazz The {@link ItemBlock} class that this block uses.
	 * 
	 * @param blockMaterial The {@link Material} of the block.
	 */
	public Ultimat3Block(String name, Class<? extends ItemBlock> clazz, Material blockMaterial) {
		super(blockMaterial);
		this.name = name;
		// RegisterHelper.registerBlock(this, clazz, name, EndGame.MODID); // will be used in 1.8, do NOT remove
		this.setBlockName(Reference.MOD_ID + "_" + name);
		this.setBlockTextureName(Reference.RESOURCE_PREFIX + name); // will be removed in 1.8
		this.setCreativeTab(ExpoTech.creaTab);
	}
	
	/**
	 * 1.8 stuff. <br />
	 * The value returned by this is the name of the .json files that define
	 * this block.
	 */
	public String getName() {
		return this.name;
	}
}
