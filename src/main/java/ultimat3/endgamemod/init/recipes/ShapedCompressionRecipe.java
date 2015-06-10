package ultimat3.endgamemod.init.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ultimat3.endgamemod.blocks.machines.tileentity.TileEntitySuperCompressor;

public class ShapedCompressionRecipe implements ICompressionRecipe {
	
	/** How many horizontal slots this recipe is wide. */
	public final int			recipeWidth;
	
	/** How many vertical slots this recipe uses. */
	public final int			recipeHeight;
	
	/** Is an array of ItemStack that composes the recipe. */
	public final ItemStack[]	recipeItems;
	
	/** Is the ItemStack that you get when craft the recipe. */
	private ItemStack			recipeOutput;
	
	private boolean				useTagCompound;
	
	public ShapedCompressionRecipe(int width, int height, ItemStack[] items, ItemStack result) {
		this.recipeWidth = width;
		this.recipeHeight = height;
		this.recipeItems = items;
		this.recipeOutput = result;
	}
	
	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}
	
	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(TileEntitySuperCompressor compressor, World world) {
		for (int i = 0; i <= 3 - this.recipeWidth; ++i) {
			for (int j = 0; j <= 3 - this.recipeHeight; ++j) {
				if (this.checkMatch(compressor, i, j, true)) {
					return true;
				}
				
				if (this.checkMatch(compressor, i, j, false)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if the region of a crafting inventory is match for the recipe.
	 */
	private boolean checkMatch(TileEntitySuperCompressor compressor, int startX, int startY, boolean p_77573_4_) {
		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				int i1 = x - startX;
				int j1 = y - startY;
				ItemStack itemstack = null;
				
				if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight) {
					if (p_77573_4_) {
						itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
					} else {
						itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
					}
				}
				
				ItemStack itemstack1 = compressor.getStackInRowAndColumn(x, y);
				
				// If either is not null
				if (itemstack1 != null || itemstack != null) {
					// If one is null and the other isn't, the recipe isn't matched
					if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null) {
						return false;
					}
					
					// If the items aren't equal in this position, the recipe isn't matched
					if (itemstack.getItem() != itemstack1.getItem()) {
						return false;
					}
					
					// If either item has a different metadata... you get the point
					if (itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage()) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(TileEntitySuperCompressor compressor) {
		ItemStack itemstack = this.getRecipeOutput().copy();
		
		if (this.useTagCompound) {
			for (int i = 0; i < compressor.getSizeInventory(); ++i) {
				ItemStack itemstack1 = compressor.getStackInSlot(i);
				
				if (itemstack1 != null && itemstack1.hasTagCompound()) {
					itemstack.setTagCompound((NBTTagCompound) itemstack1.stackTagCompound.copy());
				}
			}
		}
		
		return itemstack;
	}
	
	/**
	 * Returns the size of the recipe area
	 */
	public int getRecipeSize() {
		return this.recipeWidth * this.recipeHeight;
	}
	
	public ShapedCompressionRecipe setUseTagCompound() {
		this.useTagCompound = true;
		return this;
	}
}
