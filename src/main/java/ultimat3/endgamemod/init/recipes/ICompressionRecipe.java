package ultimat3.endgamemod.init.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntitySuperCompressor;

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
