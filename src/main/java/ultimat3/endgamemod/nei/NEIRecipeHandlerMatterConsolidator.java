package ultimat3.endgamemod.nei;

import static ultimat3.endgamemod.recipes.MatterConsolidatorRecipes.output;
import static ultimat3.endgamemod.recipes.MatterConsolidatorRecipes.recipe;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import ultimat3.endgamemod.Reference;
import ultimat3.endgamemod.gui.GuiMatterConsolidator;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class NEIRecipeHandlerMatterConsolidator extends TemplateRecipeHandler {

	@Override
	public String getRecipeName() {
		return "Matter Consolidator";
		/*return StatCollector
				.translateToLocal("endgamemod.nei.matterconsolidator");*/
	}

	@Override
	public String getGuiTexture() {
		return Reference.RESOURCE_PREFIX
				+ "textures/gui/nei/matterConsolidator.png";
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiMatterConsolidator.class;
	}

	@Override
	public String getOverlayIdentifier() {
		return "ExpoTechMatterConsolidator";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(
				new Rectangle(149, 32, 16, 16), "ExpoTechMatterConsolidator",
				new Object[0]));
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {

		if (result == null) {
			return;
		}

		for (int i = 0; i < recipe.size(); i++) {
			if(output.get(i).isItemEqual(result)) {
				ClassRecipe rec = new ClassRecipe(recipe.get(i)[0],
						recipe.get(i)[1], recipe.get(i)[2], output.get(i));
				arecipes.add(rec);
			}
		}
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("ExpoTechMatterConsolidator")
				&& getClass() == NEIRecipeHandlerMatterConsolidator.class) {
			for (int i = 0; i < recipe.size(); i++) {
				ClassRecipe rec = new ClassRecipe(recipe.get(i)[0],
						recipe.get(i)[1], recipe.get(i)[2], output.get(i));
				arecipes.add(rec);
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	  @Override
	  public void loadUsageRecipes(ItemStack ingredient) {

			if (ingredient == null) {
				return;
			}

			for (int i = 0; i < recipe.size(); i++) {
				if(recipe.get(i)[0].isItemEqual(ingredient) || recipe.get(i)[1].isItemEqual(ingredient) || recipe.get(i)[2].isItemEqual(ingredient))
				{
					ClassRecipe rec = new ClassRecipe(recipe.get(i)[0], recipe.get(i)[1],
							recipe.get(i)[2], output.get(i));
					rec.setIngredientPermutation(rec.input, ingredient);
					arecipes.add(rec);
				}
			}
	  }

	public class ClassRecipe extends TemplateRecipeHandler.CachedRecipe {

		private final ArrayList<PositionedStack> input;
		private final PositionedStack output;

		@Override
		public List<PositionedStack> getIngredients() {
			return getCycledIngredients(cycleticks / 20, input);
		}

		@Override
		public PositionedStack getResult() {
			return output;
		}

		public ClassRecipe(ItemStack in1, ItemStack in2, ItemStack in3,
				ItemStack result) {
			this.input = new ArrayList<PositionedStack>();
			this.input.add(new PositionedStack(in1, 31, 16));
			this.input.add(new PositionedStack(in2, 75, 7));
			this.input.add(new PositionedStack(in3, 119, 16));
			this.output = new PositionedStack(result, 75, 41);
            for (PositionedStack p : input)
                p.generatePermutations();
		}

	}

}
