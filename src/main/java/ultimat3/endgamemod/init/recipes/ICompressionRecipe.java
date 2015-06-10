package ultimat3.endgamemod.init.recipes;

import ultimat3.endgamemod.blocks.tileentity.TileEntitySuperCompressor;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICompressionRecipe {
	/**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(TileEntitySuperCompressor compressor, World world);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(TileEntitySuperCompressor compressor);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    ItemStack getRecipeOutput();
}
