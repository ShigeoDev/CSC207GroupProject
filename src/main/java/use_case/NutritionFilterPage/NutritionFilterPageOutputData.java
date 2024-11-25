package use_case.NutritionFilterPage;

import entity.Recipe;

import java.util.List;

/**
 * Output Data for the Filter Based on Nutrition Use Case.
 */
public class NutritionFilterPageOutputData {

    private final List<Recipe> recipes;
    private final boolean useCaseFailed;

    public NutritionFilterPageOutputData(List<Recipe> recipes, boolean useCaseFailed) {
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
