package ultimat3.expotech.nei;

import static ultimat3.expotech.recipes.MechanicalAssemblerRecipes.output;
import static ultimat3.expotech.recipes.MechanicalAssemblerRecipes.recipe;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import ultimat3.expotech.Reference;
import ultimat3.expotech.gui.GuiMechanicalAssembler;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class NEIRecipeHandlerMechanicalAssembler extends TemplateRecipeHandler {

	@Override
	public String getRecipeName() {
		return "Mechanical Assembler";
	}

	@Override
	public String getGuiTexture() {
		return Reference.RESOURCE_PREFIX
				+ "textures/gui/nei/mechanicalAssembler.png";
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiMechanicalAssembler.class;
	}

	@Override
	public String getOverlayIdentifier() {
		return "ExpoTechMechanicalAssembler";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(
				new Rectangle(149, 32, 16, 16), "ExpoTechMechanicalAssembler",
				new Object[0]));
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {

		if (result == null) {
			return;
		}

		for (int i = 0; i < recipe.size(); i++) {
			if(output.get(i).isItemEqual(result))
			arecipes.add(new ClassRecipe(recipe.get(i), output.get(i)));
		}
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("ExpoTechMatterConsolidator")
				&& getClass() == NEIRecipeHandlerMechanicalAssembler.class) {
			for (int i = 0; i < recipe.size(); i++) {
				arecipes.add(new ClassRecipe(recipe.get(i), output.get(i)));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	  @Override
	  public void loadUsageRecipes(ItemStack ingredient) {
			if (ingredient == null || recipe == null) {
				return;
			}

			for (int i = 0; i < recipe.size(); i++) {
				boolean flag = true;
				if(recipe.get(i) == null) continue;
				for(int x=0; x<4 && flag; x++) {
					if(recipe.get(i)[x] == null) continue;
					for(int y=0; y<4 && flag; y++) {
						
						if(recipe.get(i)[x][y] != null && recipe.get(i)[x][y].isItemEqual(ingredient)) {
							ClassRecipe rec = new ClassRecipe(recipe.get(i), output.get(i));
							rec.setIngredientPermutation(rec.input, ingredient);
							arecipes.add(rec);
							flag = false;
						}
					}
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

		public ClassRecipe(ItemStack[][] recipe, ItemStack result) {
			this.input = new ArrayList<PositionedStack>();
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(recipe[i][j]!=null)
					input.add(new PositionedStack(recipe[i][j], 10 + i * 16, 10 + j*16));
				}
			}
			output = new PositionedStack(result, 90, 58);
            for (PositionedStack p : input)
                p.generatePermutations();
		}

	}

}
